package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.exception.InsertException;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public void upsertTask(String username, List<Task> tasks) throws InsertException{

        String id = "";

        Optional<User> user = userRepo.findUserByUsername(username);

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setName("");
            id = userRepo.insertUser(newUser);
        }
        else {
            id = user.get().getUserId();
        }
        for (Task task : tasks) {
            taskRepo.insertTask(username, task);
        }
    }
}
