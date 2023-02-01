package com.myapp.services;

import com.myapp.model.ToDos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    public static List<ToDos> todos = new ArrayList<>();
    private static int count=0;

    static {
        todos.add(new ToDos(++count, "Namig", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        todos.add(new ToDos(++count, "Namig", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todos.add(new ToDos(++count, "Asif", "Learn Full Stack Development",
                LocalDate.now().plusYears(3), false));
    }

    public List<ToDos> findByUserName(String username) {
        Predicate<? super ToDos> predicate = toDos -> toDos.getUser().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).collect(Collectors.toList());
    }

    public void addTodos(String username, String description, LocalDate endDate,boolean completed){
        todos.add(new ToDos(++count,username,description,endDate,completed));
    }

    public void deleteById(int id){
        todos.removeIf(toDo -> toDo.getId() == id);
    }

    public ToDos findById(int id){
        for(ToDos toDo:todos){
            if (toDo.getId()==id){
                return toDo;
            }
        }
        return null;
    }


    public void updateTodo(int id,ToDos toDos) {
        deleteById(id);
        todos.add(toDos);
    }
}
