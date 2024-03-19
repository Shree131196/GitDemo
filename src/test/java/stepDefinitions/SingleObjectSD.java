package stepDefinitions;

import resources.Utils;
import static io.restassured.RestAssured.given;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
//import static org.testng.Assert.assertEquals;
//import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//simport org.hamcrest.MatcherAssert;
//import org.hamcrest.MatcherAssert;
import org.junit.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

//import io.cucumber.java.en.Given;

//import java.io.IOException;

//import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.EnumResource;


public class SingleObjectSD extends Utils {
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response resp;
	String ID;
	String strresp;
	String presp;
	JsonPath jsonpath;

public static Scenario scenarioObj;
	
	@Before
	public void setUp(Scenario scenario) {

	    scenarioObj = scenario;
	   
	}

	@When("user calls {string} with {string} http request to the endpoint with the ID as <{int}>")
	public void user_calls_with_http_request_to_the_endpoint_with_the_id_as(String resource, String string2, Integer int1) throws IOException
	 {
	EnumResource enumres = EnumResource.valueOf(resource);
	resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	resp = given().spec(requestSpecification()).queryParam("id", int1).when().get(enumres.getResource()).then().spec(resspec).extract()
.response();

	System.out.println(resp.asPrettyString());
	strresp =  resp.asString();
	//Utils u = new Utils();
	//u.getActualVal(strresp);
	 jsonpath = new JsonPath(strresp);
	List<Object> ids = jsonpath.getList("id");
	System.out.println(ids);
	scenarioObj.log("The Value of ID is : "+jsonpath.getString("id"));
	ID = jsonpath.getString("id");
	}

	@Then("extract ID present in response and validate it with actual ID")
	public void extract_id_present_in_response_and_validate_it_with_actual_id()  {
	
		ArrayList<String> al = new ArrayList<String>();
		al.add("10");
		 String[] arr = new String[al.size()];
	        arr = al.toArray(arr);
		
		
		String expectedValue = arr.toString();
		String actualValue =  ID;
		Assert.assertEquals(expectedValue, actualValue);
		//MatcherAssert.assertThat(ID, is(equals("10")));
		
	}
	}







	
	


