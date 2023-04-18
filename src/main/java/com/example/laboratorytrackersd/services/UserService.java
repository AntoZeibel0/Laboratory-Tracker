package com.example.laboratorytrackersd.services;

import com.example.laboratorytrackersd.model.User;
import com.example.laboratorytrackersd.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.laboratorytrackersd.model.Role.STUDENT;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user){
        log.info("Saving user {} into the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(STUDENT);
        user.setFullName(user.getFullName());
        user.setHobby(user.getHobby());
        user.setGroupNumber(user.getGroupNumber());
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id){
        log.info("Deleting user with {} id", userRepository.findById(id));
        userRepository.delete(userRepository.findById(id).get());
    }

    public List<User> fetchUsers(){
        log.info("Fetching all users.");
        return userRepository.findAll();
    }

    public User updateUser(User user, Integer id){
        User userDB = userRepository.findById(id).get();

        if(Objects.nonNull(user.getUsername())
                && !"".equalsIgnoreCase(user.getUsername())){
            userDB.setUsername(user.getUsername());
        }

        if(Objects.nonNull(user.getPassword())
                && !"".equalsIgnoreCase(user.getPassword())){
            userDB.setPassword((user.getPassword()));
        }

        if(Objects.nonNull(user.getFullName())
                && !"".equalsIgnoreCase(user.getFullName())){
            userDB.setFullName((user.getFullName()));
        }

        if(Objects.nonNull(user.getHobby())
                && !"".equalsIgnoreCase(user.getHobby())){
            userDB.setHobby((user.getHobby()));
        }

        if(Objects.nonNull(user.getGroupNumber())){
            userDB.setGroupNumber((user.getGroupNumber()));
        }

//        if(Objects.nonNull(user.getRole().name())
//                && !"".equalsIgnoreCase(user.getRole().name())){
//            userDB.setRole(user.getRole());
//        }
        userDB.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userDB);
    }
}
