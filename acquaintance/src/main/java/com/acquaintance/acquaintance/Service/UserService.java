package com.acquaintance.acquaintance.Service;

import com.acquaintance.acquaintance.Config.UserConfig;
import com.acquaintance.acquaintance.Entity.User;
import com.acquaintance.acquaintance.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByName(username);


        return user.map(UserConfig::new)
                .orElseThrow(()->new UsernameNotFoundException(username+"Пользователь отсутствует"));
    }
    public void registerNewUser(String username, String password, String email) {
        User newUser = new User();
        newUser.setName(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("ROLE_USER");
        userRepo.save(newUser);
    }
}