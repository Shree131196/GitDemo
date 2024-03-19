package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
//import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

//import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddObj;
import pojo.DataObj;
import resources.EnumResource;
import resources.Utils;

public class AddObject extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response resp;
	String strresponse;
	//String ID;
	
	public static Scenario scenarioObj;
	public static String ID;
	
	
	@Before
	public void setUp(Scenario scenario) {

	    scenarioObj = scenario;
	   
	}
	
	@Given("Add phone model payload")
	public void add_phone_model_payload() throws IOException {
		
		AddObj ab = new AddObj();
		ab.setName("Apple MacBook Pro 16");
		
		DataObj d = new DataObj();
		d.setYear(2019);
		d.setPrice(1849.99);
		d.setCPU_model("Intel Core i9");
		d.setHard_disk_size("1 TB");
		ab.setData(d);
		
		res = given().spec(requestSpecification()).body(ab);
	  
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String string2) {
		EnumResource enumres = EnumResource.valueOf(resource);
		System.out.println(enumres.getResource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		resp = res.when().post(enumres.getResource()).then().spec(resspec).extract()
		.response();
		System.out.println(resp.asPrettyString());
		strresponse = resp.asString();
		System.out.println(strresponse);

	}

	@Then("an object with name as {string} is added")
	public void an_object_with_name_as_is_added(String string) {
		
		JsonPath js = new JsonPath(strresponse);
		System.out.println(js.get(string).toString());
		//List<Object> names = js.getList("name");
		//System.out.println(names.get(6));
		scenarioObj.log("The Value of name is : "+js.getString("name"));
		ID = js.getString("id").toString();
		//return ID;
		System.out.println("The ID value is : " + ID);
		
	   
	}


}
