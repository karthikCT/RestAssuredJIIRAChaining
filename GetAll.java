package chain.multi.jira;

import org.testng.annotations.Test;

import base.jira.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAll extends BaseClass{
	
	//@Test(dependsOnMethods = "chain.multi.jira.CreateIssueWithAttach.creatAttachment")
	@Test
public void getAllIssues() {
	
RestAssured.baseURI = "https://api-may2020.atlassian.net/rest/api/2/search";
RestAssured.authentication = RestAssured
.preemptive()
.basic("hari.radhakrishnan@testleaf.com", "kT7KIWoNaaXTU0e9wgIa2EE2");

Response getAll = RestAssured
                 .given()
                 .contentType(ContentType.JSON)
                 .log().all()
                 .queryParam("JQL", "project=”RS”")
                 .queryParam("maxResults", 20)
                // .queryParam("JQL", "project=”JIRA”")
                 //.pathParam("JQL", "project=”JIRA”")
                 .get()
                 .then()
                 .statusCode(200)
                 .contentType(ContentType.JSON)
                 .extract()
                 .response();

System.out.println(getAll.statusCode());
getAll.prettyPrint();


}
}