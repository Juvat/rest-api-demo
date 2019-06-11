package pl.juvat.tests;

import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.joda.time.DateTime;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.juvat.data_provider.XMLTestConfig;
import pl.juvat.data_provider.XMLTestDataProvider;
import pl.juvat.entities.Beer;
import pl.juvat.test_defaults.TestCase;
import pl.juvat.utils.DateUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.jodatime.api.Assertions.assertThat;

/**
 * @author lominskk on 2019-06-11
 */
@Owner("Kamil Lominski")
public class BeersTest extends TestCase {

    @Test(groups = "smoke")
    public void shouldReturnAllBeers() {
        // @formatter:off
        given(spec)
        .when()
            .get()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(matchesJsonSchemaInClasspath("schemas/beer-schema.json"));
        // @formatter:on
    }

    @Test(groups = "regression", dataProvider = "XmlDataProvider", dataProviderClass = XMLTestDataProvider.class)
    public void shouldReturnBeersBrewedBefore(final XMLTestConfig config) {
        final var date = new DateTime(config.get("date"));
        // @formatter:off
        final var beers =
        given(spec)
            .param("brewed_before", DateUtils.convertDateFormat(date))
        .when()
            .get()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(matchesJsonSchemaInClasspath("schemas/beer-schema.json"))
            .extract()
            .as(Beer[].class);
        // @formatter:on
        assertThat(beers).allSatisfy(beer -> assertThat(beer.getFirstBrewed()).isBefore(date));
    }

    @Parameters("abv")
    @Test(groups = {"smoke", "regression"})
    public void shouldReturnBeersAboveGivenAbv(final String abv) {
        // @formatter:off
        final var beers =
        given(spec)
            .param("abv_gt", abv)
        .when()
            .get()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(matchesJsonSchemaInClasspath("schemas/beer-schema.json"))
            .extract()
            .as(Beer[].class);
        // @formatter:on
        assertThat(beers).allSatisfy(beer -> assertThat(beer.getAbv()).isGreaterThan(6));
    }

    @Test(groups = "smoke", dataProvider = "XmlDataProvider", dataProviderClass = XMLTestDataProvider.class)
    public void shouldReturnPaginatedResults(final XMLTestConfig config) {
        final var page = config.get("page");
        final var size = config.get("size");
        // @formatter:off
        final var beers =
        given(spec)
            .params("page", page, "per_page", size)
        .when()
            .get()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(matchesJsonSchemaInClasspath("schemas/beer-schema.json"))
            .extract()
            .as(Beer[].class);
        // @formatter:on
        assertThat(beers).hasSize(5);
    }

}
