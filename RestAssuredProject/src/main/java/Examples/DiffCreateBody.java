package Examples;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

/*
 *  diffrent ways of crating request body
	- using HashMap
	- using org.json 
	- using POJO class
	- using external json file
 */

public class DiffCreateBody {

	@Test()
	public void data_HashMap() {
		HashMap<String, String> body = new HashMap<String, String>();
		body.put("name", "newName");
		body.put("job", "sxasef");

		given().contentType("appllcation/json").body(body).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	public void data_jsonObj() {
		JSONObject data = new JSONObject();
		data.put("name", "test");
		data.put("job", "sss");

		given().contentType("application/json").body(data.toString()).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	public void data_POJO() {
		UserPOJO data = new UserPOJO();
		data.setName("POJOName");
		data.setJob("POJOJob");
		given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	public void use_ExtFile() throws FileNotFoundException {
		File file = new File(".\\User.json");
		FileReader fr = new FileReader(file);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		given().contentType("application/json").body(data.toString()).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}
}
