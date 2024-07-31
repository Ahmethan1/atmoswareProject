package com.turkcell.gyt.managementService.business.abstracts;

import com.turkcell.gyt.managementService.core.dtos.request.RegisterRequest;
import com.turkcell.gyt.managementService.core.dtos.response.CreatedRegisterResponse;
import com.turkcell.gyt.managementService.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    CreatedRegisterResponse register(RegisterRequest request);
    User findByUsername(String email);
}
