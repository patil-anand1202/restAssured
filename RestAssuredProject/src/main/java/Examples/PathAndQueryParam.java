package Examples;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class PathAndQueryParam {

	//@Test
public void prams() {
	given()
	.pathParams("mypath","users")
	.queryParam("page", "2")
	.queryParam("id", "2")
	.when()
		.get("https://reqres.in/api/{mypath}")
	.then()
		.statusCode(200)
		.log().all();
	}
	//@Test
	public void getCoockies() {
		given()
		.when()
		.get("https://www.google.com")
		.then()
		.cookie("AEC")  // validates if the given cookie is there are not
		.and()
		.header("Content-Type", "text/html; charset=ISO-8859-1") // header validation
		
		.log().all();
	}
	
	@Test
	public void getResp() {
		Response resp = given().get("https://www.google.com");
		// System.out.println("Display all the cookies >>>>>>>>>>>> " + resp.getCookies());
		 System.out.println("Display all the resp header >>>>>>>>>>>> " + resp.headers()); 
		 System.out.println("********************************* ");
	}
	
	
}
