package com.turkcell.gyt.managementService.business.concretes;

import com.turkcell.gyt.managementService.business.abstracts.UserService;
import com.turkcell.gyt.managementService.business.messages.AuthMessages;
import com.turkcell.gyt.managementService.business.rules.UserBusinessRules;
import com.turkcell.gyt.managementService.core.dtos.request.user.RegisterRequest;
import com.turkcell.gyt.managementService.core.dtos.request.user.UpdatedResgisterRequest;
import com.turkcell.gyt.managementService.core.dtos.response.user.CreatedRegisterResponse;
import com.turkcell.gyt.managementService.core.dtos.response.user.UpdatedRegisterResponse;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.types.BusinessException;
import com.turkcell.gyt.managementService.core.utilitiy.mapper.UserMapper;
import com.turkcell.gyt.managementService.dataAccess.UserRepository;
import com.turkcell.gyt.managementService.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private UserBusinessRules userBusinessRules;

    @Override
    public CreatedRegisterResponse register(RegisterRequest request) {
        //iş kodu null değer dönderdiği için yoruma aldın bakmayı unutma
        //this.userBusinessRules.isEmailCanNotBeDuplicated(request.getEmail());

        User user = this.userMapper.registerRequestToUserEntity(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedDate(LocalDateTime.now());

        User savedUser =this.userRepository.save(user);

        return this.userMapper.userToCreatedRegisterResponse(savedUser);

    }

    @Override
    public User findByUsername(String email) {

        return this.userRepository.findUserByEmail(email).orElseThrow();
    }

    @Override
    public UpdatedRegisterResponse update(UpdatedResgisterRequest updatedResgisterRequest) {
        this.userBusinessRules.isExistByEmail(updatedResgisterRequest.getEmail());

        User user = this.userMapper.updateRegisterRequestToUser(updatedResgisterRequest);

        String encodedPassword = passwordEncoder.encode(updatedResgisterRequest.getPassword());
        user.setPassword(encodedPassword);

        User updatedUser = this.userRepository.save(user);

        return this.userMapper.userToUpdatedRegisterResponse(updatedUser);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(()-> new BusinessException(AuthMessages.LOGIN_FAILED));
    }
}
