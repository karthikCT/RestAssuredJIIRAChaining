package chain.multi.jira;

import static org.hamcrest.Matchers.containsString;

import org.testng.annotations.Test;

import base.jira.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetIssue extends BaseClass{
	
	
@Test(dependsOnMethods = "chain.multi.jira.CreateIssueWithAttach.creatAttachment")

public void getIssue() {
	

Response getAll = RestAssured
                 .given()
                 .contentType(ContentType.JSON)
                 .log().all()
                 .pathParam("id", id)
                 .get("{id}")
                 .then()
                 .statusCode(200)
                 .body("fields.description", containsString("Creating"))
                 .contentType(ContentType.JSON)
                 .extract()
                 .response();

System.out.println(getAll.statusCode());
getAll.prettyPrint();


}
}