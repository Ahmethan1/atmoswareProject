package com.turkcell.gyt.managementService.business.rules;

import com.turkcell.gyt.managementService.business.messages.UserMessages;
import com.turkcell.gyt.managementService.core.business.abstracts.MessageService;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.types.BusinessException;
import com.turkcell.gyt.managementService.dataAccess.UserRepository;
import com.turkcell.gyt.managementService.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;
    private final MessageService messageService;

    public User isUserExistById(UUID id){
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new BusinessException(messageService.getMessage(UserMessages.USER_NOT_FOUND));
        }
        return optionalUser.get();
    }

    public User isExistByEmail(String email){
        Optional<User> user = this.userRepository.findUserByEmail(email);
        if (user.isEmpty()){
            throw new BusinessException(messageService.getMessage(UserMessages.USER_NOT_FOUND));
        }
        return user.get();
    }

    public User isEmailCanNotBeDuplicated(String email){
        Optional<User> user = this.userRepository.findUserByEmail(email);
        if (user.isPresent()){
            throw new BusinessException(messageService.getMessage(UserMessages.USER_ALREADY_EXISTS));
        }
        return user.get();
    }

}
