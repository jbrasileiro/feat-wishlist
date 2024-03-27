Feature: Update product
  As a user
  I want to update product info

Scenario Outline: Send request to update a product
    Given product "<product_id>"
    When sending PUT request "<request>" to update product "<product_id>"
    Then product operation response "<response_PUT>" should be returned successfully
    And product information should be available response as "<response_GET>"
    And product information with id "<product_id>" should be available response as "<response_GET_id>"  

  Examples:
    | request            								| product_id 											| response_PUT								| response_GET												| response_GET_id												|
    | \PUT_example_request.json 	      | 000000000000000000000000 				| \PUT_example_response.json	| \GET_updated_example_response.json	| \GET_id_updated_example_response.json	|

    
Scenario Outline: Send invalid POST request to update product
    Given product "000000000000000000000000" 
    When sending PUT request "<request>" to update product "<product_id>"
    Then unsuccessful product operation response with <status> and "<response>" should be returned

  Examples:
    | product_id 											| request            							| status 															| response								  |
    | 000000000000000000000000 				| ..\empty.json					  				| 400												  				| \PUT_empty_response.json	|