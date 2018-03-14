package com.gmail.dmitrypashko.dmitry.modelDTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.gmail.dmitrypashko.dmitry.model.StatusUser;
import com.gmail.dmitrypashko.dmitry.model.User;

import java.util.Collection;
import java.util.Collections;

public class AppUserPrincipal implements UserDetails {

    private User user;

    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    public AppUserPrincipal(User user) {
        this.user = user;
        grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())
        );
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatusUser() == StatusUser.STATUS_UNBLOCKED;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
