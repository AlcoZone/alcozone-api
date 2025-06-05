package com.alcozone.IntegrationTests;

import com.alcozone.application.service.RevisionService;
import com.alcozone.domain.models.Revision;
import com.google.firebase.auth.FirebaseAuthException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
public class RevisionRestListTest {

    @Inject
    RevisionService revisionService;

    @Test
    public void testRevisionListEndpoint() throws FirebaseAuthException {


        Revision saved1 = revisionService.saveRevision("Revisión Test 1");
        Revision saved2 = revisionService.saveRevision("Revisión Test 2");

        given()
                .header("Authorization", "Bearer " + "testtoken")
                .when()
                .get("/api/v1/revision/list")
                .then()
                .statusCode(200)
                .body("name", hasItems("Revisión Test 1", "Revisión Test 2"));
    }

    @Test
    public void testRevisionListEndpointNoAuth() throws FirebaseAuthException {

        Revision saved1 = revisionService.saveRevision("Revisión Test 1");
        Revision saved2 = revisionService.saveRevision("Revisión Test 2");

        given()
                .when()
                .get("/api/v1/revision/list")
                .then()
                .statusCode(401);
    }
}