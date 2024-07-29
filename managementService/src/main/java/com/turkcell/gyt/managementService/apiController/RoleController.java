package com.turkcell.gyt.managementService.apiController;

import com.turkcell.gyt.managementService.business.abstracts.RoleService;
import com.turkcell.gyt.managementService.core.dtos.request.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.GetByIdRoleResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.UUID;

@RestController
@RequestMapping("managementservice/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/add")
    public CreatedRoleResponse add(@Valid @RequestBody CreateRoleRequest createRoleRequest){

        return this.roleService.createRole(createRoleRequest);
    }
    @GetMapping("/getById/{id}")
    public GetByIdRoleResponse getById(@PathVariable UUID id){

        return this.roleService.getById(id);
    }
}
