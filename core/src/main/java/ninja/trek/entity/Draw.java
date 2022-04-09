package ninja.trek.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Draw {
    public void set(Object... other){};

    public void render(float delta, SpriteBatch batch){};

    public void renderFilled(float delta, ShapeRenderer shape){};

    public void renderLine(float delta, ShapeRenderer shape){};

    public void update(float delta, World world){};

    public void reset() {

    }
}
