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
import org.wishlist.domain.model.Wishlist;
import org.wishlist.domain.port.out.WishlistPO;
import org.wishlist.infrastructure.db.nosql.WishlistRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WishlistDBAdapter implements WishlistPO {
	
	private final WishlistRepository repository;

	@Override
	public PageTO<Wishlist> findAll(@NotNull PaginationTO pagination) {
		Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
		Page<Wishlist> result = repository.findAll(pageable);
		return PageTO.of(result.getContent(), pagination, result.getTotalElements());
	}
	
	@Override
	public Collection<Wishlist> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Collection<Wishlist> findAllById(Collection<ObjectId> id) {
		return repository.findAllById(id);
	}
	
	@Override
	public Optional<Wishlist> findByID(ObjectId id) {
		return repository.findById(id);
	}

	@Override
	public Wishlist save(Wishlist entity) {
		return repository.save(entity);
	}

	@Override
	public boolean delete(ObjectId id) {
		repository.deleteById(id);
		return true;
	}


}
