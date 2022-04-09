package ninja.trek.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Draw {
    abstract void render(float delta, SpriteBatch batch);

    abstract void renderFilled(float delta, ShapeRenderer shape);

    abstract void renderLine(float delta, ShapeRenderer shape);
}
