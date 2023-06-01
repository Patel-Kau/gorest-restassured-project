package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

/**
 * Created by Kaushik Patel
 */
public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
//        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/posts")
                .then().statusCode(200);
    }

    // 1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }
    // 2. Verify the if the title of id = 39295 is equal to "Sapiente aestivus valde aro vacuus saepe."
    @Test
    public void test002() {
        response.body("find{it.id == 39295}.title", equalTo("Sapiente aestivus valde aro vacuus saepe."));

    }

    // 3. Check the single user_id in the Array list (5522)
    @Test
    public void test003() {

        response.body("[6].id", equalTo(39292));
           }

    // 4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(39305, 39296, 39292));

    }

    // 5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
//    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
//    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
//    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
//    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
//    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
//    adflicto. Assentator umquam pel."”

    @Test
    public void test005() {
        response.body("find{it.id == 39305}.body", equalTo("Adhuc crebro odit. Tres tredecim cubo. Adfectus universe crustulum. Thorax altus varius. Defigo utor succurro. Denique enim aliqua. Similique torqueo cogo. Succurro triginta thymum. Delectatio desolo atrox. Damno expedita accendo. Nemo cursim tenetur. Utor tamen qui. Ambitus quos baiulus. Sollicito vicissitudo cras. Voluptas numquam sperno. Cultellus auris curriculum. Cogito sodalitas quia. Adaugeo blandior amplus. Tolero libero capio."));

    }

}
