package com.turkcell.gyt.managementService.api.controller;

import com.turkcell.gyt.managementService.business.abstracts.UserService;
import com.turkcell.gyt.managementService.core.dtos.request.user.RegisterRequest;
import com.turkcell.gyt.managementService.core.dtos.request.user.UpdatedResgisterRequest;
import com.turkcell.gyt.managementService.core.dtos.response.user.CreatedRegisterResponse;
import com.turkcell.gyt.managementService.core.dtos.response.user.UpdatedRegisterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("managementservice/api/v1/users")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public CreatedRegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return this.userService.register(request);
    }
    @PutMapping("/updateRegister")
    public UpdatedRegisterResponse updateRegister(@Valid @RequestBody UpdatedResgisterRequest updatedResgisterRequest){
        return this.userService.update(updatedResgisterRequest);
    }
}
