package api;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class GetTest {

    @BeforeAll
    public static void setUp() throws IOException {
        InputStream input = GetTest.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(input);
        RestAssured.baseURI = properties.getProperty("base.uri");
        RestAssured.port = Integer.parseInt(properties.getProperty("port"));
    }


    @ParameterizedTest
    @ValueSource(ints = {-1000, -100, -10, -9, -8, -7, -6, -5, -4, -3, -2 , -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 1000})
    @DisplayName("Validate GET Users status")
    void givenUrlWithPageParam_whenGetUsers_thenStatusCodeOk(int page) {
        given().param("page", page)
                .when().get("/users?/"+page)
                .then()
                    .statusCode(200);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    @DisplayName("Validate GET Users response for pages -1, 0, 1")
    void givenUrlWithPageParam_whenGetUsers_1_thenResponseCorrect(int page) {
            given().param("page", page)
                .when().get("/users?/"+page)
                .then()
                .statusCode(200)
                .assertThat()
                    .body("page", equalTo(page!=0 ? page : 1))
                    .body("per_page", equalTo(6))
                    .body("total", equalTo(12))
                    .body("total_pages", equalTo(2))
                    .body("data[0].id", equalTo(1))
                    .body("data[0].email", equalTo("george.bluth@reqres.in"))
                    .body("data[0].first_name", equalTo("George"))
                    .body("data[0].last_name", equalTo("Bluth"))
                    .body("data[0].avatar", notNullValue())
                    .body("data[1].id", equalTo(2))
                    .body("data[1].email", equalTo("janet.weaver@reqres.in"))
                    .body("data[1].first_name", equalTo("Janet"))
                    .body("data[1].last_name", equalTo("Weaver"))
                    .body("data[1].avatar", notNullValue())
                    .body("data[2].id", equalTo(3))
                    .body("data[2].email", equalTo("emma.wong@reqres.in"))
                    .body("data[2].first_name", equalTo("Emma"))
                    .body("data[2].last_name", equalTo("Wong"))
                    .body("data[2].avatar", notNullValue())
                    .body("data[3].id", equalTo(4))
                    .body("data[3].email", equalTo("eve.holt@reqres.in"))
                    .body("data[3].first_name", equalTo("Eve"))
                    .body("data[3].last_name", equalTo("Holt"))
                    .body("data[3].avatar", notNullValue())
                    .body("data[4].id", equalTo(5))
                    .body("data[4].email", equalTo("charles.morris@reqres.in"))
                    .body("data[4].first_name", equalTo("Charles"))
                    .body("data[4].last_name", equalTo("Morris"))
                    .body("data[4].avatar", notNullValue())
                    .body("data[5].id", equalTo(6))
                    .body("data[5].email", equalTo("tracey.ramos@reqres.in"))
                    .body("data[5].first_name", equalTo("Tracey"))
                    .body("data[5].last_name", equalTo("Ramos"))
                    .body("data[5].avatar", notNullValue())
                    .body("support.url", notNullValue())
                    .body("support.text", notNullValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    @DisplayName("Validate GET Users response for page 2")
    void givenUrlWithPageParam_whenGetUsers_thenResponseCorrect(int page) {
        given().param("page", page)
                .when().get("/users?/"+page)
                .then()
                .statusCode(200)
                .assertThat()
                .body("page", equalTo(page))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data[0].id", equalTo(7))
                .body("data[0].email", equalTo("michael.lawson@reqres.in"))
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"))
                .body("data[0].avatar", notNullValue())
                .body("data[1].id", equalTo(8))
                .body("data[1].email", equalTo("lindsay.ferguson@reqres.in"))
                .body("data[1].first_name", equalTo("Lindsay"))
                .body("data[1].last_name", equalTo("Ferguson"))
                .body("data[1].avatar", notNullValue())
                .body("data[2].id", equalTo(9))
                .body("data[2].email", equalTo("tobias.funke@reqres.in"))
                .body("data[2].first_name", equalTo("Tobias"))
                .body("data[2].last_name", equalTo("Funke"))
                .body("data[2].avatar", notNullValue())
                .body("data[3].id", equalTo(10))
                .body("data[3].email", equalTo("byron.fields@reqres.in"))
                .body("data[3].first_name", equalTo("Byron"))
                .body("data[3].last_name", equalTo("Fields"))
                .body("data[3].avatar", notNullValue())
                .body("data[4].id", equalTo(11))
                .body("data[4].email", equalTo("george.edwards@reqres.in"))
                .body("data[4].first_name", equalTo("George"))
                .body("data[4].last_name", equalTo("Edwards"))
                .body("data[4].avatar", notNullValue())
                .body("data[5].id", equalTo(12))
                .body("data[5].email", equalTo("rachel.howell@reqres.in"))
                .body("data[5].first_name", equalTo("Rachel"))
                .body("data[5].last_name", equalTo("Howell"))
                .body("data[5].avatar", notNullValue())
                .body("support.url", notNullValue())
                .body("support.text", notNullValue());
                

    }



    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }



}
