package com.turkcell.gyt.managementService.dataAccess;

import com.turkcell.gyt.managementService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
    Role findRoleById(UUID id);

}
