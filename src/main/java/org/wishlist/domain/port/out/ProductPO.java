package org.wishlist.domain.port.out;

import java.util.Collection;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Product;

import jakarta.validation.constraints.NotNull;

public interface ProductPO {

	Collection<Product> findAll();
	Optional<Product> findByID(ObjectId id);
	Collection<Product> findAllById(Collection<ObjectId> id);
	Product save(@NotNull Product entity);
	boolean delete(
			ObjectId id);
	PageTO<Product> findAll(@NotNull PaginationTO pagination);

}
