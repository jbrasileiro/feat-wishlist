package org.wishlist.application.wishlist;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Wishlist;
import org.wishlist.domain.service.WishlistService;
import org.wishlist.domain.to.WishlistTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistUseCase {

	private final WishlistService wishlistService;

	public PageTO<Wishlist> findAll(PaginationTO pagination) {
		return wishlistService.findAll(pagination);
	}
	
	public Collection<Wishlist> findAll() {
		return wishlistService.findAll();
	}

	public Wishlist findByID(ObjectId id) {
		return wishlistService.get(id);
	}

	public Wishlist save(WishlistTO o) {
		return wishlistService.save(o);
	}

	public Wishlist update(ObjectId id, WishlistTO request) {
		return wishlistService.update(id, request);
	}

	public boolean delete(ObjectId id) {
		return wishlistService.delete(id);
	}

	public Wishlist addProduct(ObjectId id, Collection<ObjectId> productID) {
		return wishlistService.addProduct(id, productID);
	}


}
