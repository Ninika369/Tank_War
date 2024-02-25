package george.tank;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: George Sun
 * @Date: 2024-01-30-00:58
 * @Description: george.tank
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();
    //colliders in config file
    private static final String cs = (String)PropertyMgr.get("colliders");

    public void add(Collider c) {
        colliders.add(c);
    }

    public ColliderChain() {
        for (String str : cs.split(",")) {
            try {
                add((Collider) Class.forName(str).getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean collide(GameOject o1, GameOject o2) {
        for (int i = 0; i < colliders.size(); i++) {
           if (!colliders.get(i).collide(o1, o2)) {
               return false;
           }
        }
        return true;
    }
}
