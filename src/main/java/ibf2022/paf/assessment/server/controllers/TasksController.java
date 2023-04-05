package ibf2022.paf.assessment.server.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.exception.InsertException;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    private TodoService todoSvc;

    @PostMapping(path="/task")
    public ModelAndView postTask(@RequestParam Map<String, String> form, Model model) throws InsertException{

        List<Task> tasks = new ArrayList<>();

        int taskNumber = 0;

        String username = form.get("username");

        // System.out.println(username);

        for (int i = 0; i < form.size(); i++) {
            String description = form.get("description-" + i);
            if (description == null) {
                break;
            }
            taskNumber += 1;
            String priority = form.get("priority-" + i);
            String date = form.get("dueDate-" + i);
            // System.out.println(description);
            // System.out.println(priority);
            // System.out.println(date);
            Date dueDate = Date.valueOf(date);
            Task task = new Task(username, description, Integer.parseInt(priority), dueDate);
            tasks.add(task);
            System.out.println(task.toString());
        }

        ModelAndView view = new ModelAndView();

        try {
            todoSvc.upsertTask(username, tasks);
            model.addAttribute("taskCount", taskNumber);
            model.addAttribute("username", username);
            view.setViewName("result");
            view.setStatus(HttpStatusCode.valueOf(200));
            return view;
        }
        catch (InsertException ex) {
            view.setViewName("error");
            view.setStatus(HttpStatusCode.valueOf(500));
            return view;
        }
    }
}
