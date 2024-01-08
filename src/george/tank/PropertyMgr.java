package george.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: George Sun
 * @Date: 2024-01-08-13:27
 * @Description: george.tank
 */
public class PropertyMgr {
    static Properties props = new Properties();
    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getInt(String key) {
        if (props == null)
            return null;
        return Integer.parseInt((String)get(key));
    }

    public static Object get(String key) {
        if (props == null)
            return null;
        return props.get(key);
    }
}
