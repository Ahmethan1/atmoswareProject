package com.turkcell.gyt.managementService.business.concretes;

import com.turkcell.gyt.managementService.business.abstracts.RoleService;
import com.turkcell.gyt.managementService.business.rules.RoleBusinessRules;
import com.turkcell.gyt.managementService.core.dtos.request.role.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.role.GetByIdRoleResponse;
import com.turkcell.gyt.managementService.core.utilitiy.mapper.RoleMapper;
import com.turkcell.gyt.managementService.dataAccess.RoleRepository;
import com.turkcell.gyt.managementService.entity.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    private RoleBusinessRules roleBusinessRules;

    @Override
    @Transactional
    public CreatedRoleResponse createRole(CreateRoleRequest createRoleRequest) {
        Role role = this.roleMapper.createRoleRequestToRoleEntity(createRoleRequest);

        Role saveRole = this.roleRepository.save(role);

        return this.roleMapper.roleEntityToCreateRoleResponse(saveRole);
    }

    @Override
    public GetByIdRoleResponse getById(UUID id) {
        Optional<Role> role = this.roleRepository.findById(id);

        return this.roleMapper.roleEntityToGetByIdRoleResponse(role.get());
    }

    @Override
    public Role getRoleById(UUID id) {
        return this.roleBusinessRules.isRoleExistById(id);
    }

    @Override
    public String getRoleNameById(UUID id) {
        this.roleBusinessRules.isRoleExistById(id);
        Role role = this.roleRepository.findById(id).orElse(null);
        assert role != null;
        return role.getName();
    }
}
