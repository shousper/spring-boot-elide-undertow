package com.shousper.jsonapi;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
@ActiveProfiles("development")
public class ApiControllerTests {

    public static final String JSON_API_MEDIA_TYPE = "application/vnd.api+json";

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
        // By default JSON API expects this media type.
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setAccept(JSON_API_MEDIA_TYPE)
                .build();
        // By default JSON API responds with this media type.
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectContentType(JSON_API_MEDIA_TYPE)
                .build();
    }

    // @formatter:off

    @Test
    public void testAuthors() throws Exception {
        when().
            get("/api/author").
        then().
            statusCode(HttpStatus.OK.value()).
            body("data", hasSize(3)).
            body("data[0].id", is("1")).
            body("data[0].attributes.name", is("Bruce Wayne"));
    }

    // @formatter:on
}
