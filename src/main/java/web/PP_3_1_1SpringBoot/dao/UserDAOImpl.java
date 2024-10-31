package web.PP_3_1_1SpringBoot.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import web.PP_3_1_1SpringBoot.model.User;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(@Valid User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(@Valid User user) {
        User userUsing  = em.find(User.class, user.getId());
        if (userUsing  == null) {
            throw new EntityNotFoundException("Пользователь с id " + user.getId() + " не найден для обновления");
        }
        em.merge(user);
    }

    @Override
    public void deleteUser(@Valid int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        } else {
            throw new EntityNotFoundException("Пользователь с id " + id + " не найден для удаления");
        }
    }

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User showUser(int id) {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Пользователь с id " + id + " не найден");
        }
        return user;
    }
}
