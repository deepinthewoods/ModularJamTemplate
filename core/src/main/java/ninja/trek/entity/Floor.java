package ninja.trek.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.entity.components.Box2dAABB;
import ninja.trek.entity.components.Box2dChainFloor;
import ninja.trek.entity.components.Box2dFloorContact;
import ninja.trek.entity.components.LayeredSprite;

public class FloorEntity extends Entity{

    public Box2dChainFloor physics = new Box2dChainFloor();
//    IEntity floor = new Box2dFloorContact();
//    IEntity animSelect = new AnimationSelect();
    IEntity[] updates = {physics};

//    Draw layeredSprite = new LayeredSprite();
    Draw[] draw = {};
    @Override
    public void onAdd(World world, float x, float y){

        //layeredSprite.set(physics);
        for (IEntity u : updates) u.onAdd(world, x, y);
//        for (Draw d : draw) d.onAdd(world, x, y);
    }
    public FloorEntity(){
        reset();
    }

    @Override
    public void update(float delta, World world) {
        super.update(delta, world);
        for (IEntity u : updates) u.update(delta, world);
        for (Draw d : draw) d.update(delta, world);
//        Gdx.app.log("entity ", "update " + time);
//        if (time > timeTarget ) markedForRemoval = true;
    }

    @Override
    public void reset() {
        super.reset();
        for (IEntity u : updates) u.reset();
        for (Draw d : draw) d.reset();
    }

    @Override
    public void onRemove(World world) {
        super.onRemove(world);
        for (IEntity u : updates) u.onRemove(world);

    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        for (Draw d : draw) d.render(delta, batch);
    }

    @Override
    public void renderLine(float delta, ShapeRenderer shape) {
        for (Draw d : draw) d.renderLine(delta, shape);
    }

    @Override
    public void renderFilled(float delta, ShapeRenderer shape) {
        for (Draw d : draw) d.renderFilled(delta, shape);
    }
}
