package com.turkcell.gyt.managementService.apiController;

import com.turkcell.gyt.managementService.business.abstracts.UserService;
import com.turkcell.gyt.managementService.core.dtos.request.user.RegisterRequest;
import com.turkcell.gyt.managementService.core.dtos.response.user.CreatedRegisterResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("managementservice/api/v1/users")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public CreatedRegisterResponse register(@RequestBody RegisterRequest request) {
        return this.userService.register(request);
    }
}
