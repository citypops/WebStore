package citypops.webstore.persistence.impl;

import citypops.webstore.domain.User;
import citypops.webstore.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDAO implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM accounts";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper());
        for(User user : users) {
            user.setRoles(getUserRoles(user.getUsername()));
        }
        return users;
    }

    @Override
    public User getUserById(String username) {

        String sql = "SELECT * FROM accounts WHERE username=?";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
        user.setRoles(getUserRoles(username));
        return user;
    }

    private List<String> getUserRoles(String username) {

        String sql = "SELECT role FROM user_roles WHERE username=?";
        return jdbcTemplate.queryForList(sql, String.class, username);
    }

    @Override
    public void addUser(User user) {

        String sql = "INSERT INTO accounts (username,password,email,first_name,last_name," +
                "city,postcode,street,home_no,phone_no) values (?,?,?,?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO user_roles (username,role) values (?,'ROLE_CUSTOMER')";

        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getCity(), user.getPostcode(),
                user.getStreet(), user.getHomeNo(), user.getPhoneNo());

        jdbcTemplate.update(sql2, user.getUsername());
    }

    @Override
    public void updateUser(User u) {

        String sql = "UPDATE accounts SET password=?,email=?,first_name=?,last_name=?," +
                "city=?,postcode=?,street=?,home_no=?,phone_no=?,enabled=? WHERE username=?";
        String sql2 = "DELETE FROM user_roles WHERE username=?";
        String sql3 = "INSERT INTO user_roles (username,role) values (?,?)";
        String username = u.getUsername();

        jdbcTemplate.update(sql, u.getPassword(), u.getEmail(), u.getFirstName(),
                u.getLastName(), u.getCity(), u.getPostcode(), u.getStreet(), u.getHomeNo(),
                u.getPhoneNo(), u.isEnabled(), username);

        jdbcTemplate.update(sql2, username);

        List<String> roles = u.getRoles();
        if(! roles.isEmpty()) {
            for(String role : roles) {
                jdbcTemplate.update(sql3, username, role);
            }
        }
    }

    @Override
    public void deleteUser(String username) {

        String sql = "DELETE FROM user_roles WHERE username=?";
        String sql2 = "DELETE FROM accounts WHERE username=?";
        jdbcTemplate.update(sql, username);
        jdbcTemplate.update(sql2, username);
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"),
                    rs.getString("first_name"), rs.getString("last_name"), rs.getString("city"),
                    rs.getString("postcode"), rs.getString("street"), rs.getString("home_no"),
                    rs.getString("phone_no"), rs.getBoolean("enabled"));
        }
    }
}