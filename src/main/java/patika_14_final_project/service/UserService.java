package patika_14_final_project.service;

import patika_14_final_project.dao.UserDAO;
import patika_14_final_project.exception.ExceptionMessagesConstant;
import patika_14_final_project.exception.PatikaStoreException;
import patika_14_final_project.model.User;
import patika_14_final_project.model.enums.Role;
import patika_14_final_project.util.PasswordUtil;

import java.security.PublicKey;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }
    public void save(String userName , String password) throws PatikaStoreException {
        User foundUser = userDAO.findByUserName(userName);
        if (foundUser != null){
            throw new PatikaStoreException(ExceptionMessagesConstant.USER_EMAIL_ALREADY_EXISTS);
        }
         userDAO.save(new User(userName,PasswordUtil.hash(password), Role.SUPPORT));

    }

    public User login(String userName, String password) throws PatikaStoreException {
        User foundUser = userDAO.findByUserName(userName);

        if (foundUser != null) {
            String hashedPassword = PasswordUtil.hash(password);
            if (!hashedPassword.equals(foundUser.getPassword())) {
                throw new PatikaStoreException(ExceptionMessagesConstant.USER_PASSWORD_DOES_NOT_MATCH);
            }
        } else {
            throw new PatikaStoreException(ExceptionMessagesConstant.USER_PASSWORD_DOES_NOT_MATCH);
        }
        return foundUser;

    }
}
