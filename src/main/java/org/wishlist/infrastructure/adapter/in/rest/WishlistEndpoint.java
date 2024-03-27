package org.wishlist.infrastructure.adapter.in.rest;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wishlist.application.wishlist.WishlistUseCase;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Product;
import org.wishlist.domain.model.Wishlist;
import org.wishlist.domain.to.WishlistTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistEndpoint {

	private final WishlistUseCase useCase;

	@GetMapping
	public ResponseEntity<PageTO<Wishlist>> get(@Valid @ParameterObject PaginationTO pagination) {
		return ResponseEntity.ok(useCase.findAll(pagination));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Wishlist> get(
		@PathVariable final ObjectId id) {
		return ResponseEntity.ok(useCase.findByID(id));
	}
	
	@PostMapping
	public ResponseEntity<Wishlist> save(
			final @Valid @RequestBody WishlistTO request) {
		return ResponseEntity.ok(useCase.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Wishlist> update(
		@PathVariable final ObjectId id,
		final @Valid @RequestBody WishlistTO request) {
		return ResponseEntity.ok(useCase.update(id, request));
	}
	
	@PatchMapping("/{id}/product")
	public ResponseEntity<Wishlist> addProduct(
			@PathVariable final ObjectId id,
			final @RequestBody Set<ObjectId> productIds) {
		return ResponseEntity.ok(useCase.addProduct(id, productIds));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
		@PathVariable final ObjectId id) {
		useCase.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/client/{clientId}/product/{productId}")
	public ResponseEntity<Product> get(
			@PathVariable final Long clientId,
			@PathVariable final Long productId) {
		
		return ResponseEntity.ok().build();
	}
}
