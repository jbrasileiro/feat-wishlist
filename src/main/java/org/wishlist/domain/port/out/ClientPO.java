package org.wishlist.domain.port.out;

import java.util.Collection;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Client;

import jakarta.validation.constraints.NotNull;

public interface ClientPO {

	Collection<Client> findAll();

	Optional<Client> findByID(ObjectId id);

	Client save(@NotNull Client entity);

	boolean delete(ObjectId id);

	PageTO<Client> findAll(@NotNull PaginationTO pagination);

}
