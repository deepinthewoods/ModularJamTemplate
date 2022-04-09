package ninja.trek;

import com.badlogic.gdx.utils.Array;

import ninja.trek.entity.Entity;

public class Subject {
    private static final String TAG = "subject";
    private Array<Observer> observers = new Array<Observer>();

    public void add(Observer obs){
        observers.add(obs);
    }
    public void remove(Observer obs){
        observers.removeValue(obs, true);
    }
    public void notify(Entity e, Observer.Event event, Object c){
        for (int i = 0; i < observers.size; i++){
            Observer obs = observers.get(i);
            obs.onNotify(e, event, c);

        }

    }


}
