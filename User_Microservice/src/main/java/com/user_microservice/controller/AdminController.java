package com.user_microservice.controller;

import com.user_microservice.dto.InsertUserDTO;
import com.user_microservice.dto.UserDTO;
import com.user_microservice.entity.User;
import com.user_microservice.service.implementation.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/admin")
public class AdminController {
    public final UserService userService;

    @GetMapping("/adminHome")
    public String adminHomePage(){
        return "AdminHome";
    }

    @GetMapping ("/doctors")
    public ResponseEntity<?> getDoctors(){
        List<User> doctors = userService.getDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @GetMapping ("/medicalAssistants")
    public ResponseEntity<?> getMedicalAssistants(){
        List<User> medicalAssistants = userService.getMedicalAssistants();
        return new ResponseEntity<>(medicalAssistants, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<UUID> insertUser(@RequestBody InsertUserDTO userDTO){
        System.out.println(userDTO.toString());
        UUID userID = userService.insertUser(userDTO);
        return new ResponseEntity<>(userID, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")UUID id){
        UUID userId = userService.deleteUser(id);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        User user = userService.editUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/isUsernameTaken/{username}")
    public ResponseEntity<?> isUsernameTaken(@PathVariable("username") String username){
        boolean isTaken = userService.isUsernameTaken(username);
        return new ResponseEntity<>(isTaken, HttpStatus.OK);
    }

}
