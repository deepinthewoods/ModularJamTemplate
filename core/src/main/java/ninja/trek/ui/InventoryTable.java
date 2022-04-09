package ninja.trek.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ninja.trek.GameScreen;
import ninja.trek.Observer;
import ninja.trek.entity.components.Inventory;

public class InventoryTable extends Table {
    public InventoryTable(Skin skin){
        for (int i = 0; i < Inventory.names.length; i++){
            TextButton btn = new TextButton(Inventory.names[i], skin);
            final int index = i;
            btn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    GameScreen.inventoryNotifier.notify(null, Observer.Event.INVENTORY_SELECT, index);
                    super.clicked(event, x, y);
                }
            });
            add(btn);
        }
        add(new Actor()).expandX().row();
        add(new Actor()).expandY();
        setFillParent(true);
    }
}
