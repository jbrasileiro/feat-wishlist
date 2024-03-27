package org.wishlist.infrastructure.db.nosql;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.wishlist.domain.model.Client;

public interface ClientRepository extends MongoRepository<Client, ObjectId> {

}
