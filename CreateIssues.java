package chain.multi.jira;

import static org.hamcrest.Matchers.containsString;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.jira.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateIssues extends BaseClass{
	
	@DataProvider(name = "Fetchdata")
	public String[] setData() {
		// row -> No of data set
		// column -> No of data in each data set
		String[] data = new String[2];
		data[0] = "./Data/CreateIssue1.json";
		data[1] = "./data/CreateIssue1.json";
		return data;
	}
	//@Test(dataProvider = "Fetchdata", dependsOnMethods = "chain.multi.jira.GetAll.getAllIssues")
	@Test(dataProvider = "Fetchdata")
	public void createIssue(String filepath) {
		
		
		Response createIssue = RestAssured.given()
				.contentType(ContentType.JSON)
				.log().all()
				.body(new File(filepath))
				.post()
				.then()
				.statusCode(201)
				.contentType(ContentType.JSON)
				.extract()
				.response();
		
		System.out.println(createIssue.statusCode()); 
		createIssue.prettyPrint();
		
		id = createIssue.jsonPath().get("id");
		System.out.println(id);
		System.out.println(createIssue.jsonPath().get("description"));
	}
	

}
