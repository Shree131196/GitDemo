package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddObj;
import pojo.DataObj;
import resources.EnumResource;
import resources.Utils;

public class UpdateObject extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response resp;
	String strresponse;
	String presp;
	
public static Scenario scenarioObj;
public static String ID;
	
	@Before
	public void setUp(Scenario scenario) {

	    scenarioObj = scenario;
	   
	}
	
	public void UsageID() {
		
		String ID = AddObject.ID;
	}

	//@Given("user provides id as <{int}> which is to be updated")
	//public void user_provides_id_as_which_is_to_be_updated(Integer int1) throws IOException {
		
		@Given("user provides id as we get in AddObject which is to be updated")
		public void user_provides_id_as_we_get_in_AddObject_which_is_to_be_updated() throws IOException {
		
		AddObj ab = new AddObj();
		
		DataObj d = new DataObj();
		d.setPrice(2049.99);
		
		
		//getIDVal();
		res = given().spec(requestSpecification1()).proxy("web-proxy.cv.hp.com", 8080, "http").relaxedHTTPSValidation()
			    .header("Content-Type", "application/json").body(ab.toString());
		//res = given().spec(requestSpecification()).queryParam("id", String.valueOf(ID)).body(ab);
	}

	@When("User calls {string} with {string} http request to the endpoint")
	public void user_calls_with_http_request_to_the_endpoint(String resource, String string2) {
		
		EnumResource enumres = EnumResource.valueOf(resource);
		System.out.println(enumres.getResource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		resp = ((Validatable<ValidatableResponse, Response>) ((RestAssured) res.when().put(enumres.getResource())).preemptive())
			    .then().assertThat().statusCode(200).spec(resspec).extract()
		.response();
		System.out.println(resp.asPrettyString());
		strresponse = resp.asString();
		System.out.println(strresponse);
	  
	}
	
	//Response updateResponse = given().proxy("web-proxy.cv.hp.com", 8080, "http").relaxedHTTPSValidation()
		  //  .header("Content-Type", "application/json").body(request.toString()).log().all().when().log().all()
		    
		//has context menu
	
	@Then("user should see the updated value of price as <{double}>")
	public void user_should_see_the_updated_value_of_price_as(String double1) {
		JsonPath js = new JsonPath(strresponse);
		System.out.println(js.get(double1).toString());
		List<Object> names = js.getList("name");
		System.out.println(names.get(0));
		scenarioObj.log("The Value of name is : "+js.getString("name"));
		ID = js.getString("id").toString();
		System.out.println("The ID value is : " + ID);
	}

	
		
	 
	
}

