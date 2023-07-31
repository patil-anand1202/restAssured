package Examples;

import io.restassured.path.json.JsonPath;
import java.util.List;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;

// This program will find the user name having highest rank  

public class GetName {
    public static void main(String[] args) {
        // Assuming you have made the API request and stored the JSON response in "response" variable
       // String jsonResponse = response.getBody().asString();
        
        // Parse the JSON response using JsonPath
      //  JsonPath jsonPath = new JsonPath(jsonResponse);
        
        String filePath = System.getProperty("user.dir")+"\\User.json";
		File jsonFile = new File(filePath);
		JsonPath jsonPath = new JsonPath(jsonFile);
        
        
        // Assuming your JSON has an array of students with "name" and "rank" properties
        List<String> studentNames = jsonPath.getList("data.first_name");
        List<Integer> studentRanks = jsonPath.getList("data.rank");
        System.out.println(">>>>>>>>>>> : "+studentNames);
        System.out.println(">>>>>>>>>>> : "+studentRanks);
        
        // Find the index of the highest rank using a custom comparator
        	Integer maxRank = Collections.min(studentRanks);
        	Integer indexOfMaxRank= studentRanks.indexOf(maxRank);
      
        System.out.println(">>>>>>>>>>> : "+indexOfMaxRank);
      
        // Retrieve the name of the student with the highest rank
        String studentNameWithHighestRank = studentNames.get(indexOfMaxRank);
        
        System.out.println("User with the highest rank: " + studentNameWithHighestRank +" having rank : "+maxRank);
    }
    
  
}
