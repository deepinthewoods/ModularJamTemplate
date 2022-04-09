package ninja.trek.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.entity.Entity;
import ninja.trek.entity.IEntity;

public class Box2dAABB extends Physics {
    public static final short PLAYER_WEAPON = 1, PLAYER = 2, ENEMY_WEAPON = 4, ENEMY = 8, FLOOR = 16;


    static BodyDef bodyD;
    static FixtureDef fixD;
    static PolygonShape shape;
    static float h = 2, w = .5f;
    private static final FixtureDef sensorD;
    private static final PolygonShape sensorShape;
    static Vector2[] verts = {
            new Vector2(-w, h),
            new Vector2(w, h),
            new Vector2(-w, 0),
            new Vector2(w, 0)
    };
    static Vector2[] sensorVerts = {
            new Vector2(-w, .2f),
            new Vector2(w, .2f),
            new Vector2(-w, -.2f),
            new Vector2(w, -.2f)
    };

    static {
        bodyD = new BodyDef();
        fixD = new FixtureDef();
        shape = new PolygonShape();
        shape.set(verts);
        fixD.shape = shape;
        fixD.friction = 0.1f;
        bodyD.type = BodyDef.BodyType.DynamicBody;
        bodyD.allowSleep = false;
        sensorD = new FixtureDef();
        sensorShape = new PolygonShape();
        sensorShape.set(sensorVerts);
        sensorD.shape = sensorShape;
        sensorD.isSensor = true;
        fixD.filter.categoryBits = ENEMY;
        fixD.filter.maskBits = (PLAYER_WEAPON + FLOOR + PLAYER);
    }

    public transient Body body;
    public transient Fixture fix, sensorFix;

    public void onAdd(World world, float x, float y, Entity parent){
        body = world.createBody(bodyD);
        fix = body.createFixture(fixD);
        fix.setUserData(parent);
        sensorFix = body.createFixture(sensorD);
        sensorFix.setUserData(parent);

        body.setTransform(x, y, 0);
        position.set(body.getPosition());
    }

    public void update(float delta, World world){
        position.set(body.getPosition());
    }

    public void reset(){
        body = null;
        position.set(0, 0);
    }

    public void onRemove(World world) {
        if (body != null){
            world.destroyBody(body);
            body = null;
        }
    }
}

