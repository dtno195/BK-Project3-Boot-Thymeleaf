package com.sd.service.impl;

import com.sd.entity.User;
import com.sd.repo.UserRepository;
import com.sd.service.UserService;
import com.sd.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User checkLogin(String email, String password) {
        User u = userRepository.findByEmail(email);
        String passwordEncode = SecurityUtil.md5(password);
        if(u!=null && u.getPassword().equals(passwordEncode)){
            return u;
        }
        return null;
    }


}
