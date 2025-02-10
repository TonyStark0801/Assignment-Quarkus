package com.myjio.service;

import com.myjio.model.Users;
import com.myjio.repository.UserRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepo userRepo;

    public List<Users> getAllUsers(){
        return userRepo.listAll();
    }

    @Transactional(rollbackOn = Exception.class)
    public Users addUser(Users users) {
        try {
            userRepo.persist(users);
            return users;
        } catch (ConstraintViolationException e) {
            throw new WebApplicationException("User with email " + users.getEmail() + " already exists.",Response.Status.CONFLICT);
        } catch (Exception e) {
            throw new RuntimeException("Database error occurred while adding user: " + e.getMessage());
        }
    }

    public Users getById(Long id){
        return  userRepo.findByIdOptional(id).orElseThrow(()-> new NotFoundException("User not found with id: "+id));
    }
    @Transactional
    public void  deleteById(Long id){
        userRepo.deleteById(id);
    }

}
