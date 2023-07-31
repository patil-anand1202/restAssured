package Examples;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

/*
 * given() - content_type, set coockies, add auth, set headers info etc...
 * when() - get, post, delete, put ...
 * then() - validate status code, extracts resp code, resp headers , resp body etc..
 */


public class HTTPReqs {
	int id;
	@Test(priority=1)
	public void create_user() {
		
		HashMap<String, String> data = new HashMap();
		data.put("name", "user01");
		data.put("job", "no job");
		
		id = given()
		 .contentType("application/json")
		 .body(data)
		.when()
		 .post("https://reqres.in/api/users")
		 .jsonPath().getInt("id");
		 System.out.println("user id is "+id);
		
	}
	@Test(priority=2)
	public void get_user() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			//.body("id", equalTo(id))
			.log().body();
	}
	
	@Test(priority=3)
 public void update_user() {
	 HashMap update = new HashMap();
	 update.put("name", "updated");
	 update.put("job", "updated");
	 
	 given()
	 	.body(update)
	 	.contentType("application/json")
	 .when()
	 	.put("https://reqres.in/api/users/"+id)
	 .then()
	 	.statusCode(200)
	 	.log().all();
	 
 }
	@Test(priority = 4)
	public void delete() {
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
	
}
