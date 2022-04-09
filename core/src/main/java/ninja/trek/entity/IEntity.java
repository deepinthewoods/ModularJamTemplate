package ninja.trek.entity;

import com.badlogic.gdx.physics.box2d.World;

public abstract class IEntity {

    public void set(Object... other){};

    public void reset(){};

    public void update(float delta, World world){};

    public void onRemove(World world){};

    public void onAdd(World world, float x, float y, Entity parent){};
}
