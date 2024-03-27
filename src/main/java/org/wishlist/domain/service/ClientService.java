package org.wishlist.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wishlist.domain.commons.PageTO;
import org.wishlist.domain.commons.PaginationTO;
import org.wishlist.domain.exceptions.ApplicationRuntimeException;
import org.wishlist.domain.model.Client;
import org.wishlist.domain.port.out.ClientPO;
import org.wishlist.domain.to.ClientTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientPO port;
	
	public Client get(ObjectId id) {
		return port.findByID(id).orElseThrow(() ->
        new ApplicationRuntimeException(
            String.format("Couldn't find Client with ID [%s]", id)));
	}
	
	public Optional<Client> findByID(ObjectId id) {
		return port.findByID(id);
	}

	public Collection<Client> findAll() {
		return port.findAll();
	}
	
	public PageTO<Client> findAll(PaginationTO pagination) {
		return port.findAll(pagination);
	}

	public Client save(ClientTO o) {
		Client entity = Client.builder()
				.nome(o.getNome())
				.build();
		return port.save(entity);
	}

	public Client update(ObjectId id, ClientTO to) {
		Client current = get(id);
		current.update(to);
		return port.save(current);
	}

	public boolean delete(ObjectId id) {
		return port.delete(id);
	}

	
	
}
