package com.sayit.shadhi.Services;

import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Exceptions.NotValidInformation;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;

    private final MinioService minioService;


    @Transactional
    public GeneralStatus updateDetail(
        long userId , String value ,  String toUpdate
    )throws NotValidInformation {
        Optional<User> findedUser = userRepository.findById(userId);
        if (findedUser.isPresent()){
                return null;
        }else throw new UsernameNotFoundException("");
    }
}
