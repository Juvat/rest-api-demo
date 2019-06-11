package pl.juvat.config;

import java.util.HashMap;

/**
 * @author lominskk on 2019-06-11
 */
public final class Config {

    private final HashMap<String, String> config;

    public Config() {
        config = YamlConfigLoader.getConfig();
    }

    public Object getProperty(final String name) {
        return config.get(name);
    }
}
