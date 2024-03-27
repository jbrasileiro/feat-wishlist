package org.wishlist.application.product;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Product;
import org.wishlist.domain.service.ProductService;
import org.wishlist.domain.to.ProductTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductUseCase {

	private final ProductService service;

	public PageTO<Product> findAll(PaginationTO pagination) {
		return service.findAll(pagination);
	}

	public Product findByID(ObjectId id) {
		return service.get(id);
	}

	public Product save(ProductTO o) {
		return service.save(o);
	}

	public Product update(ObjectId id, ProductTO request) {
		return service.update(id, request);
	}

	public boolean delete(ObjectId id) {
		return service.delete(id);
	}

}
