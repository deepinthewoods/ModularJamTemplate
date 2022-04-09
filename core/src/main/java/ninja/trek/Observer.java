package ninja.trek;

import ninja.trek.entity.Entity;

public interface Observer {
    public enum Event {
        SCREEN_TOUCH, INVENTORY_SELECT
    };
    public void onNotify(Entity e, Event event, Object data);
}
