    package com.user_microservice.controller;

    import com.user_microservice.dto.JwtResponse;
    import com.user_microservice.dto.LoginRequestDTO;
    import com.user_microservice.security.JwtUtils;
    import com.user_microservice.security.UserDetailsImpl;
    import com.user_microservice.service.implementation.UserService;
    import lombok.AllArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.stream.Collectors;

    @RestController
    @CrossOrigin(origins = "*")
    @RequestMapping("/auth")
    @AllArgsConstructor
    public class AuthController {

        public final UserService userService;
        public final JwtUtils jwtUtils;
        public final AuthenticationManager authenticationManager;

        @PostMapping("/login")
        public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {

            System.out.println("In login");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);
            userService.processToken(jwt);
            System.out.println(loginRequest.getUsername());
            System.out.println(loginRequest.getPassword());

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            //Person currentPerson = personService.findPersonByEmail(userDetails.getEmail());
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            String role="";
            if (roles.contains("ADMIN")) {
                role = "ADMIN";
            } else if (roles.contains("DOCTOR")) {
                role = "DOCTOR";
            } else if (roles.contains("MEDICAL_ASSISTANT")) {
                role = "MEDICAL_ASSISTANT";
            }
            return ResponseEntity.ok(new JwtResponse(
                    jwt,
                    userDetails.getId(),
                    userDetails.getPassword(),
                    userDetails.getUsername(),
                    role
            ));

        }
    }
