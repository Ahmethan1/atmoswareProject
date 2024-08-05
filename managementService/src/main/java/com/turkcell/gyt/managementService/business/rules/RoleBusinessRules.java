package com.turkcell.gyt.managementService.business.rules;

import com.turkcell.gyt.managementService.business.messages.RoleMessages;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.types.BusinessException;
import com.turkcell.gyt.managementService.dataAccess.RoleRepository;
import com.turkcell.gyt.managementService.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@AllArgsConstructor
public class RoleBusinessRules {
    private RoleRepository roleRepository;

    public Role isRoleExistById(UUID id) {
        Optional<Role> role = this.roleRepository.findById(id);

        if (role.isEmpty()) {
            throw new BusinessException(RoleMessages.ROLE_NOT_FOUND);
        }

        return role.get();
    }
}
