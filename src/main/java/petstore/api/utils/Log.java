package petstore.api.utils;

import org.apache.log4j.Logger;

public class Log {

    public static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Log.class);

    public static Logger getInstance() {
        return LOG;
    }
}
