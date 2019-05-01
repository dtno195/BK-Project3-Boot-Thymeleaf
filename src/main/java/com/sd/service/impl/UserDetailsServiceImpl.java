//package com.sd.service.impl;
//
//import com.sd.entity.Role;
//import com.sd.entity.User;
//import com.sd.repo.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//        if(user==null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        Set<Role> roles = user.getRoles();
//        for (Role r : roles){
//            grantedAuthorities.add(new SimpleGrantedAuthority(r.getName()));
//        }
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),user.getPassword(),grantedAuthorities);
//    }
//
//    public boolean checkLogin (String email, String password){
//        User u = userRepository.findByEmail(email);
//        if(u!=null){
//            return true;
//        }
//        return false;
//    }
//}
