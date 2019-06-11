package pl.juvat.config;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author lominskk on 2019-06-11
 */
@Slf4j
final class YamlConfigLoader {

    private static final Yaml YAML = new Yaml();

    static HashMap<String, String> getConfig() {
        try (final InputStream inputStream = YamlConfigLoader.class.getResourceAsStream("/config.yml")) {
            return YAML.load(inputStream);
        } catch (final IOException e) {
            log.error("Exception thrown when trying to create config", e);
        }
        return null;
    }

}
