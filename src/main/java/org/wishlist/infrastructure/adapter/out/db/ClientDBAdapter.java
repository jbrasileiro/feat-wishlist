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
import org.wishlist.domain.model.Client;
import org.wishlist.domain.port.out.ClientPO;
import org.wishlist.infrastructure.db.nosql.ClientRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientDBAdapter implements ClientPO {
	
	private final ClientRepository repository;

	@Override
	public PageTO<Client> findAll(@NotNull PaginationTO pagination) {
		Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
		Page<Client> result = repository.findAll(pageable);
		return PageTO.of(result.getContent(), pagination, result.getTotalElements());
	}
	
	@Override
	public Collection<Client> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Client> findByID(ObjectId id) {
		return repository.findById(id);
	}

	@Override
	public Client save(Client entity) {
		return repository.save(entity);
	}

	@Override
	public boolean delete(ObjectId id) {
		repository.deleteById(id);
		return true;
	}


}
