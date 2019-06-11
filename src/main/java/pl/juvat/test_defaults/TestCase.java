package pl.juvat.test_defaults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pl.juvat.config.Config;
import pl.juvat.listeners.TestListener;

import java.lang.reflect.Method;

/**
 * @author lominskk on 2019-06-11
 */
@Slf4j
@Listeners(TestListener.class)
public abstract class TestCase {

    private final Config config = new Config();

    protected final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri((String) config.getProperty("baseUri"))
            .setBasePath((String) config.getProperty("basePath"))
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .setConfig(getConfig())
            .build();

    @BeforeMethod
    public void displayTestInfo(final Method method) {
        log.info("--- Test Class: {} ---", this.getClass().getSimpleName());
        log.info("*** Test Method: {} ***", method.getName());
    }

    private RestAssuredConfig getConfig() {
        return RestAssuredConfig
                .newConfig()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((type, s) -> {
                    final var objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JodaModule());
                    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                    return objectMapper;
                }));
    }
}
