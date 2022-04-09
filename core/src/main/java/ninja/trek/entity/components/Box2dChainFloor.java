package ninja.trek.entity.components;

import static ninja.trek.entity.components.Box2dAABB.FLOOR;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.entity.Entity;
import ninja.trek.entity.IEntity;

public class Box2dChainFloor extends IEntity {
    private static final String TAG = "floor";
    transient BodyDef bodyD;
    transient FixtureDef fixD;
    transient ChainShape shape;
    float r = 10;
    private static final int VERTS_X_COUNT = 100;
    Vector2[] verts = new Vector2[VERTS_X_COUNT + 2];
    private  float[] renderVerts;
    private transient Body body;
    private transient Fixture fix;
    public Vector2 position = new Vector2();

    public Box2dChainFloor() {
        super();
        bodyD = new BodyDef();
        fixD = new FixtureDef();
//        shape = new PolygonShape();
        shape = new ChainShape();
        int x = 0;
        for (; x < VERTS_X_COUNT; x++){
            verts[x] = new Vector2(x*4, -3 + MathUtils.random(2f));
        }
        verts[x++] = new Vector2(VERTS_X_COUNT, -20);
        verts[x++] = new Vector2(0, -20);
        shape.createLoop(verts);
        renderVerts = new float[verts.length * 2];
        for (int i = 0; i < verts.length; i++){
            renderVerts[i*2] = verts[i].x;
            renderVerts[i*2+1] = verts[i].y;
        }

//        shape.set(verts);
        fixD.shape = shape;

        fixD.friction = 1f;
        bodyD.type = BodyDef.BodyType.StaticBody;
        fixD.filter.maskBits = Short.MAX_VALUE;
        fixD.filter.categoryBits = FLOOR;
    }
    @Override
    public void onAdd(World world, float x, float y, Entity parent){
        body = world.createBody(bodyD);
        fix = body.createFixture(fixD);
        fix.setUserData(parent);
        body.setTransform(x, y, 0);
        position.set(body.getPosition());
    }

}
