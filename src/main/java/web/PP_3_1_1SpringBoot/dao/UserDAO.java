package web.PP_3_1_1SpringBoot.dao;


import web.PP_3_1_1SpringBoot.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getListUsers();
    User showUser(int id);
}
