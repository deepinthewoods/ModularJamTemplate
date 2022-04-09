package ninja.trek.entity;

import ninja.trek.entity.components.Box2dChainFloor;
import ninja.trek.entity.components.ChainFloorPolygonRender;

public class Floor extends Entity implements IFloor{

    public Box2dChainFloor physics = new Box2dChainFloor();
    Draw polyDraw = new ChainFloorPolygonRender();

    public Floor(){
        updates = new IEntity[]{physics};
        draw = new Draw[]{polyDraw};
        setConnections();
    }

    @Override
    public void setConnections() {
        polyDraw.set(physics);
    }

}
