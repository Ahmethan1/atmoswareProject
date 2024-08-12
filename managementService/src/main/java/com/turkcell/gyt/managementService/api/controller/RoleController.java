package com.turkcell.gyt.managementService.api.controller;

import com.turkcell.gyt.managementService.business.abstracts.RoleService;
import com.turkcell.gyt.managementService.core.dtos.request.role.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.role.GetByIdRoleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
