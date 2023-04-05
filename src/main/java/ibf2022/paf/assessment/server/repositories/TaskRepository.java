package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.exception.InsertException;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;

// TODO: Task 6

@Repository
public class TaskRepository {

    private final String INSERT_TASK_SQL = "insert into task(description, priority, due_date, user_id) values (?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepo;

    public void insertTask(String username, Task task) throws InsertException{

        Optional<User> user = userRepo.findUserByUsername(username);

        // Assume user exists
        String id = user.get().getUserId();

        jdbcTemplate.update(INSERT_TASK_SQL, task.getDescription(), task.getPriority(), 
        task.getDueDate(), id);
    }

    // public Boolean insertTask(String username, Task task) {

    //     Boolean bUpdated = false;

    //     String id = "";

    //     Optional<User> user = userRepo.findUserByUsername(username);

    //     if (user.isEmpty()) {
    //         User newUser = new User();
    //         newUser.setName(username);
    //         id = userRepo.insertUser(newUser);
    //     }
    //     else {
    //         id = user.get().getUserId();
    //     }

    //     int iUpdated = jdbcTemplate.update(INSERT_TASK_SQL, task.getDescription(), task.getPriority(), 
    //             task.getDueDate(), id);

    //     if (iUpdated >= 0) {
    //         bUpdated = true;
    //     }

    //     return bUpdated;
    // }
}
