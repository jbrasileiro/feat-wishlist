Feature: Add product
  As a user
  I want to add new product

Scenario Outline: Send POST request to add a product
    When sending POST request "<request>" to create a new product
    Then product operation response "<response_POST>" should be returned successfully
    And product information should be available response as "<response_GET>"

  Examples:
    | request            								| response_POST 											| response_GET								|
    | \POST_example_request.json	      | \POST_example_response.json 				| \GET_example_response.json	|

    
Scenario Outline: Send invalid POST request to add a product
    When sending POST request "<request>" to create a new product
    Then unsuccessful product operation response with <status> and "<response>" should be returned

  Examples:
    | request            						| status 															| response								  |
    | ..\empty.json					  			| 400												  				| \POST_empty_response.json	|