package principal.persistence;

import java.util.ArrayList;
import java.util.Collection;
import principal.domain.user.User;

public class UserDAO extends DAO {

    public void saveUser(User user) throws Exception {

        try {
            if (user == null) {
                throw new Exception("User must not be null");
            }

            String sql = "INSERT INTO user (email, password)"
                    + "VALUES ( '" + user.getEmail() + "', '" + user.getPassword() + "' ); ";
            insertUpdateDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateUser(User user) throws Exception {
        try {
            if (user == null) {
                throw new Exception("User must not be null");
            }

            String sql = "UPDATE user SET "
                    + "password ( '" + user.getPassword() + "' WHERE email = '" + user.getEmail() + "' ); ";
            insertUpdateDelete(sql);
        } catch (Exception e) {
        }
    }

    public void deleteUser(String email) throws Exception {
        try {
            String sql = "DELETE FROM user WHERE email = '" + email + "'";
            insertUpdateDelete(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public User findByEmail(String email) throws Exception {
        try {
            String sql = "SELECT * FROM user "
                    + "WHERE email = '" + email + "'";
            selectDataBase(sql);
            User user = null;

            while (result.next()) {
                user = new User();
                user.setId(result.getInt(1));
                user.setEmail(result.getString(2));
                user.setPassword(result.getString(3));
            }
            dataBaseDisconnect();
            return user;

        } catch (Exception e) {
            dataBaseDisconnect();
            throw e;
        }
    }

    public User findById(Integer id) throws Exception {
        try {

            String sql = "SELECT * FROM user "
                    + " WHERE id = '" + id + "'";

            selectDataBase(sql);

            User user = null;
            while (result.next()) {
                user = new User();
                user.setId(result.getInt(1));
                user.setEmail(result.getString(2));
                user.setPassword(result.getString(3));
            }
            dataBaseDisconnect();
            return user;
        } catch (Exception e) {
            dataBaseDisconnect();
            throw e;
        }
    }

    public Collection<User> findAllUsers() throws Exception {
        try {
            String sql = "SELECT email, password FROM user";
            selectDataBase(sql);

            User user = null;
            Collection<User> users = new ArrayList();
            while (result.next()) {
                user = new User();
                user.setEmail(result.getString(1));
                user.setPassword(result.getString(2));
                users.add(user);
            }
            dataBaseDisconnect();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            dataBaseDisconnect();
            throw new Exception("System Error");
        }
    }
}
