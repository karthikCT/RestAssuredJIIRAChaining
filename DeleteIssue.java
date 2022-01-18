package chain.multi.jira;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.jira.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteIssue extends BaseClass{
	
	@Test(dependsOnMethods = "chain.multi.jira.UpdateIssue.updateIssue")
	public void deleteIssues() {
		Response createIssue = RestAssured.given()
				.contentType(ContentType.JSON)
				.log().all()
				.pathParam("id", id)
				.delete("{id}")
				.then()
				.statusCode(204)
				.contentType(ContentType.JSON)
				.extract()
				.response();		
		System.out.println(createIssue.statusCode()); 
		createIssue.prettyPrint();
		
	}
	

}
