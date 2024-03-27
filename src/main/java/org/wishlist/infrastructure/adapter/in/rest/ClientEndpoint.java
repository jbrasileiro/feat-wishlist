package org.wishlist.infrastructure.adapter.in.rest;

import org.bson.types.ObjectId;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wishlist.application.client.ClientUseCase;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Client;
import org.wishlist.domain.to.ClientTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientEndpoint {

	private final ClientUseCase useCase;

	@GetMapping
	public ResponseEntity<PageTO<Client>> get(@Valid @ParameterObject PaginationTO pagination) {
		return ResponseEntity.ok(useCase.findAll(pagination));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> get(
		@PathVariable final ObjectId id) {
		return ResponseEntity.ok(useCase.findByID(id));
	}

	@PostMapping
	public ResponseEntity<Client> save(
		final @Valid @RequestBody ClientTO request) {
		return ResponseEntity.ok(useCase.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> update(
		@PathVariable final ObjectId id,
		final @Valid @RequestBody ClientTO request) {
		return ResponseEntity.ok(useCase.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(
		@PathVariable final ObjectId id) {
		useCase.delete(id);
		return ResponseEntity.noContent().build();
	}
}
