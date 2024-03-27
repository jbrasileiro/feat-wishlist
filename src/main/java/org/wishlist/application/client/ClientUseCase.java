package org.wishlist.application.client;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.model.Client;
import org.wishlist.domain.service.ClientService;
import org.wishlist.domain.to.ClientTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientUseCase {

	private final ClientService service;

	public PageTO<Client> findAll(PaginationTO pagination) {
		return service.findAll(pagination);
	}

	public Client findByID(ObjectId id) {
		return service.get(id);
	}

	public Client save(ClientTO o) {
		return service.save(o);
	}

	public Client update(ObjectId id, ClientTO request) {
		return service.update(id, request);
	}

	public boolean delete(ObjectId id) {
		return service.delete(id);
	}

}
