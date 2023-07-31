package Examples;

import java.io.File;
import java.util.List;

import io.restassured.path.json.JsonPath;

public class ConditionalJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = System.getProperty("user.dir")+"\\User.json";
		File jsonFile = new File(filePath);
		JsonPath jpath = new JsonPath(jsonFile);
		
		//Print First name of 1st object
		System.out.println("First name of 1st object : "+jpath.getString("data[0].first_name"));
		
		//Print gender  of 3rd object
		System.out.println("Gender gender of 3rd object : "+jpath.getString("data[2].gender"));
		
		//Get all the 1st names
		List<String> allFNames = jpath.getList("data.first_name");
		System.out.println("Get all the 1st names : "+allFNames);
		
		// Get all the last names of males
		List<String> allMales = jpath.getList("data.findAll{it.gender=='Female'}.last_name");
		System.out.println("Get all the last names of males : "+allMales);
		
		// Get all the email ids having ranks less then 10
		List<String> emailIds = jpath.getList("data.findAll{it.rank <=10}.email");
		System.out.println("Get all the email ids having ranks less then 10 : "+emailIds);
		
		//Get all the ranks having ranks less then 10
		List<String> ranks = jpath.getList("data.findAll{it.rank<=10}.rank");
		System.out.println("Get all the ranks having ranks less then 10 : "+ranks);
		
		
		System.out.println(">>>>>>>> "+jpath.getInt("data.size()"));
	
		//get the id of use 
		//int id = jpath.getInt("data.find{it.last_name=='Cowser'& it.first_name=='Shellie'}.id");
		System.out.println(" >>>> "+jpath.getInt("data.find{it.last_name=='Sharl'& it.first_name=='Shellie'}.id"));
	}

}
