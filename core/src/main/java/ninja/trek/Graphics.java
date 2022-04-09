package ninja.trek;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntMap;

import java.util.Iterator;

import ninja.trek.entity.Entity;

public class Graphics {

    private static final String TAG = "Graphics";
    public static IntMap<IntMap<Animation<Sprite>>> anims = new IntMap();

    static  {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("tiles.atlas"));
        createAnims(atlas);
        setDelta("walk", .05f);

    }

    private static void setDelta(String name, float delta) {
        int animHash = getHash(name);
        Iterator<IntMap.Entry<IntMap<Animation<Sprite>>>> animI = anims.iterator();
        while (animI.hasNext()){
            IntMap.Entry<IntMap<Animation<Sprite>>> entry = animI.next();
            IntMap<Animation<Sprite>> animations = entry.value;
            animations.get(animHash).setFrameDuration(delta);
        }
    }

    private static void createAnims(TextureAtlas atlas) {
        Array<TextureAtlas.AtlasRegion> regs = atlas.getRegions();
        for (TextureAtlas.AtlasRegion reg : regs){
            String[] str = reg.name.split("_");
            int nameHash = str[0].hashCode();
            int animHash = str[1].hashCode();
            if (!anims.containsKey(nameHash))
                anims.put(nameHash, new IntMap<>());
            IntMap<Animation<Sprite>> name = anims.get(nameHash);
            if (!name.containsKey(animHash)) {
                Array<Sprite> sprites = atlas.createSprites(reg.name);
                for (Sprite s : sprites){
                    s.setSize(Entity.SPRITE_SIZE, Entity.SPRITE_SIZE);
                }
                Animation<Sprite> anim = new Animation<>(.1f, sprites);
                anim.setPlayMode(Animation.PlayMode.LOOP);
                name.put(animHash, anim);
//                Gdx.app.log(TAG, "anim " + str[0] + " an " + str[1] + " h " + name.hashCode());
            }
        }
    }

    public static int getHash(String s){
        return s.hashCode();
    }

    public static Animation<Sprite> getAnim(int name, int animation) {
        Gdx.app.log(TAG, "anim " + name + " " + animation);
        return anims.get(name).get(animation);
    }
}
