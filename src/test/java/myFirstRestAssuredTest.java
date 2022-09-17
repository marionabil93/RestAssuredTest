import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class myFirstRestAssuredTest {

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
    }


    @Test(priority = 1,description = "Create new Pet")
    public static void postRequest(){
        String json = "{\n" +
                "  \"id\": 601,\n" +
                "  \"name\": \"DonnaDodo\",\n" +
                "  \"photoUrls\": [\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "  ],\n" +
                "  \"status\": \"avaliable\"\n" +
                "}";
        RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
        System.out.println("The Record is Created successfully");
        System.out.println("------------------------------------");

    }

    @Test(priority = 2,description = "Update Existing Pet")
    public void putRequest(){
        String json = "{\n" +
                "  \"id\": 601,\n" +
                "  \n" +
                "  \"name\": \"dood2\",\n" +
                " \n" +
                "  \"status\": \"available\"\n" +
                "}";
        RestAssured.given()
                .header("Content-type", "application/json")
                .contentType("application/json")
                .body(json)
                .when()
                .put()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
          System.out.println("The Record is Updated successfully");
        System.out.println("------------------------------------");
    }

    @Test(priority = 3,description = "Find Existing pet")
    public void getRequest() {
        String penID = RestAssured.given().baseUri(baseURI)
                .contentType("application/json")
                .when().get("/601")
                .then()
                .extract().response().body().asString();
        System.out.println("PenID is " + penID);
        System.out.println("The Record is Found successfully");
        System.out.println("------------------------------------");
    }

    @Test(priority = 4,description = "Delete the pet")
    public void deleteRequest(){
        RestAssured.given().baseUri(baseURI)
                .contentType("application/json")
                .log().all().
                when().
                delete("/601").then().
                log().all().
                assertThat().
                statusCode(200);
        System.out.println("The Record is deleted successfully");
        System.out.println("------------------------------------");
    }

    @AfterTest
    public void tearDown(){
        System.out.println("------------------------------------");
        System.out.println("The 4 methods are Done");
        System.out.println("------------------------------------");
    }

}

