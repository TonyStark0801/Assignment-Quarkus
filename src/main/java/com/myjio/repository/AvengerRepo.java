package com.myjio.repository;

import com.myjio.model.Avenger;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

@ApplicationScoped
public class AvengerRepo implements PanacheMongoRepository<Avenger> {

    @PostConstruct
    void ensureIndexes() {
        MongoCollection<Avenger> collection = mongoCollection();
        collection.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
    }
}
