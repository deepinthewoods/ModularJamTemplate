package ninja.trek.entity.components;

import static ninja.trek.entity.Entity.SPRITE_OFFSET;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.Graphics;
import ninja.trek.entity.Draw;

public class LayeredSprite extends Draw {
    private transient Animation<Sprite> anim;
    private float animTime;
    private transient Box2dAABB physics;

    @Override
    public void set(Object... other) {
        physics = (Box2dAABB) other[0];
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        Sprite s = anim.getKeyFrame(animTime);
        s.setPosition(physics.position.x - SPRITE_OFFSET, physics.position.y - SPRITE_OFFSET);
        s.draw(batch);
    }
    public void setAnimation(int name, int animation, boolean resetTime) {
        Animation<Sprite> anim = Graphics.getAnim(name, animation);
        this.anim = anim;
        if (resetTime) animTime = 0f;
    }

    @Override
    public void update(float delta, World world) {
        animTime += delta;
    }

    @Override
    public void reset() {
        animTime = 0f;
    }
}
