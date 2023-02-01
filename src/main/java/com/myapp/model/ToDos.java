package com.myapp.model;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

public class ToDos {

    private int id;
    private String user;
    private String name;
    private LocalDate endDate;
    private boolean completed;

    public ToDos(int id, String user, String name, LocalDate endDate, boolean completed) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.endDate = endDate;
        this.completed = completed;
    }

    public ToDos(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDos toDos = (ToDos) o;

        if (id != toDos.id) return false;
        if (completed != toDos.completed) return false;
        if (!Objects.equals(user, toDos.user)) return false;
        if (!Objects.equals(name, toDos.name)) return false;
        return Objects.equals(endDate, toDos.endDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ToDos{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", endDate=" + endDate +
                ", completed=" + completed +
                '}';
    }
}
