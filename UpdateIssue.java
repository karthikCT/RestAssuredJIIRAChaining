package chain.multi.jira;

import java.io.File;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.jira.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateIssue extends BaseClass{
	@DataProvider(name = "Fetchdata")
	public String[] setData() {
		// row -> No of data set
		// column -> No of data in each data set
		String[] data = new String[1];
		data[0] = "./Data/UpdateIssue1.json";
		//data[1] = "./data/UpdateIssue2.json";
		return data;
	}
	
	
	@Test(dataProvider = "Fetchdata", dependsOnMethods = "chain.multi.jira.GetIssue.getIssue")
	public void updateIssue(String filepath) {
	
		Response updateIssue = RestAssured
				.given()				
				.contentType(ContentType.JSON)
				.log().all()
				.body(new File(filepath))
				.pathParams("id", id)
				.put("{id}")
				.then()
				.statusCode(204)
				.contentType(ContentType.JSON)
				.extract()
				.response();
		
		System.out.println(updateIssue.statusCode()); 
		updateIssue.prettyPrint();
	}

}
