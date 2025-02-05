package web.PP_3_1_1SpringBoot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import web.PP_3_1_1SpringBoot.dao.UserDAO;
import web.PP_3_1_1SpringBoot.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Transactional
    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
    @Transactional
    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public List<User> getListUsers() {
        return userDAO.getListUsers();
    }

    @Override
    public User showUser(int id) {
        return userDAO.showUser(id);
    }
}
