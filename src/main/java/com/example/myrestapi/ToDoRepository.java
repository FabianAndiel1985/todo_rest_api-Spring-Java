package com.example.myrestapi;


import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ToDoRepository extends CrudRepository<ToDo,Integer> {

    Set<ToDo> findAllByUserId(int userId);

}
