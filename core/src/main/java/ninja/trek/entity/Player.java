package ninja.trek.entity;

import ninja.trek.GameScreen;
import ninja.trek.entity.components.AnimationSelect;
import ninja.trek.entity.components.Box2dAABB;
import ninja.trek.entity.components.Box2dFloorContact;
import ninja.trek.entity.components.Box2dPlatformerMovement;
import ninja.trek.entity.components.CameraFollow;
import ninja.trek.entity.components.InputData;
import ninja.trek.entity.components.Inventory;
import ninja.trek.entity.components.LayeredAnimation;
import ninja.trek.entity.components.PlayerKeyboardInput;

public class Player extends Entity implements IFloorContact{

    public Box2dAABB physics = new Box2dAABB();
    public Box2dFloorContact floor = new Box2dFloorContact();
    public IEntity  animSelect = new AnimationSelect("player")
    ;
    public PlayerKeyboardInput playerMovement = new PlayerKeyboardInput();

    public Box2dPlatformerMovement platformerMovement = new Box2dPlatformerMovement();
    public CameraFollow camera = new CameraFollow();
    Draw layeredSprite = new LayeredAnimation();
    InputData inputData = new InputData();
    Inventory inventory = new Inventory();

    public Player(){
        updates = new IEntity[]{playerMovement, platformerMovement, physics, floor, animSelect, camera};
        draw = new Draw[]{layeredSprite};
        setConnections();
    }

    @Override
    public void setConnections() {
        animSelect.set(layeredSprite, physics, platformerMovement, inputData, inventory);
        layeredSprite.set(physics);
        platformerMovement.set(floor, physics, animSelect, inputData);
        playerMovement.set(inputData);
        camera.set(physics, floor);
        GameScreen.inventoryNotifier.add(inventory);
    }


    @Override
    public Box2dFloorContact getFloor() {
        return (Box2dFloorContact) floor;
    }
}
