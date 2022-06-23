package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.mapper.LoginMapper;
import com.example.exam_task_with_spring_boot.dto.mapper.LoginResponse;
import com.example.exam_task_with_spring_boot.dto.mapper.ValidationExceptionType;
import com.example.exam_task_with_spring_boot.dto.request.RegisterRequest;
import com.example.exam_task_with_spring_boot.dto.response.RegisterResponse;
import com.example.exam_task_with_spring_boot.models.User;
import com.example.exam_task_with_spring_boot.repositories.UserRepository;
import com.example.exam_task_with_spring_boot.security.jwt.JwtTokenUtil;
import com.example.exam_task_with_spring_boot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginMapper loginMapper;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = userRepository.findByEmail(token.getName()).get();
            return ResponseEntity.ok()
                    .body(loginMapper.loginView(jwtTokenUtil.generateToken(user), ValidationExceptionType.SUCCESSFUL, user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("",
                    ValidationExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    public RegisterResponse create(@RequestBody RegisterRequest request){
        return userService.create(request);
    }
}
