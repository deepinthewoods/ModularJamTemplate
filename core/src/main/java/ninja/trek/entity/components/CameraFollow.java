package ninja.trek.entity.components;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.entity.IEntity;

public class CameraFollow extends IEntity {
    public transient OrthographicCamera cam;
    private transient Box2dAABB physics;
    private transient Box2dFloorContact floor;
    public Vector2 position = new Vector2(), target = new Vector2();

    @Override
    public void set(Object... other) {
        physics = (Box2dAABB) other[0];
        floor = (Box2dFloorContact) other[1];
    }

    @Override
    public void update(float delta, World world) {
        if (cam == null) return;
        target.x = physics.position.x;
//        cam.position.x = physics.position.x;
        if (floor.onGround) target.y = physics.position.y;
        position.lerp(target, .1f);

        cam.position.set(target.x, position.y, 0);

        cam.update();

    }
}
