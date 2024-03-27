package org.wishlist.domain.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.exceptions.ApplicationRuntimeException;
import org.wishlist.domain.model.Product;
import org.wishlist.domain.port.out.ProductPO;
import org.wishlist.domain.to.ProductTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductPO port;
	
	public Product get(ObjectId id) {
		return port.findByID(id).orElseThrow(() ->
        new ApplicationRuntimeException(
            String.format("Couldn't find Product with ID [%s]", id)));
	}
	

	public Collection<Product> get(Collection<ObjectId> id) {
		Collection<Product> result = port.findAllById(id);
		HashSet<ObjectId> notFound = new HashSet<>(id);
		notFound.removeAll(result.stream().map(each -> each.getId()).toList());
		if(notFound.isEmpty()) {
			return result;
		} else {
			String values = notFound.stream().map(ObjectId::toHexString) .collect(Collectors.joining());
			throw new ApplicationRuntimeException(String.format("Couldn't find entity with ID [%s]", values));
		}
	}
	
	public Optional<Product> findByID(ObjectId id) {
		return port.findByID(id);
	}

	public Collection<Product> findAll() {
		return port.findAll();
	}
	
	public PageTO<Product> findAll(PaginationTO pagination) {
		return port.findAll(pagination);
	}

	public Product save(ProductTO o) {
		Product entity = Product.builder()
				.nome(o.getNome())
				.build();
		return port.save(entity);
	}

	public Product update(ObjectId id, ProductTO to) {
		Product current = get(id);
		current.update(to);
		return port.save(current);
	}

	public boolean delete(ObjectId id) {
		return port.delete(id);
	}
	
}
