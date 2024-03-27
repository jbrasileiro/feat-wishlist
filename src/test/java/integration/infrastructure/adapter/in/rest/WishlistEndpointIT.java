package integration.infrastructure.adapter.in.rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wishlist.domain.model.Product;
import org.wishlist.domain.model.Wishlist;

import support.ITTemplate;
import support.ResponseEntityAssert;

class WishlistEndpointIT extends ITTemplate {

	private static final String ADD_PRODUCT_URI = "wishlist/000000000000000000000000/product";
	private static final String JSON = "classpath:IT/json/wishlist/";

	@Test
	void addProductWithWishlistMaxProduct() {
		addWishlistWithMaxProduct();
		saveProduct("000000000000000000000021");
		String expectedResponse = readJSON(JSON + "/PATCH_wishlist_max_product_response.json");
		ResponseEntity<String> response = getRestTemplate().requestPATCH(toURI(ADD_PRODUCT_URI), List.of("000000000000000000000021"));
		ResponseEntityAssert.assertThat(response)
			.hasStatus(HttpStatus.UNPROCESSABLE_ENTITY)
			.responseBody(expectedResponse);
	}
	
	@Test
	void addNonexistentProduct() {
		addWishlistWithEmptyProduct();
		String expectedResponse = readJSON(JSON + "/PATCH_wishlist_nonexistent_product_response.json");
		ResponseEntity<String> response = getRestTemplate().requestPATCH(toURI(ADD_PRODUCT_URI), List.of("999999999999999999999999"));
		ResponseEntityAssert.assertThat(response)
			.hasStatus(HttpStatus.UNPROCESSABLE_ENTITY)
			.responseBody(expectedResponse);
	}
	
	@Test
	void addEmptyProduct() {
		addWishlistWithEmptyProduct();
		String expectedResponse = readJSON(JSON + "/PATCH_wishlist_empty_product_response.json");
		ResponseEntity<String> response = getRestTemplate().requestPATCH(toURI(ADD_PRODUCT_URI), Collections.emptySet());
		ResponseEntityAssert.assertThat(response)
			.hasStatus(HttpStatus.UNPROCESSABLE_ENTITY)
			.responseBody(expectedResponse);
	}

	private void addWishlistWithEmptyProduct() {
		getMongoTemplate().save(Wishlist.builder()
				.id(new ObjectId("000000000000000000000000"))
				.nome("wishlist #000000000000000000000000")
				.build());
	}
	
	private void addWishlistWithMaxProduct() {
		Set<Product> products = addProduct(20);
		getMongoTemplate().save(Wishlist.builder()
			.id(new ObjectId("000000000000000000000000"))
			.nome("wishlist #000000000000000000000000")
			.products(products)
			.build());
	}

	private Set<Product> addProduct(int max) {
		Set<Product> products = new HashSet<Product>();
		for (int i = 1; i <= max; i++) {
			String id = "%024d".formatted(i);
			Product product = saveProduct(id);
			products.add(product);
		}
		return products;
	}

	private Product saveProduct(String id) {
		Product product = Product.builder()
				.id(new ObjectId(id))
				.nome("product #" + id)
				.build();
		getMongoTemplate().save(product);
		return product;
	}

}
