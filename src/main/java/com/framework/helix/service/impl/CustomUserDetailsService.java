package com.framework.helix.service.impl;

/**
 * Created by nuwan.n.bandara on 5/26/2014.
 */
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * This...
     * @param  login
     * @return UserDetails
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User domainUser = null;
       try {
            domainUser = userService.getUser(login);

        } catch (HelixServiceException e) {
            //todo: implement login
            e.printStackTrace();
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(domainUser.getUserName(), domainUser.getPassword().toLowerCase(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(domainUser.getIdRole()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(Integer role) {
        List<String> roles = new ArrayList<String>();
        if (role.equals(1)) {
           roles.add("ROLE_ADMIN");
        } else if (role.equals(2)) {
           roles.add("ROLE_USER");
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}