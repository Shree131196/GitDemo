

@tag

Feature: Validating Phone API

 
  Scenario: Verifying if phone model is successfully extracted using GetObjectAPI
  Given I have a REST API endpoint for getting list of phone models
  When user calls "GetObjectAPI" with "Get" http request and user can see a list of all the phone models
  Then API call is success and status is 200
   
  Scenario: Verifying if phone details are successfully extracted for a single ID using GetObjectAPI
  Given I have a REST API endpoint for getting list of phone models
  When user calls "GetObjectAPI" with "Get" http request to the endpoint with the ID as <10>
  #Then extract ID present in response and validate it with actual ID
  
  Scenario:  Verifying if phone model with id is successfully extracted using GetObjectAPI
  Given I have a REST API endpoint for getting list of phone models
  When User calls "GetObjectAPI" with "Get" http request to the endpoint with IDs: <4>, <5>, <10>
  Then I should receive a response for ID : <4>, <5>, <10>
  
  Scenario: Add a phone model to the list
  Given Add phone model payload
  When User calls "AddObjectAPI" with "Post" http request 
  Then an object with name as "name" is added
  
  Scenario: Update an object in the list
  Given user provides id as we get in AddObject which is to be updated
  When User calls "PutObjectAPI" with "Put" http request to the endpoint
  Then user should see the updated value of price as "2049.99"
  
  #Scenario : Delete an object from the list
  #Given
  #When
  #Then

   
       
  
 