package com.example.task1_DI;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;

    @GetMapping("/")
    public String openHomePage(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }

    @GetMapping("/add-task")
    public String openTaskPage() {
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable long id){
        taskRepository.deleteById(id);
        return "redirect:/";
    }



    @GetMapping("/tasks")
    public List<Task> getTasks(){
       return taskRepository.findAll();
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){
        Task task =taskRepository.findById(id).orElse(null);
        if (task==null){
            return "redirect:/";
        }
        model.addAttribute("task", task);
        return "details";
    }

    @PostMapping("/save-task")
    public String updateTask(Task task){
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }

}
