package com.kaniya.resturentbackend.security.user;

import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.repository.ResturentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {
    private final ResturentRepository resturentRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Resturents user = Optional.ofNullable(resturentRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return ShopUserDetails.buildUserDetails(user);
    }
}