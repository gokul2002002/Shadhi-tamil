package com.sayit.shadhi.Security.Service;

import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.UserRepository;
import com.sayit.shadhi.Security.UserDetails.UserDetailImp;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailService implements UserDetailsService {

    private  final UserRepository userRepository;

    public JpaUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> user =  userRepository.findByUserName(username);
            return user.map(UserDetailImp::new).orElseThrow(()->new UsernameNotFoundException("The User Not Found"));
    }
}
