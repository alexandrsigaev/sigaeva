package ru.job4j.httpexample.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.httpexample.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.01.2019
 */
public class UserDAO implements Store<User> {
    private static BasicDataSource source = new BasicDataSource();
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class.getName());
    private static final UserDAO INSTANCE = new UserDAO();


    private UserDAO() {
        Properties properties = new Properties();
        try (InputStream in = UserDAO.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            source.setDriverClassName(properties.getProperty("driver-class-name"));
            source.setUrl(properties.getProperty("url"));
            source.setUsername(properties.getProperty("username"));
            source.setPassword(properties.getProperty("password"));
            source.setMinIdle(5);
            source.setMaxIdle(10);
            source.setMaxOpenPreparedStatements(100);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static UserDAO getInstance() {
        return UserDAO.INSTANCE;
    }

    @Override
    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = source.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                allUsers.add(this.createUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allUsers;
    }

    @Override
    public User findById(int id) {
        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
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
    public User userLoginIsExists(String login) {
        User user = null;
        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login = ?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = this.createUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO users (name, login, password, email, role, creatDate) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole());
            ps.setTimestamp(6, Timestamp.valueOf(user.getCreateDate()));
            ps.execute();
            result = true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setInt(1, user.getId());
            ps.execute();
            result = true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE users SET name = ?, password = ?, email = ?, role = ? WHERE id = ?")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            ps.setInt(5, user.getId());
            result = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);

        }
        return result;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        LocalDateTime creatDate = resultSet.getTimestamp("creatDate").toLocalDateTime();
        return new User(id, name, login, password, email, role, creatDate);
    }
}
