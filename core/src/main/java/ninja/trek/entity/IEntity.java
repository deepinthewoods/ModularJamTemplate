package ninja.trek.entity;

import com.badlogic.gdx.physics.box2d.World;

public interface IPhysics {
    void reset();

    void update();

    void onRemove(World world);

    void onAdd(World world, float x, float y);
}
