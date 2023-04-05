package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.Utils;
import ibf2022.paf.assessment.server.exception.InsertException;
import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3

@Repository
public class UserRepository {

    private final String FIND_USER_SQL = "select * from user where username = ?";
    private final String INSERT_USER_SQL = "insert into user(id, username, name) values(?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> findUserByUsername(String username) {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(FIND_USER_SQL, username);

        if (!rs.next()) {
            return Optional.empty();
        }
        return Optional.of(Utils.toUser(rs));
    }

    public String insertUser(User user) throws InsertException{

        String newId = UUID.randomUUID().toString().substring(0, 8);

        user.setUserId(newId);


        jdbcTemplate.update(INSERT_USER_SQL, user.getUserId(), 
                user.getUsername(), user.getName().isEmpty() ? null : user.getName());

        return newId;
    }

}
