package com.turkcell.gyt.managementService.business.concretes;

import com.turkcell.gyt.managementService.business.abstracts.UserService;
import com.turkcell.gyt.managementService.business.messages.AuthMessages;
import com.turkcell.gyt.managementService.core.dtos.request.RegisterRequest;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.types.BusinessException;
import com.turkcell.gyt.managementService.core.utilitiy.mapper.UserMapper;
import com.turkcell.gyt.managementService.dataAccess.UserRepository;
import com.turkcell.gyt.managementService.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper mapper;
    @Override
    public void register(RegisterRequest request) {
        User user = this.mapper.registerRequestToUserEntity(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);

    }

    @Override
    public User findByUsername(String email) {

        return this.userRepository.findUserByEmail(email).orElseThrow();
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(()-> new BusinessException(AuthMessages.LOGIN_FAILED));
    }
}
