package com.turkcell.gyt.managementService.business.concretes;

import com.turkcell.gyt.managementService.business.abstracts.RoleService;
import com.turkcell.gyt.managementService.core.dtos.request.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.GetByIdRoleResponse;
import com.turkcell.gyt.managementService.core.utilitiy.mapper.RoleMapper;
import com.turkcell.gyt.managementService.dataAccess.RoleRepository;
import com.turkcell.gyt.managementService.entity.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

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
}
