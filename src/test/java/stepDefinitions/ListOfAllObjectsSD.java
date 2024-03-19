package stepDefinitions;


import static io.restassured.RestAssured.given;
	import static org.junit.Assert.assertEquals;
	//import static org.hamcrest.Matchers.*;

	//import java.io.FileNotFoundException;
	import java.io.IOException;

	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	//import io.restassured.RestAssured;
	//import io.restassured.builder.RequestSpecBuilder;
	import io.restassured.builder.ResponseSpecBuilder;
	import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import io.restassured.specification.ResponseSpecification;
	import resources.EnumResource;
	import resources.Utils;

public class ListOfAllObjectsSD extends Utils {  

ResponseSpecification resspec;
RequestSpecification res;
Response resp;
String strresponse;

@Given("I have a REST API endpoint for getting list of phone models")
public void i_have_a_rest_api_endpoint_for_getting_list_of_phone_models() throws IOException {
res = given().spec(requestSpecification()); 
	}


@When("user calls {string} with {string} http request and user can see a list of all the phone models")
public void user_calls_with_http_request(String resource, String string2) {
EnumResource enumres = EnumResource.valueOf(resource);
System.out.println(enumres.getResource());

resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	resp = res.when().get(enumres.getResource()).then().spec(resspec).extract()
	.response();
	System.out.println(resp.asPrettyString());
	 strresponse = resp.asString();
	System.out.println(strresponse);
	
	}


@Then("API call is success and status is 200")
public void api_call_is_success_and_status_is_200() {
assertEquals(resp.getStatusCode(),200);
System.out.println(resp.getStatusCode());
}


//@Then("{string} in response body is {string}")
//public void in_response_body_is(String Actualval, String Expectedval) {
//JsonPath js = new JsonPath(strresponse);
//assertEquals(js.get(Actualval).toString(),Expectedval);
//System.out.println(js.get(Expectedval).toString());


} 
	
