package com.alba.simplecrudspring.controller;

import com.alba.simplecrudspring.model.Users;
import com.alba.simplecrudspring.repository.UsersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersJpaRepository userJpaReposiory;

    @GetMapping(value = "/all")
    public List<Users> findAll(){
       return userJpaReposiory.findAll();
    }

    @GetMapping(value = "/{name}")
    public Users findByName(@PathVariable final String name){
        return userJpaReposiory.findByName(name);
    }

    @PostMapping(value = "/create")
    public Users create (@RequestBody final Users users){
        userJpaReposiory.save(users);
        return userJpaReposiory.findByName(users.getName());
    }

    @PutMapping("/update/{id}")
    Users update (@RequestBody Users newUser, @PathVariable Long id){
        return userJpaReposiory.findById(id)
                .map(users -> {
                    users.setName(newUser.getName());
                    users.setJob(newUser.getJob());
                    users.setSalary(newUser.getSalary());
                    return userJpaReposiory.save(users);
                }).orElseGet(() -> {
                    newUser.setId(id);
                    return userJpaReposiory.save(newUser);
                });
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        userJpaReposiory.deleteById(id);
    }


}
