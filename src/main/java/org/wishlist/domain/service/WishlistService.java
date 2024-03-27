package org.wishlist.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.exceptions.ApplicationRuntimeException;
import org.wishlist.domain.model.Client;
import org.wishlist.domain.model.Product;
import org.wishlist.domain.model.Wishlist;
import org.wishlist.domain.port.out.WishlistPO;
import org.wishlist.domain.to.WishlistTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistService {

	private final WishlistPO port;
	private final ProductService productService;
	private final ClientService clientService;
	
	public Wishlist get(ObjectId id) {
		return port.findByID(id).orElseThrow(() ->
        new ApplicationRuntimeException(
            String.format("Couldn't find Wishlist with ID [%s]", id)));
	}
	
	public Optional<Wishlist> findByID(ObjectId id) {
		return port.findByID(id);
	}
	
	public PageTO<Wishlist> findAll(PaginationTO pagination) {
		return port.findAll(pagination);
	}

	public Collection<Wishlist> findAll() {
		return port.findAll();
	}

	public Wishlist save(WishlistTO o) {
		Client client = clientService.get(o.getClientId());
		Wishlist entity = Wishlist.builder()
				.nome(o.getNome())
				.client(client)
				.build();
		return port.save(entity);
	}

	public Wishlist update(ObjectId id, WishlistTO to) {
		Wishlist current = get(id);
		Client client = clientService.get(to.getClientId());
		Wishlist upadate = Wishlist.builder()
				.id(current.getId())
				.nome(to.getNome())
				.client(client)
				.build();
		return port.save(upadate);
	}

	public boolean delete(ObjectId id) {
		return port.delete(id);
	}

	public Wishlist addProduct(ObjectId id, @NotEmpty Collection<ObjectId> productID) {
		if(new ArrayList<>(productID).isEmpty()) {
			throw new ApplicationRuntimeException("List of product could not be empty");
		}
		Wishlist wishlist = get(id);
		Collection<Product> products = productService.get(productID);
		wishlist.addProducts(products);
		return port.save(wishlist);
	}

	
}
