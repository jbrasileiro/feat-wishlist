Feature: Update wishlist
  As a user
  I want to update wishlist info

Scenario Outline: Send request to update a wishlist
    Given client "999999999999999999999999"
    Given wishlist "999999999999999999999999" 
    When sending PUT request "<request>" to update wishlist "<wishlist_id>"
    Then wishlist operation response "<response_PUT>" should be returned successfully
    And wishlist information should be available response as "<response_GET>"
    And wishlist information with id "<wishlist_id>" should be available response as "<response_GET_id>"  

  Examples:
    | request            											| wishlist_id 											| response_PUT													| response_GET																| response_GET_id																|
    | \PUT_wishlist_example_request.json 	    | 999999999999999999999999 					| \PUT_wishlist_example_response.json		| \GET_wishlist_updated_example_response.json	| \GET_wishlist_id_updated_example_response.json	|

    
Scenario Outline: Send invalid POST request to update wishlist
    Given client "999999999999999999999999"
    Given wishlist "999999999999999999999999" 
    When sending PUT request "<request>" to update wishlist "<wishlist_id>"
    Then unsuccessful wishlist operation response with <status> and "<response>" should be returned

  Examples:
    | wishlist_id 											| request            											| status 															| response								  				|
    | 999999999999999999999999 					| ..\empty.json					 	        				| 400												  				| \PUT_wishlist_empty_response.json	|
    | 999999999999999999999999 					| \PUT_wishlist_w_client_request.json			| 400												  				| \PUT_wishlist_empty_response.json	|