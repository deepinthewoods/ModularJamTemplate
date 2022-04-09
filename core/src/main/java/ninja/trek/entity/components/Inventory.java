package ninja.trek.entity.components;

import ninja.trek.Observer;
import ninja.trek.entity.Entity;
import ninja.trek.entity.IEntity;

public class Inventory extends IEntity implements Observer {
    public static final int BOW = 0, SWORD = 1, STAFF = 3;
    public static final String[] names = {"bow", "sword", "staff"};
    public int equipped;
    public boolean hasAimWeapon() {
        return equipped == BOW;
    }

    @Override
    public void onNotify(Entity e, Event event, Object data) {
        if (event == Event.INVENTORY_SELECT){
            equipped = (Integer)data;
        }
    }
}
