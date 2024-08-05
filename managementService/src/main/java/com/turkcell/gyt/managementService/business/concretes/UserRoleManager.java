package com.turkcell.gyt.managementService.business.concretes;

import com.turkcell.gyt.managementService.business.abstracts.RoleService;
import com.turkcell.gyt.managementService.business.abstracts.UserRoleService;
import com.turkcell.gyt.managementService.business.abstracts.UserService;
import com.turkcell.gyt.managementService.core.dtos.request.userRole.CreateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.request.userRole.UpdateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedUserRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.userRole.UpdatedUserRoleResponse;
import com.turkcell.gyt.managementService.core.utilitiy.mapper.UserRoleMapper;
import com.turkcell.gyt.managementService.dataAccess.UserRoleRepository;
import com.turkcell.gyt.managementService.entity.Role;
import com.turkcell.gyt.managementService.entity.User;
import com.turkcell.gyt.managementService.entity.UserRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserRoleManager implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public CreatedUserRoleResponse add(CreateUserRoleRequest createUserRoleRequest) {
        UserRole userRole =this.userRoleMapper.createUserRoleRequestToUserRoleEntity(createUserRoleRequest);

        UserRole savedUserRole=this.userRoleRepository.save(userRole);

        return this.userRoleMapper.userRoleToCreatedRoleResponse(savedUserRole);
    }

    @Override
    public UpdatedUserRoleResponse update(UpdateUserRoleRequest updateUserRoleRequest) {
        Optional<UserRole> optionalUserRole = this.userRoleRepository.findById(updateUserRoleRequest.getId());

        UserRole userRole =this.userRoleMapper.updatedUserRoleRequestToUserRoleEntity(updateUserRoleRequest);

        UserRole updatedUserRole= this.userRoleRepository.save(userRole);

        return this.userRoleMapper.userRoleToUpdatedUserRoleResponse(updatedUserRole);
    }

    @Override
    public UUID getRoleByUser(User user) {
        UserRole userRole = this.userRoleRepository.findUserRoleByUserId(user.getId());
        return userRole.getRole().getId();
    }

    @Override
    public UUID getRoleIdByUserId(UUID userId) {
        UserRole userRole = this.userRoleRepository.findUserRoleByUserId(userId);
        return userRole.getRole().getId();
    }
}
