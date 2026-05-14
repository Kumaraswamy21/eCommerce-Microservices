package com.ecommerce.user.controller;


import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        return new ResponseEntity<>(userService.fetchAllUsers(),
                                    HttpStatus.OK);
       // return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id){
//        User user = userService.fetchUser(id);
//        if(user == null)
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(user);

        log.trace("This is TRACE level - Very detailed logs");
        log.debug("This is DEBUG level - Used for development debugging");
        log.info("This is INFO level - General system information");
        log.warn("This is WARN level - Something might be wrong");
        log.error("This is ERROR level - Something failed");

        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return ResponseEntity.ok("User added Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserRequest updateUserRequest){
        boolean updated  = userService.updateUser(id,updateUserRequest);
        if(updated)
            return ResponseEntity.ok("User updated Successfully");
        return ResponseEntity.notFound().build();
    }
}
