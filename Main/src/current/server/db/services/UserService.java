package current.server.db.services;

import current.server.db.DatabaseConnection;
import current.server.db.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    public List<User> findAll() {
        Connection connection = DatabaseConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                if (getUser(rs).isPresent()) {
                    users.add(
                            getUser(rs).get()
                    );
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection);
        }
    }

    public Optional<User> findById(long id) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection);
        }

        return Optional.empty();
    }

    public Optional<User> findByLoginAndPass(String login, String pass) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? and pass = ?");
            statement.setString(1, login);
            statement.setString(2, pass);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return getUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection);
        }
        return Optional.empty();
    }

    public void save(User user) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students (login, pass, nick) VALUES (?, ?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getNick());

            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            DatabaseConnection.rollback(connection);
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection);
        }
    }

    public void update(User user) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET login = ?, pass = ?, nick = ? WHERE id = ?");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getNick());
            statement.setLong(4, user.getId());

            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            DatabaseConnection.rollback(connection);
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection);
        }
    }

    public void delete(User user) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?");
            statement.setLong(1, user.getId());

            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            DatabaseConnection.rollback(connection);
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection);
        }
    }

    private Optional<User> getUser(ResultSet rs) throws SQLException {
        return Optional.of(
                new User(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("pass"),
                        rs.getString("nick")
                ));
    }
}
