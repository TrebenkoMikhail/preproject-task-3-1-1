package com.habsida.preproject.service;

import com.habsida.preproject.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    @Override
    public User getUserById(Long id) {
        try{
        return entityManager.find(User.class, id);
    } catch (NoResultException e) {
            e.printStackTrace();
        return null;
        }
    }
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
    @Override
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class,id);
        if(user != null) {
            entityManager.remove(user);
            logger.info("User with id {} has been removed", id);
        } else {
            logger.warn("User with id {} not found", id);
        }
    }
}
