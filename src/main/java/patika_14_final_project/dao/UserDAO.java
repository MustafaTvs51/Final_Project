package patika_14_final_project.dao;

import patika_14_final_project.model.User;

import java.util.List;

public class UserDAO implements BaseDAO<User>{

    @Override
    public void save(User user) {

    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(long id) {

    }
    public  User findByUserName(String userName) {
       User user = null;
return user;
    }
}
