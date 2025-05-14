package com.example.board.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.board.DTO.ApiDTO;
import com.example.board.DTO.UserDTO;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
public class ApiUserController {
    @Autowired UserRepository userRepository;

    @PostMapping("/api/user/signin")
    public ResponseEntity<?> signinPost(@RequestBody User user) {
        Optional<User> opt = userRepository.findByEmail(user.getEmail());
        ApiDTO<UserDTO> res = new ApiDTO<>();

        if(opt.isPresent() && user.getPwd().equals(opt.get().getPwd())) {
            UserDTO dto = new UserDTO();
            dto.setId(opt.get().getId());
            dto.setEmail(opt.get().getEmail());
            dto.setName(opt.get().getName());

            res.setStatus(HttpStatus.OK.value());
            res.setMessage("로그인 성공");
            res.setData(dto);

            return ResponseEntity.ok(res);
        } else {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setMessage("로그인 실패");
            res.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
    }
    
    @PostMapping("/api/user/signup")
    public ResponseEntity<Void> signupPost(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
    
}
