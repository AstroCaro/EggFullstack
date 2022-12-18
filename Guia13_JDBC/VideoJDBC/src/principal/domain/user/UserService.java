package principal.domain.user;

import java.util.Collection;
import principal.persistence.UserDAO;

public class UserService {

    private final UserDAO dao;

    public UserService() {
        this.dao = new UserDAO();
    }

    public void createUser(String email, String password) throws Exception {

        try {
            //Validamos
            if (email == null || email.trim().isEmpty()) {
                throw new Exception("Email must not be null");
            }
            if (email.contains("@") == false) {
                throw new Exception("Invalid Email");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new Exception("Password must not be null");
            }
            if (password.length() < 8) {
                throw new Exception("Password must not have less than 8 characters");
            }
            if (findUserByEmail(email) != null) {
                throw new Exception("Email " + email + " already exits");
            }

            //Creamos el usuario
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            dao.saveUser(user);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updatePasswordUser(String email, String actualPassword, String newPassword) throws Exception {

        try {

            //Validamos
            if (email == null || email.trim().isEmpty()) {
                throw new Exception("Email must not be null");
            }

            if (actualPassword == null || actualPassword.trim().isEmpty()) {
                throw new Exception("Actual Password must not be null");
            }

            if (newPassword == null || newPassword.trim().isEmpty()) {
                throw new Exception("Enter a new password");
            }

            //Buscamos
            User user = findUserByEmail(email);

            //Validamos
            if (!user.getPassword().equals(actualPassword)) {
                throw new Exception("Actual password does not match for that email");
            }

            //Modificamos
            user.setPassword(newPassword);

            dao.updateUser(user);
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteUser(String email) throws Exception {

        try {

            //Validamos 
            if (email == null || email.trim().isEmpty()) {
                throw new Exception("Email must not be null");
            }

            dao.deleteUser(email);
        } catch (Exception e) {
            throw e;
        }
    }

    public User findUserByEmail(String email) throws Exception {

        try {

            //Validamos
            if (email == null || email.trim().isEmpty()) {
                throw new Exception("Email must not be null");
            }

            User user = dao.findByEmail(email);

            return user;
        } catch (Exception e) {
            throw e;
        }
    }

    public User findUserById(Integer id) throws Exception {

        try {

            //Validamos
            if (id == null) {
                throw new Exception("User id must not be null");
            }
            User user = dao.findById(id);

            return user;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<User> findAllUsers() throws Exception {

        try {

            Collection<User> users = dao.findAllUsers();

            return users;
        } catch (Exception e) {
            throw e;
        }
    }

    public void printUsers() throws Exception {

        try {

            //Listamos los usuarios
            Collection<User> users = findAllUsers();

            //Imprimimos los usuarios
            if (users.isEmpty()) {
                throw new Exception("Can not print anything, there is not users in database");
            } else {
                for (User u : users) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
