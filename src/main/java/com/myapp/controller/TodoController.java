package com.myapp.controller;

import com.myapp.model.ToDos;
import com.myapp.services.ToDoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes("name")
public class TodoController {

    private ToDoService toDoService;

    public TodoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @RequestMapping(value = "list-todos",method = RequestMethod.GET)
    public String listToDos(ModelMap modelMap){
        String user = (String) modelMap.getAttribute("name");
        List<ToDos> toDos = toDoService.findByUserName(user);
        modelMap.addAttribute("todos",toDos);
        return "list-todos";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("todos") ToDos toDos) {
        toDoService.addTodos(toDos.getName(),toDos.getUser(),toDos.getEndDate(),toDos.isCompleted());
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "add-todos",method = RequestMethod.GET)
    public String getToDoPage(ModelMap modelMap){
        ToDos theEmployee = new ToDos();
        modelMap.addAttribute("toDos", theEmployee);
        return "add-todo";
    }

    @RequestMapping(value = "add-todos",method = RequestMethod.POST)
    public String addToDo(ModelMap modelMap, ToDos toDos, BindingResult result){
        String user = (String) modelMap.getAttribute("name");
        toDoService.addTodos(user,toDos.getName(),LocalDate.now().plusYears(2), false);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam("todoId") int id){
        toDoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo")
    public String showFormForUpdate(@RequestParam("todoId") int id, ModelMap modelMap){
        ToDos toDos = toDoService.findById(id);
        modelMap.addAttribute("toDos",toDos);
        return "add-todo";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String updateTodo(@ModelAttribute ToDos toDos){
        System.out.println("entered to update");
        if (toDos!=null){
            toDoService.updateTodo(toDos.getId(),toDos);
            System.out.println("entered to if");
            return "redirect:list-todos";
        }
        System.out.println("Entered");
        return "add-todo";
    }
}
