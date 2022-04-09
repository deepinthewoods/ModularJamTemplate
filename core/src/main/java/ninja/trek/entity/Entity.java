package ninja.trek.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;

public abstract class Entity implements Pool.Poolable {

    public static final float SPRITE_SIZE = 12.8f, SPRITE_OFFSET = SPRITE_SIZE/2f;
    public boolean markedForRemoval;
    public float time;
    IEntity[] updates;
    Draw[] draw = {};


    public Entity(){
//        setConnections();
//        reset();
    };

    public void setConnections(){

    }

    @Override
    public void reset() {
        markedForRemoval = false;
        time = 0f;
        for (IEntity u : updates) u.reset();
        for (Draw d : draw) d.reset();
    }

    public void update(float delta, World world) {
        time += delta;
        for (IEntity u : updates) u.update(delta, world);
        for (Draw d : draw) d.update(delta, world);

    }

    public void render(float delta, SpriteBatch batch) {
        for (Draw d : draw) d.render(delta, batch);
    }

    public void onRemove(World world) {
        for (IEntity u : updates) u.onRemove(world);
    }
    public void onAdd(World world, float x, float y){
        for (IEntity u : updates) u.onAdd(world, x, y, this);
    };

    public void renderLine(float delta, ShapeRenderer shape){
        for (Draw d : draw) d.renderLine(delta, shape);
    };

    public void renderFilled(float delta, ShapeRenderer shape) {
        for (Draw d : draw) d.renderFilled(delta, shape);
    }


}
