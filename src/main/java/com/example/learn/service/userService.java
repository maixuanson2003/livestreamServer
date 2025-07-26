package com.example.learn.service;

import com.example.learn.dto.auth.authResponse;
import com.example.learn.dto.auth.loginRequest;
import com.example.learn.dto.custom.ApiResponse;
import com.example.learn.dto.user.userRequest;
import com.example.learn.dto.user.userResponse;
import com.example.learn.entity.User;
import com.example.learn.mapper.userMapper;
import com.example.learn.repository.userRepository;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

@Service
public class userService {
    @Autowired
    private userRepository userRepositorys;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private jwtService jwtServices;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ApiResponse userRegister(userRequest userRequest) throws Exception {
        User newUser = userRepositorys.save(userMapper.toEntity(userRequest, passwordEncoder));
        redisTemplate.delete("userList");
        new ApiResponse();
        return ApiResponse.builder()
                .message("createSuccess")
                .status("SUCCESS")
                .build();
    }

    public List<userResponse> getAllUser() {
        String redisKey = "userList";
        List<userResponse> cachedUsers = (List<userResponse>) redisTemplate.opsForValue().get(redisKey);
        if (cachedUsers != null) {
            System.out.println(redisKey + " found in cache");
            return cachedUsers;
        }
        List<User> users = userRepositorys.findAll();

        List<userResponse> userResponses = users.stream().map(user -> userMapper.toDto(user)).toList();
        redisTemplate.opsForValue().set(redisKey, userResponses);
        return userResponses;

    }

    public authResponse login(loginRequest loginRequest) {
        User user = userRepositorys.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        boolean isPasswordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!isPasswordMatch) {
            throw new RuntimeException("Invalid password");
        }
        return authResponse.builder()
                .username(user.getUsername())
                .token(jwtServices.createToken(user.getUsername(), user.getRole()))
                .role(user.getRole())
                .build();
    }

    public void testRabbit() {
        rabbitTemplate.convertAndSend("testQueue", "message1");
        rabbitTemplate.convertAndSend("testQueue", "message2");
        rabbitTemplate.convertAndSend("testQueue", "message3");
        rabbitTemplate.convertAndSend("testQueue", "message4");
        rabbitTemplate.convertAndSend("testQueue", "message5");
        rabbitTemplate.convertAndSend("testQueue", "message6");
        rabbitTemplate.convertAndSend("testQueue", "message7");
        rabbitTemplate.convertAndSend("testQueue", "message8");
    }
}
