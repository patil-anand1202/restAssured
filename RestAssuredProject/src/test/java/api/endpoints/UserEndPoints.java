package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(UserPOJO payload) {
	Response response =  given()
							.accept(ContentType.JSON)
 					    	.contentType(ContentType.JSON)
 					    	.body(payload)
 					    .when()
 					    	.post(Routes.postUrl);
	return response;
	}

	public static Response getUser(String userName) {
	Response response = given()
							.accept(ContentType.JSON)
							.pathParam("username", userName)
						.when()
							.get(Routes.getUrl);
	return response;
	}
	
	public static Response updateUser(UserPOJO payload, String userName) {
		Response response = given()
								. accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("username", userName)
								.body(payload)
							.when()
								.put(Routes.putUrl);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username", userName)
							.when()
								.delete(Routes.deleteUrl);
		return response;
	}
	
}
