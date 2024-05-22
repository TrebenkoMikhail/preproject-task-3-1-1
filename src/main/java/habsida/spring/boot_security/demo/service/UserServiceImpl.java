package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userRepository.findByUsername(username);
    }
}
