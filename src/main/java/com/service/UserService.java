package com.service;

import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.repository.RoleRepository;
import com.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(1);
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ADMIN"))));
        userRepository.save(user);
    }

    public void deleteUser(User user){
        user.setIsActive(0);
        userRepository.delete(user);
    }
}
