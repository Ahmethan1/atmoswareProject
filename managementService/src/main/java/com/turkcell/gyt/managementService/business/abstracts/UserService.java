package com.turkcell.gyt.managementService.business.abstracts;

import com.turkcell.gyt.managementService.core.dtos.request.RegisterRequest;
import com.turkcell.gyt.managementService.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    void register(RegisterRequest request);
    User findByUsername(String email);
}
