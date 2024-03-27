Feature: Update client
  As a user
  I want to update client info

Scenario Outline: Send request to update a client
    Given client "<client_id>"
    When sending PUT request "<request>" to update client "<client_id>"
    Then client operation response "<response_PUT>" should be returned successfully
    And client information should be available response as "<response_GET>"
    And client information with id "<client_id>" should be available response as "<response_GET_id>"  

  Examples:
    | request            											| client_id 											| response_PUT												| response_GET															| response_GET_id																|
    | \PUT_client_example_request.json 	      | 000000000000000000000000 				| \PUT_client_example_response.json		| \GET_client_updated_example_response.json	| \GET_client_id_updated_example_response.json	|

    
Scenario Outline: Send invalid POST request to update client
    Given client "000000000000000000000000" 
    When sending PUT request "<request>" to update client "<client_id>"
    Then unsuccessful client operation response with <status> and "<response>" should be returned

  Examples:
    | client_id 											| request            								| status 															| response								  			|
    | 000000000000000000000000 				| ..\empty.json					 	        	| 400												  				| \PUT_client_empty_response.json	|