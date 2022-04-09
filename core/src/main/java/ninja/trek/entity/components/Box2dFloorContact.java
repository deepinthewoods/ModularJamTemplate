package ninja.trek.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.entity.Entity;
import ninja.trek.entity.IEntity;

public class Box2dFloorContact extends IEntity {
    public int groundContacts;
    public boolean onGround;
    public Vector2 normal = new Vector2();
    public float groundTime;

    public void update(float delta, World world) {
        if (groundContacts > 0) groundTime = 0f;
        groundTime += delta;
        onGround = groundTime < .1f;
    }

    @Override
    public void onRemove(World world) {

    }

    @Override
    public void onAdd(World world, float x, float y, Entity parent) {

    }

    public void reset() {
        onGround = false;
        groundContacts = 0;
    }
}