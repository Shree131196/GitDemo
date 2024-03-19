package resources;


import java.io.FileInputStream;
	//import java.io.FileNotFoundException;
	//import java.io.FileOutputStream;
	import java.io.IOException;
//import java.util.List;
//import java.io.PrintStream;
	import java.util.Properties;

//import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
//import io.restassured.RestAssured;
	import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.filter.log.RequestLoggingFilter;
	//import io.restassured.filter.log.ResponseLoggingFilter;
	import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

RequestSpecification req;
RequestSpecification req1;
ResponseSpecification resspec;
String respo;
String strresponse;
//String ID;

public static Scenario scenarioObj;
public static String ID;


public void setUp(Scenario scenario) {

    scenarioObj = scenario;
   
}

	

public RequestSpecification requestSpecification() throws IOException {
req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON).addHeader("User-Agent","PostmanRuntime/7.36.3").build();
	   return req;
	   }

public RequestSpecification requestSpecification1() throws IOException {
req1 = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl") + ID).setContentType(ContentType.JSON).addHeader("User-Agent","PostmanRuntime/7.36.3").build();
	   return req;
	   }

public ResponseSpecification responseSpecification() {
    return resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
}

//creating this method to get values from properties file
public String getGlobalValue(String key) throws IOException { 
Properties pro = new Properties();
FileInputStream fis = new FileInputStream("C:\\Users\\sriya.mishra\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
pro.load(fis);
return pro.getProperty(key);

}



//public String getIDVal(String r) {
//this.strresponse = r;
	
	//JsonPath js = new JsonPath(rf);
	//System.out.println(js.get(string).toString());
	//List<Object> names = js.getList("name");
	//System.out.println(names.get(6));
	//scenarioObj.log("The Value of name is : "+js.getString("name"));
	//ID = js.getString("id").toString();
	//return ID;
	//System.out.println("The ID value is : " + ID);
	
}





