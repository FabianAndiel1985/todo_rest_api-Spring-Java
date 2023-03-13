package com.example.myrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greet")
    //hier wird der reuest parameter auf eine Variable gemapped
    public String hello(@RequestParam(value = "firstname")String firstname, @RequestParam(value = "lastname")String lastname ) {
        return "Hallo" + firstname + lastname;
    }

    @GetMapping("/todo")
    public ResponseEntity<ToDo> getTodo(@RequestParam(value = "id")int id) {
       Optional<ToDo> requestedToDo = toDoRepository.findById(id);
        if(!requestedToDo.isPresent()) {
            return new ResponseEntity("there is no to do with the id "+ id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ToDo>(requestedToDo.get(), HttpStatus.OK);
    }

    @GetMapping("/todo/all")
    public ResponseEntity<Iterable<ToDo>> getAll(@RequestHeader("api-secret") String secret) {
        Optional<User> userBySecret = userRepository.findBySecret(secret);
        if(!userBySecret.isPresent()) {
            return new ResponseEntity("invalid api secret", HttpStatus.BAD_REQUEST);
        }
        Iterable<ToDo> toDos = toDoRepository.findAllByUserId(userBySecret.get().getId());
        return new ResponseEntity<Iterable<ToDo>>(toDos, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDo> create(@RequestBody ToDo newToDo) {
        toDoRepository.save(newToDo);
        return new ResponseEntity<ToDo>(newToDo, HttpStatus.OK);
    }

    @DeleteMapping("/todo")
    public ResponseEntity<String> delete(@RequestParam(value = "id")int id) {

        Optional<ToDo> requestedToDo = toDoRepository.findById(id);

        if(!requestedToDo.isPresent()) {
            return new ResponseEntity<String>("there is no to do with the id "+ id, HttpStatus.NOT_FOUND);
        }

        toDoRepository.deleteById(id);
        return new ResponseEntity<String>("todo deleted successfull", HttpStatus.OK);
    }

    @PutMapping("/todo")
    public ResponseEntity<ToDo> update(@RequestBody ToDo newToDo) {
        Optional<ToDo> requestedToDo = toDoRepository.findById(newToDo.getId());

        if(!requestedToDo.isPresent()) {
            return new ResponseEntity("toDo with id " + newToDo.getId() + " doesnt exist", HttpStatus.NOT_FOUND);
        }
        ToDo savedToDo = toDoRepository.save(newToDo);
        return new ResponseEntity(savedToDo, HttpStatus.OK);
    }

    @PatchMapping("/todo/setDone")
    public ResponseEntity<ToDo> patch(@RequestParam(value = "id")int id,
                                      @RequestParam(value = "done")boolean done )
    {
        Optional<ToDo> requestedToDo = toDoRepository.findById(id);

        if(!requestedToDo.isPresent()) {
            return new ResponseEntity("toDo with id " + id + " doesnt exist", HttpStatus.NOT_FOUND);
        }

        requestedToDo.get().setIsDone(done);
        ToDo savedToDo = toDoRepository.save(requestedToDo.get());

        return new ResponseEntity<ToDo>(requestedToDo.get(),HttpStatus.OK);
    }



    }
