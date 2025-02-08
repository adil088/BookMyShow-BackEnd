package com.bookmyshow_experience.Book_my_show_experience.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.services.DatabaseAPIUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DatabaseAPIUtil databaseAPIUtil;

    public CustomUserDetailsService(DatabaseAPIUtil databaseAPIUtil) {
        this.databaseAPIUtil = databaseAPIUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = databaseAPIUtil.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
