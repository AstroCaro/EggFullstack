package principal.persistence;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public abstract class DAO {

    protected Connection connection = null;
    protected ResultSet result = null;
    protected Statement statement = null;

    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "dogs";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    protected void dataBaseConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            String urlDataBase = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            connection = DriverManager.getConnection(urlDataBase, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    protected void dataBaseDisconnect() throws Exception {
        try {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    protected void insertUpdateDelete(String sql) throws Exception {
        try {
            dataBaseConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            //connection.rollback();
            /* SET autocommit = 1;
            COMMIT;
             */
            throw ex;
        } finally {
            dataBaseDisconnect();
        }
    }

    protected void selectDataBase(String sql) throws Exception {
        try {
            dataBaseConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
