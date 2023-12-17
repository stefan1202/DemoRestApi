package com.example.demorestapi.services;

import com.example.demorestapi.entities.User;
import com.example.demorestapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class UserLoginService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameIgnoreCase(username).orElseThrow(()-> new UsernameNotFoundException("Bad credentials"));
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {

                return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
            }

            @Override
            public String getPassword() {
                return user.getUserPassword();
            }

            @Override
            public String getUsername() {
                return user.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
