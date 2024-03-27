Feature: Delete client
  As a user
  I want to delete client info

Scenario Outline: Send request to delete a client
    Given client "<client_id>"
    When sending DELETE request to client "<client_id>"
    Then DELETE client response should be returned successfully
    And no client information should be available
    And no client information with id "<client_id>" should be available  

  Examples:
    | client_id 											| response_DELETE								| response_GET												| response_GET_id												|
    | 999999999999999999999999 				| \DELETE_example_response.json	| \GET_updated_example_response.json	| \GET_id_updated_example_response.json	|
