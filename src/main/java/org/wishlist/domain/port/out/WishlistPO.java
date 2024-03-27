package org.wishlist.domain.port.out;

import java.util.Collection;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Wishlist;

public interface WishlistPO {

	Collection<Wishlist> findAll();

	PageTO<Wishlist> findAll(PaginationTO pagination);

	Optional<Wishlist> findByID(ObjectId id);
	
	Collection<Wishlist> findAllById(Collection<ObjectId> id);

	Wishlist save(Wishlist entity);

	boolean delete(ObjectId id);


}
