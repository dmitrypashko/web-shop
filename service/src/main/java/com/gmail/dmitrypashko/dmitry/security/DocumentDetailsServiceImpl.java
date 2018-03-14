package com.gmail.dmitrypashko.dmitry.security;

import com.gmail.dmitrypashko.dmitry.IUserService;
import com.gmail.dmitrypashko.dmitry.model.User;
import com.gmail.dmitrypashko.dmitry.modelDTO.AppUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DocumentDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final IUserService userService;

    @Autowired
    public DocumentDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new AppUserPrincipal(user);
    }
}
