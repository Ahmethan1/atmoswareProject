package com.turkcell.gyt.managementService.entity;

import com.turkcell.gyt.managementService.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity<UUID> implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role",cascade =CascadeType.ALL)
    private List<UserRole> userRoles;

    @Override
    public String getAuthority() {
        return name.toLowerCase();
    }

}
