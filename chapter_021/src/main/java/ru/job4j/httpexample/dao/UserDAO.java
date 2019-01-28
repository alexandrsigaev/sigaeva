package ru.job4j.httpexample.dao;

import org.apache.log4j.Logger;
import ru.job4j.httpexample.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.01.2019
 */
public class UserDAO implements Store<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static UserDAO instance;
    private Connection connection;
    private Properties userScripts = new Properties();
    private List<User> users = new CopyOnWriteArrayList<>();

    private UserDAO() {
    }

    synchronized public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
            instance.initProperties();
            instance.initConnection();
            instance.initTable();
        }
        return instance;
    }

    private void initTable() {
        try (Statement st = this.connection.createStatement()) {
            st.executeUpdate(this.userScripts.getProperty("CREATE_TABLE_USERS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uploading property during object creation.
     */
    private void initProperties() {
        try (InputStream in = UserDAO.class.getClassLoader().getResourceAsStream("users_db.sql")) {
            this.userScripts.load(in);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public void initConnection() {
        Connection connection = null;
        Properties config = new Properties();
        try (InputStream input = UserDAO.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            System.out.println(ex.getMessage());
        }
        this.connection = connection;
    }

    @Override
    synchronized public List<User> findAll() {
        try (Statement st = this.connection.createStatement()) {
            try (ResultSet rs = st.executeQuery(this.userScripts.getProperty("SELECT_ALL"))) {
                fillListUser(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return this.users;
    }

    @Override
    synchronized public User findById(int id) {
        try (PreparedStatement ps = this.connection.prepareStatement(this.userScripts.getProperty("FIND_USER_BY_ID"))) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return this.createUser(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    synchronized public boolean add(User user) {
        boolean result = false;
        try (PreparedStatement ps = this.connection.prepareStatement(this.userScripts.getProperty("ADD_USER"))) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setTimestamp(5, Timestamp.valueOf(user.getCreateDate()));
            ps.execute();
            result = true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    synchronized public boolean delete(User user) {
        boolean result = false;
        try (PreparedStatement ps = this.connection.prepareStatement(this.userScripts.getProperty("DELETE_USER"))) {
            ps.setInt(1, user.getId());
            ps.execute();
            result = true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    synchronized public boolean update(User user) {
        boolean result = false;
        try (PreparedStatement ps = this.connection.prepareStatement(this.userScripts.getProperty("UPDATE_USER"))) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getId());
            result = ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean userLoginIsExists(String login) {
        boolean result = false;
        try (PreparedStatement ps = this.connection.prepareStatement(this.userScripts.getProperty("FIND_USER_BY_LOGIN"))) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                result = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void fillListUser(ResultSet resultSet) throws SQLException {
        this.users.clear();
        while (resultSet.next()) {
            this.users.add(this.createUser(resultSet));
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        LocalDateTime creatDate = resultSet.getTimestamp("creatDate").toLocalDateTime();
        return new User(id, name, login, email, password, creatDate);
    }
}
