package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Test_user_DD {
	UserPOJO userPayload;
	Response resp;
	Faker faker;

	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void test_user_create(String userID, String username, String firstName, String lastName, String email,
			String password, String phone) {
		userPayload = new UserPOJO();
		faker = new Faker();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(username);
		userPayload.setEmail(email);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);

		resp = UserEndPoints.createUser(userPayload);
		resp.then().log().all();

		Assert.assertEquals(resp.statusCode(), 200);

	}

	@Test(priority=2,dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void teset_user_delete(String userName) {
		
		userPayload = new UserPOJO();
		userPayload.setUsername(userName);
		
		resp = UserEndPoints.deleteUser(userName);
		resp.then().log().all();
		
		Assert.assertEquals(resp.statusCode(), 200);
			
	}
}
