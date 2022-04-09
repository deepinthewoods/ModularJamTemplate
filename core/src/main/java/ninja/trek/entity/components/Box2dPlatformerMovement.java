package ninja.trek.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.entity.IEntity;

public class Box2dPlatformerMovement extends IEntity {
    public static int M_UP = 0, M_DOWN = 1, M_LEFT = 2, M_RIGHT = 3;
    private static final float JUMP_IMPULSE = 7f;
    private static final float WALK_ACCELERATION = .3f;
    private static final float WALK_SPEED_LIMIT = 7f;
    float jumpTime;
    public boolean isWalking, wasWalking, isJumping;
    public boolean left;
    private transient Box2dFloorContact floorContact;
    private transient Box2dAABB physics;
    private transient AnimationSelect animSelect;
    private InputData input;

    @Override
    public void set(Object... other) {
        floorContact = (Box2dFloorContact) other[0];
        physics = (Box2dAABB) other[1];
        animSelect = (AnimationSelect) other[2];
        input = (InputData) other[3];
    }

    @Override
    public void update(float delta, World world) {
        Body body = physics.body;
        boolean onGround = floorContact.onGround;
        Vector2 position = physics.position;
        Vector2 vel = body.getLinearVelocity();
        Vector2 normal = floorContact.normal;

        float walkDir = 0f;
        if (input.move[M_RIGHT]){
            walkDir += 1f;
            left = false;
        }
        if (input.move[M_LEFT]){
            walkDir -= 1f;
            left = true;
        }
        if ((input.move[M_RIGHT] && input.move[M_LEFT]) || (!input.move[M_RIGHT] && !input.move[M_LEFT]))
            isWalking = false;
        else isWalking = true;

        if (isWalking != wasWalking){
            if (isWalking){
                animSelect.change();
//                fix.setFriction(0f);

            } else {
                animSelect.change();

                if (onGround){
                    body.setLinearVelocity(0, body.linVelWorld.y);
                }
//                fix.setFriction(1f);
            }
        }

        if (!isJumping && onGround && input.move[M_UP]){
            isJumping = true;
//            onGround = false;
            //TODO jump animation
            jumpTime = 0f;
            body.applyLinearImpulse(0f, JUMP_IMPULSE, position.x, position.y, true);
//            Gdx.app.log(TAG, "jump");
        }
        if (isJumping && !input.move[M_UP]){
            vel.set(body.getLinearVelocity());

            isJumping = false;
            if (vel.y > 0){
//                Gdx.app.log(TAG, "stop jump");
                body.setLinearVelocity(vel.x, 0f);
            }
        }
        if ((input.move[M_LEFT] && body.getLinearVelocity().x > -WALK_SPEED_LIMIT) || (input.move[M_RIGHT] && body.getLinearVelocity().x < WALK_SPEED_LIMIT)){
            if (onGround)
                body.applyLinearImpulse(walkDir * normal.x, walkDir * normal.y, position.x, position.y, true);
            else
                body.applyLinearImpulse(walkDir * WALK_ACCELERATION, 0f, position.x, position.y, true);

        }
        wasWalking = isWalking;
    }
}
