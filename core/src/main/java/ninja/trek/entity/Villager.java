package ninja.trek.entity;

import ninja.trek.entity.components.AnimationSelect;
import ninja.trek.entity.components.Box2dAABB;
import ninja.trek.entity.components.Box2dFloorContact;
import ninja.trek.entity.components.Box2dPlatformerMovement;
import ninja.trek.entity.components.InputData;
import ninja.trek.entity.components.Inventory;
import ninja.trek.entity.components.LayeredAnimation;

public class Villager extends Entity implements IFloorContact{

    public Box2dAABB physics = new Box2dAABB();
    public Box2dFloorContact floor = new Box2dFloorContact();
    public IEntity  animSelect = new AnimationSelect("elder")

    ;
    public Box2dPlatformerMovement platformerMovement = new Box2dPlatformerMovement();

    Draw layeredSprite = new LayeredAnimation();
    InputData inputData = new InputData();
    Inventory inventory = new Inventory();

    public Villager(){
        updates = new IEntity[]{platformerMovement, physics, floor, animSelect};
        draw = new Draw[]{layeredSprite};
        setConnections();
    }

    @Override
    public void setConnections() {
        animSelect.set(layeredSprite, physics, platformerMovement, inputData, inventory);
        layeredSprite.set(physics);
        platformerMovement.set(floor, physics, animSelect, inputData);

    }


    @Override
    public Box2dFloorContact getFloor() {
        return (Box2dFloorContact) floor;
    }
}
