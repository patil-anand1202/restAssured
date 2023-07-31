package Examples;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class Authentications {
@Test(priority=1)
public void basicAuth() {
	given()
		.auth().basic("postman", "password")
	.when()
		.get("https://postman-echo.com/basic-auth")
	.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
}

@Test(priority=2)
public void digestAuth() {
	given()
		.auth().digest("postman", "password")
	.when()
		.get("https://postman-echo.com/basic-auth")
	.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
}

@Test(priority=3)
public void preemptiveAuth() {
	given()
		.auth().preemptive().basic("postman", "password")
	.when()
		.get("https://postman-echo.com/basic-auth")
	.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
}
@Test()
public void fackerDemo() {
	Faker facker = new Faker();
}

}
