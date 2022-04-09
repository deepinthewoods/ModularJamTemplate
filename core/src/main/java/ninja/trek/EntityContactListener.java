package ninja.trek;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import ninja.trek.entity.Floor;
import ninja.trek.entity.IFloor;
import ninja.trek.entity.IFloorContact;

public class EntityContactListener implements ContactListener {
    private static final String TAG = "contact listener";

    @Override
    public void beginContact(Contact contact) {
        Object ua = contact.getFixtureA().getUserData();
        Object ub = contact.getFixtureB().getUserData();
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        Gdx.app.log(TAG, "collide " + ua.getClass() + "  :  " + ub.getClass());

        if (fa.isSensor() && ua instanceof IFloorContact && ub instanceof IFloor){
            IFloorContact a = ((IFloorContact) ua);
            a.getFloor().groundContacts++;
            Gdx.app.log(TAG, "ground");
        } else if (fb.isSensor() && ub instanceof IFloorContact && ua instanceof IFloor){
            IFloorContact b = ((IFloorContact) ub);
            b.getFloor().groundContacts++;
            Gdx.app.log(TAG, "ground");
        }

        if (!fa.isSensor() && ua instanceof IFloorContact && ub instanceof IFloor){
            IFloorContact a = ((IFloorContact) ua);
            a.getFloor().normal.set(contact.getWorldManifold().getNormal()).rotate90(-1);
//            if (!a.isWalking) a.body.setLinearVelocity(0, a.body.linVelWorld.y);
        } else if (!fb.isSensor() && ub instanceof IFloorContact && ua instanceof IFloor){
            IFloorContact b = ((IFloorContact) ub);
            b.getFloor().normal.set(contact.getWorldManifold().getNormal()).rotate90(-1);
//            if (!b.isWalking) b.body.setLinearVelocity(0, b.body.linVelWorld.y);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object ua = contact.getFixtureA().getUserData();
        Object ub = contact.getFixtureB().getUserData();
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        Gdx.app.log(TAG, "collide " + ua.getClass() + "  :  " + ub.getClass());

        if (fa.isSensor() && ua instanceof IFloorContact && ub instanceof IFloor){
            IFloorContact a = ((IFloorContact) ua);
            a.getFloor().groundContacts--;
            Gdx.app.log(TAG, "ground-");
        } else if (fb.isSensor() && ub instanceof IFloorContact && ua instanceof IFloor){
            IFloorContact b = ((IFloorContact) ub);
            b.getFloor().groundContacts--;
            Gdx.app.log(TAG, "ground-");
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
