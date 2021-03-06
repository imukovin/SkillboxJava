package main;

import javassist.NotFoundException;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ListController {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/")
    public ModelAndView index(Model model) {
        Iterable<Task> iterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : iterable) {
            tasks.add(task);
        }
        model.addAttribute("taskList", tasks);
        return new ModelAndView("index");
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<String> del(@PathVariable(name = "id") int id) throws NotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.ok("ok");
        }
        throw new NotFoundException(Integer.toString(id));
    }

    @PostMapping("/listCompl/{id}")
    public ResponseEntity<String> markCompleted(@PathVariable Integer id) throws NotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            Task t = task.get();
            t.setStatus(true);
            taskRepository.save(t);
            return ResponseEntity.ok("ok");
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }

    @PostMapping("/addtask")
    public ResponseEntity<String> add(Task task) throws NullPointerException {
        if ((task.getTasktext().length() > 0) && (!task.getDate().isEmpty())) {
            taskRepository.save(task);
            return ResponseEntity.ok("ok");
        }
        throw new NullPointerException(task.toString());
    }
}