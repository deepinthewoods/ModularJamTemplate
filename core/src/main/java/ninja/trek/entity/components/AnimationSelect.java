package ninja.trek.entity.components;

import com.badlogic.gdx.physics.box2d.World;

import ninja.trek.Graphics;
import ninja.trek.entity.Entity;
import ninja.trek.entity.IEntity;

public class AnimationSelect extends IEntity {
    private final int name;
    private transient LayeredAnimation layeredAnimation;
    private static int walk = Graphics.getHash("walk")
            , walkAim = Graphics.getHash("walkaim")
            , standAim = Graphics.getHash("standaim")
            , stand = Graphics.getHash("stand");
    private transient Box2dAABB physics;
    private transient Box2dPlatformerMovement movement;
    private transient InputData input;
    private Inventory inventory;

    public AnimationSelect(String name){
        this.name = Graphics.getHash(name);
    }

    @Override
    public void set(Object... other) {

        layeredAnimation = (LayeredAnimation)other[0];
        physics = (Box2dAABB) other[1];
        movement = (Box2dPlatformerMovement) other[2];
        input = (InputData) other[3];
        inventory = (Inventory) other[4];
    }

    public void change(){
        boolean touched = input.touched;
        boolean hasAimWeapon = inventory.hasAimWeapon();
        if (touched && hasAimWeapon){
            //Gdx.app.log(TAG, "touched + aim");
            if (movement.isWalking){
                layeredAnimation.setAnimation(name, walkAim, true);
//                itemAnim = Graphics.itemWalkAim[selectedItem];
            }
            else {
                layeredAnimation.setAnimation(name, standAim, true);
//                itemAnim = Graphics.itemStandAim[selectedItem];
            }
            return;
        }
        if (movement.isWalking){
            layeredAnimation.setAnimation(name, walk, true);
//            itemAnim = Graphics.itemWalk[selectedItem];
        }
        else{
            layeredAnimation.setAnimation(name, stand, true);
//            itemAnim = Graphics.itemStand[selectedItem];
        }
    }

    @Override
    public void onAdd(World world, float x, float y, Entity parent) {
        int nameHash = name;
        int animHash = Graphics.getHash("walk");
        layeredAnimation.setAnimation(nameHash, animHash, true);
    }
}
