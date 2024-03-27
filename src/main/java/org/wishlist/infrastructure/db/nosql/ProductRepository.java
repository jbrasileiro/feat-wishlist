package org.wishlist.infrastructure.db.nosql;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.wishlist.domain.model.Product;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {

}
