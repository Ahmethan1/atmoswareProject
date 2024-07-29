package com.turkcell.gyt.managementService.apiController;

import com.turkcell.gyt.managementService.business.abstracts.UserService;
import com.turkcell.gyt.managementService.core.dtos.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }
}
