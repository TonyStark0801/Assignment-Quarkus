package com.myjio.service;

import com.myjio.model.Avenger;
import com.myjio.repository.AvengerRepo;
import com.mongodb.MongoWriteException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.UUID;
import java.util.List;

@ApplicationScoped
public class AvengerService {

    @Inject
    AvengerRepo avengerRepo;

    public List<Avenger> getAllUsers() {
        return avengerRepo.listAll();
    }

    public Avenger addUser(Avenger avenger) {
        try {
            avengerRepo.persist(avenger);
            return avenger;
        } catch (MongoWriteException e) {
            if (e.getError().getCode() == 11000) {  // Duplicate email id is handled here.
                throw new WebApplicationException("User with email " + avenger.getEmail() + " already exists.", Response.Status.CONFLICT);
            }
            throw new RuntimeException("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    public Avenger getByUserId(ObjectId userId) {
        Avenger user = avengerRepo.find("_id", userId).firstResult();
        if (user == null) {
            throw new NotFoundException("User not found with userId: " + userId);
        }
        return user;
    }

    public void deleteByUserId(ObjectId userId) {
        long deleted = avengerRepo.delete("_id", userId);
        if (deleted == 0) {
            throw new NotFoundException("User not found with userId: " + userId);
        }
    }
}
