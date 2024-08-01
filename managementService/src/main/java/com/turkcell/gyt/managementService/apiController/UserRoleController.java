package com.turkcell.gyt.managementService.apiController;

import com.turkcell.gyt.managementService.business.abstracts.UserRoleService;
import com.turkcell.gyt.managementService.core.dtos.request.userRole.CreateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.request.userRole.UpdateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedUserRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.userRole.UpdatedUserRoleResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("managementservice/api/v1/userrole")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping("/add")
    public CreatedUserRoleResponse add(@Valid @RequestBody CreateUserRoleRequest createUserRoleRequest){
        return this.userRoleService.add(createUserRoleRequest);
    }

    @PutMapping("/update")
    public UpdatedUserRoleResponse update(@Valid @RequestBody UpdateUserRoleRequest updateUserRoleRequest){
        return this.userRoleService.update(updateUserRoleRequest);
    }


}
