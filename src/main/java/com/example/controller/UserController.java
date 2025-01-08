package com.example.controller;

import com.example.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    // Constructor to add some initial users
    public UserController() {
        users.add(new User(1L, "John Doe", "john.doe@example.com"));
        users.add(new User(2L, "Jane Smith", "jane.smith@example.com"));
    }

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public String handlerMethod() { // handler logic
        return "response";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/books")
    public String getBooks(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                           @RequestParam(value = "author", required = false) String author,
                           @RequestParam(value = "title", required = false) String title) {
        String response = "Books endpoint accessed.";
        if (authorizationHeader != null) {
            response += " Authorization header: " + authorizationHeader;
        }
        if (author != null) {
            response += " Filtering by author: " + author;
        }
        if (title != null) {
            response += " Filtering by title: " + title;
        }
        return response;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
        }
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}

