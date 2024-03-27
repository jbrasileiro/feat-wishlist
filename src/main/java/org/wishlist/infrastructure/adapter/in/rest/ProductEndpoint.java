package org.wishlist.infrastructure.adapter.in.rest;

import org.bson.types.ObjectId;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wishlist.application.product.ProductUseCase;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Product;
import org.wishlist.domain.to.ProductTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductEndpoint {

	private final ProductUseCase useCase;

	@GetMapping
	public ResponseEntity<PageTO<Product>> get(@Valid @ParameterObject PaginationTO pagination) {
		return ResponseEntity.ok(useCase.findAll(pagination));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> get(
		@PathVariable final ObjectId id) {
		return ResponseEntity.ok(useCase.findByID(id));
	}

	@PostMapping
	public ResponseEntity<Product> save(
		final @Valid @RequestBody ProductTO request) {
		return ResponseEntity.ok(useCase.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(
		@PathVariable final ObjectId id,
		final @Valid @RequestBody ProductTO request) {
		return ResponseEntity.ok(useCase.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
		@PathVariable final ObjectId id) {
		useCase.delete(id);
		return ResponseEntity.noContent().build();
	}
}
