package com.turkcell.gyt.managementService.dataAccess;

import com.turkcell.gyt.managementService.entity.User;
import com.turkcell.gyt.managementService.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    //UserRole findUserRoleByUser(UUID userId);
    UserRole findUserRoleByUserId(UUID userId);

}
