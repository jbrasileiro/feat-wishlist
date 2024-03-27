Feature: Delete wishlist
  As a user
  I want to delete wishlist info

Scenario Outline: Send request to delete a wishlist
    Given wishlist "<wishlist_id>"
    When sending DELETE request to wishlist "<wishlist_id>"
    Then DELETE wishlist response should be returned successfully
    And no wishlist information should be available
    And no wishlist information with id "<wishlist_id>" should be available  

  Examples:
    | wishlist_id 											| response_DELETE								| response_GET												| response_GET_id												|
    | 000000000000000000000000 				| \DELETE_example_response.json	| \GET_updated_example_response.json	| \GET_id_updated_example_response.json	|
