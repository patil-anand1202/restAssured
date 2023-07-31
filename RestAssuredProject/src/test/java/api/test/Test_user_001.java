package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Test_user_001 {
	Faker faker;
	UserPOJO userPayload;
	Response resp;
	Logger logger;

	@BeforeClass
	public void dataSetUp() {
		faker = new Faker();
		userPayload = new UserPOJO();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void test_user_create_001() {
		logger.info("************** test_user_create_001 ****************");
		 resp = UserEndPoints.createUser(userPayload);
		resp.then().log().all();
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void test_user_get_001() {
		logger.info("************** test_user_get_001 ****************");
		//System.out.println(">>>>>>>>>>>>>> "+userPayload.getUsername());
		resp = UserEndPoints.getUser(userPayload.getUsername());
		//Json schema validation
		resp.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("userJsonSchema.json"))
		.log().all();
		Assert.assertEquals(resp.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void test_user_update_001() {
		logger.info("************** test_update_001 ****************");
		// generate new set of values to update
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		resp = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
		resp.then().log().all();
		
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void test_user_delete_001() {
		logger.info("************** test_delete_001 ****************");
		resp = UserEndPoints.deleteUser(this.userPayload.getUsername());
		resp.then().log().all();
		
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
}
