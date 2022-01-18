package chain.multi.jira;

import java.io.File;
import static org.hamcrest.Matchers.containsString;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.jira.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateIssueWithAttach extends BaseClass{
	
	
	@DataProvider(name = "Fetchdata")
	public String[] setData() {
		// row -> No of data set
		// column -> No of data in each data set
		String[] data = new String[1];
		data[0] = "./Data/console.log";
		//data[1] = "./data/UpdateIssue2.json";
		return data;
	}
	
	@Test(dataProvider = "Fetchdata", dependsOnMethods = "chain.multi.jira.CreateIssues.createIssue")
	public void creatAttachment(String filepath) {
	
		Response create = RestAssured.given()
				.contentType(ContentType.JSON)
				.log().all()
				.header("Content-Type", "multipart/form-data")
		       .header("X-Atlassian-Token", "no-check")
		       .multiPart("file", new File(filepath))
				.basePath(id+"/attachments")
				.post()
				.then()
				.statusCode(200)
				.body("filename", Matchers.contains("console.log"))			
				.extract()
				.response();

	System.out.println(create.statusCode());
	create.prettyPrint();
	}

}
