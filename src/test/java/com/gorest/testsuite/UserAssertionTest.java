package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Kaushik Patel
 */
public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
//        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }

    // 2. Verify the if the name of id = 2272630 is equal to ”Bhaumik Varma”
    @Test
    public void test002() {

        response.body("[0].name", equalTo("Aasa Kaniyar II"));
    }

    // 3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {

        response.body("[2].name", equalTo("Smriti Pilla"));
    }


    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004() {

        response.body("name", hasItems("Chandraayan Chopra", "Dipendra Kakkar", "Gudakesa Guha"));
    }


    // 5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("find{it.id == 2272607}.email", equalTo("kakkar_dipendra@gottlieb.test"));

    }

    // 6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("find{it.status == 'active'}.name", equalTo("Gautam Kaul"));
    }


    // 7. Verify the Gender = male of user name is  "Chandraayan Chopra"

    @Test
    public void test007() {
        response.body("find{it.gender == 'male'}.name", equalTo("Chandraayan Chopra"));
    }
}
