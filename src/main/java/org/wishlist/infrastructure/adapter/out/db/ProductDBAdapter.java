package org.wishlist.infrastructure.adapter.out.db;

import java.util.Collection;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Product;
import org.wishlist.domain.port.out.ProductPO;
import org.wishlist.infrastructure.db.nosql.ProductRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductDBAdapter implements ProductPO {
	
	private final ProductRepository repository;

	@Override
	public PageTO<Product> findAll(@NotNull PaginationTO pagination) {
		Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
		Page<Product> result = repository.findAll(pageable);
		return PageTO.of(result.getContent(), pagination, result.getTotalElements());
	}
	
	@Override
	public Collection<Product> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Product> findByID(ObjectId id) {
		return repository.findById(id);
	}
	
	@Override
	public Collection<Product> findAllById(Collection<ObjectId> id) {
		return repository.findAllById(id);
	}

	@Override
	public Product save(Product entity) {
		return repository.save(entity);
	}

	@Override
	public boolean delete(ObjectId id) {
		repository.deleteById(id);
		return true;
	}

}
