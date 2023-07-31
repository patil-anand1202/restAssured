package api.endpoints;


//https://petstore.swagger.io/v2/
public class Routes {
	public static String baseUrl= "https://petstore.swagger.io/v2";
	
	//User module
	public static String postUrl = baseUrl+"/user";
	public static String getUrl = baseUrl+"/user/{username}";
	public static String putUrl = baseUrl+"/user/{username}";
	public static String deleteUrl = baseUrl+"/user/{username}";
	
}
