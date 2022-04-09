package ninja.trek.entity.components;

import static ninja.trek.entity.components.Box2dAABB.verts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


import ninja.trek.entity.Draw;
import ninja.trek.entity.Floor;

public class ChainFloorPolygonRender extends Draw {
    private static final String TAG = "chain floor render";
    Vector2 third = new Vector2(), fourth = new Vector2(), start = new Vector2();
    private transient Box2dChainFloor floor;
    public Color color = new Color(Color.GRAY);

    @Override
    public void set(Object... other) {
        floor = (Box2dChainFloor) other[0];
    }

    @Override
    public void renderFilled(float delta, ShapeRenderer shape) {
        //shape.polygon(renderVerts);
        verts = floor.verts;
//        Gdx.app.log(TAG, "draw");

        for (int i = 0; i < verts.length-3; i++){
            if (verts[i].y > verts[i+1].y) {
                third.set(verts[i].x, verts[i+1].y);
                fourth.set(verts[i+1]);
            }
            else {
                third.set(verts[i+1].x, verts[i].y);
                fourth.set(verts[i]);
            }
//            if (i %2 == 0) continue;
            float w = verts[i+1].x - verts[i].x;
            float h = 100;
            start.set((verts[i].x), third.y -h);
//            Gdx.app.log(TAG, "rect " + start + w + ", " + h);
            shape.triangle(verts[i].x, verts[i].y, verts[i+1].x, verts[i+1].y, third.x, third.y, color, color, color);
            shape.rect(start.x, start.y, w, h, color, color, color, color);
        }
//            shape.line(verts[i], verts[i+1]);
//        shape.line(0, 0, 10, 10);

    }
}
