Feature: Add client
  As a user
  I want to add new client

Scenario Outline: Send POST request to add a client
    When sending POST request "<request>" to create a new client
    Then client operation response "<response_POST>" should be returned successfully
    And client information should be available response as "<response_GET>"

  Examples:
    | request            											| response_POST 														| response_GET											|
    | \POST_client_example_request.json	      | \POST_client_example_response.json 				| \GET_client_example_response.json	|

    
Scenario Outline: Send invalid POST request to add a client
    When sending POST request "<request>" to create a new client
    Then unsuccessful client operation response with <status> and "<response>" should be returned

  Examples:
    | request            								| status	| response								  |
    | ..\empty.json					  					| 400			| \POST_client_empty_response.json	|