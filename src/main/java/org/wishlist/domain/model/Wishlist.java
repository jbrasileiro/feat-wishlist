package org.wishlist.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.wishlist.domain.exceptions.ApplicationRuntimeException;
import org.wishlist.domain.to.WishlistTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document("wishlists")
public class Wishlist implements Serializable {

	private static final int MAX_PRODUCT = 20;

	@Id
	private ObjectId id;

	private String nome;
	private Client client;
	@Builder.Default
	private Set<Product> products = new HashSet<>();
	
	public Set<Product> getProducts(){
		return Collections.unmodifiableSet(products);
	}
	
	public boolean addProducts(Product o){
		return addProducts(List.of(o));
	}
	
	public boolean addProducts(Collection<Product> o) {
		if (products.size() >= MAX_PRODUCT) {
			throw new ApplicationRuntimeException("Wishlist #%s with max product".formatted(id.toHexString()));
		}
		return products.addAll(o);
	}
	
	public void update(WishlistTO to) {
		this.nome = to.getNome();
	}

}
