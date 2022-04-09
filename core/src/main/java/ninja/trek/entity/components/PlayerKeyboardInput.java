package ninja.trek.entity.components;

import static ninja.trek.entity.components.Box2dPlatformerMovement.M_DOWN;
import static ninja.trek.entity.components.Box2dPlatformerMovement.M_LEFT;
import static ninja.trek.entity.components.Box2dPlatformerMovement.M_RIGHT;
import static ninja.trek.entity.components.Box2dPlatformerMovement.M_UP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.entity.IEntity;

public class PlayerKeyboardInput extends IEntity {

    private InputData input;

    @Override
    public void set(Object... other) {
        input = (InputData) other[0];
    }

    @Override
    public void update(float delta, World world) {
        input.move[M_UP] = Gdx.input.isKeyPressed(Input.Keys.W);
        input.move[M_DOWN] = Gdx.input.isKeyPressed(Input.Keys.S);
        input.move[M_LEFT] = Gdx.input.isKeyPressed(Input.Keys.A);
        input.move[M_RIGHT] = Gdx.input.isKeyPressed(Input.Keys.D);
        input.touched = Gdx.input.isTouched();

    }
}
