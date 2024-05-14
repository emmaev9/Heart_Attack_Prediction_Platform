package com.user_microservice.service.implementation;

import com.user_microservice.controller.handlers.exceptions.ResourceNotFoundException;
import com.user_microservice.dto.DoctorDTO;
import com.user_microservice.dto.InsertUserDTO;
import com.user_microservice.dto.UserDTO;
import com.user_microservice.dto.UserDetailsDTO;
import com.user_microservice.entity.User;
import com.user_microservice.mapper.UserMapper;
import com.user_microservice.repository.UserRepository;
import com.user_microservice.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    public final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder encoder;
    private final WebClient.Builder webClientBuilder;
    private final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public void processToken(String token) {
        tokenThreadLocal.set(token);
    }

    public String getToken() {
        return tokenThreadLocal.get();
    }

    @Override
    public UUID insertUser(InsertUserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user = userRepository.save(user);
        if(user.getRole().equals("DOCTOR")) {
            DoctorDTO doctorDTO = new DoctorDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername());
            WebClient.builder()
                    .baseUrl("http://localhost:8081")
                    .build()
                    .post()
                    .uri("/doctor/insert")
                    .bodyValue(doctorDTO)
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse -> {
                        return Mono.error(new RuntimeException("Call to doctor service failed"));
                    })
                    .toBodilessEntity()
                    .block();
        }
        return user.getId();
    }

    @Override
    public UUID deleteUser(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            LOGGER.error("Person with id {} was not found in db", userId);
            throw new com.user_microservice.controller.handlers.exceptions.ResourceNotFoundException(User.class.getSimpleName() + " with id: " + userId);
        }
        if(optionalUser.get().getRole().equals("DOCTOR")){
            WebClient.builder()
                    .baseUrl("http://localhost:8081")
                    .build()
                    .delete()
                    .uri("/doctor/delete/"+ userId.toString())
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse -> {
                        return Mono.error(new RuntimeException("Call to doctor service failed"));
                    })
                    .toBodilessEntity()
                    .block();
        }

        userRepository.delete(optionalUser.get());
        return userId;

    }

    @Override
    public List<User> getDoctors() {
        List<User> usersList =  userRepository.findAll();
        List<User> doctorsList = new ArrayList<>();
        for(User user: usersList){
            if(user.getRole().equals("DOCTOR")) {
                doctorsList.add(user);
            }
        }
        return doctorsList;
    }
    @Override
    public List<User> getMedicalAssistants() {
        List<User> usersList =  userRepository.findAll();
        List<User> assistantsList = new ArrayList<>();
        for(User user: usersList){
            if(user.getRole().equals("MEDICAL_ASSISTANT")) {
                assistantsList.add(user);
            }
        }
        return assistantsList;
    }

    @Override
    @Transactional
    public User editUser(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        if(!optionalUser.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", userDTO.getId());
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + userDTO.getId());
        }
        if(optionalUser.get().getRole().equals("DOCTOR")){
            DoctorDTO doctorDTO = new DoctorDTO(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername());
            WebClient.builder()
                    .baseUrl("http://localhost:8081")
                    .build()
                    .put()
                    .uri("/doctor/update")
                    .bodyValue(doctorDTO)
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse -> {
                        return Mono.error(new RuntimeException("Call to doctor service failed"));
                    })
                    .toBodilessEntity()
                    .block();
        }

        userRepository.save(userMapper.DTOtoModel(userDTO));
        return userRepository.findById(userDTO.getId()).get();
    }

    public boolean isUsernameTaken(String username){
        List<User> users = userRepository.findAll();
        Boolean taken = false;
        for(User user: users){
            if(user.getUsername().equals(username)){
                taken = true;
            }
        }
        return taken;
    }
}
