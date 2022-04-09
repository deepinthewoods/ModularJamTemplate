package ninja.trek.entity;

import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.Graphics;
import ninja.trek.entity.components.LayeredSprite;

public class AnimationSelect extends IEntity {
    private transient LayeredSprite layeredAnimation;

    @Override
    public void set(Object... other) {
        layeredAnimation = (LayeredSprite)other[0];
    }

    @Override
    public void reset() {

    }

    @Override
    public void update(float delta, World world) {

    }

    @Override
    public void onRemove(World world) {

    }

    @Override
    public void onAdd(World world, float x, float y) {
        int nameHash = Graphics.getHash("player");
        int animHash = Graphics.getHash("walk");
        layeredAnimation.setAnimation(nameHash, animHash, true);
    }
}
