Feature: Add wishlist
  As a user
  I want to add new wishlist

Scenario Outline: Send POST request to add a wishlist
		Given client "999999999999999999999999"
    When sending POST request "<request>" to create a new wishlist
    Then wishlist operation response "<response_POST>" should be returned successfully
    And wishlist information should be available response as "<response_GET>"

  Examples:
    | request            													| response_POST 																	| response_GET													|
    | \POST_wishlist_example_request.json	      	| \POST_wishlist_example_response.json 						| \GET_wishlist_example_response.json		|

    
Scenario Outline: Send invalid POST request to add a wishlist
    When sending POST request "<request>" to create a new wishlist
    Then unsuccessful wishlist operation response with <status> and "<response>" should be returned

  Examples:
    | request            								| status	| response								  |
    | ..\empty.json					  					| 400			| \POST_wishlist_empty_response.json	|