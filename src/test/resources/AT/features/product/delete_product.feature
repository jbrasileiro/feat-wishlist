Feature: Delete product
  As a user
  I want to delete product info

Scenario Outline: Send request to delete a product
    Given product "<product_id>"
    When sending DELETE request to product "<product_id>"
    Then DELETE product response should be returned successfully
    And no product information should be available
    And no product information with id "<product_id>" should be available  

  Examples:
    | product_id 											| response_DELETE								| response_GET												| response_GET_id												|
    | 999999999999999999999999 				| \DELETE_example_response.json	| \GET_updated_example_response.json	| \GET_id_updated_example_response.json	|