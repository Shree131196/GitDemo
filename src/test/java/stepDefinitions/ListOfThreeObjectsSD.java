package stepDefinitions;


import static io.restassured.RestAssured.given;
//import static org.junit.Assert.assertEquals;
//import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.EnumResource;
import resources.Utils;



public class ListOfThreeObjectsSD extends Utils {

	ResponseSpecification resspec;
	RequestSpecification res;
	Response resp;
	String ID;
	String strresp;
	String presp;
	
	public static Scenario scenarioObj;
	
	@Before
	public void setUp(Scenario scenario) {

	    scenarioObj = scenario;
	   
	}

	@When("User calls {string} with {string} http request to the endpoint with IDs: <{int}>, <{int}>, <{int}>")
	public void user_calls_with_http_request_to_the_endpoint_with_i_ds(String resource, String string2, Integer string3, Integer string4, Integer string5)  throws IOException {

	EnumResource enumres = EnumResource.valueOf(resource);
	resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	resp = given().spec(requestSpecification()).queryParam("id", string3).queryParam("id", string4).queryParam("id", string5).when().get(enumres.getResource()).then().spec(resspec).extract()
	.response();
	System.out.println(resp.asPrettyString());
	System.out.println(resp.getStatusCode());
	strresp =  resp.asString();
	
	JsonPath jsonpath = new JsonPath(strresp);
	List<Object> ids = jsonpath.getList("id");
	System.out.println(ids);
	scenarioObj.log("The Value of ID is : "+jsonpath.getString("id"));
	 ID = jsonpath.getString("id");
	}
	
	@Then("I should receive a response for ID : <{int}>, <{int}>, <{int}>")
	public void i_should_receive_a_response_for_id(Integer int1, Integer int2, Integer int3) {
	if(ID=="int1") {
	presp = resp.then().extract().response().asPrettyString();
	System.out.println("The response for id " + int1 + " is " + presp );
	}
	else
		if(ID=="int2") {
			presp = resp.then().extract().response().asPrettyString();
			System.out.println("The response for id " + int2 + " is " + presp );
			}
		else
			if(ID=="int3") {
				presp = resp.then().extract().response().asPrettyString();
				System.out.println("The response for id " + int3 + " is " + presp );
				}
	}
		
	}


