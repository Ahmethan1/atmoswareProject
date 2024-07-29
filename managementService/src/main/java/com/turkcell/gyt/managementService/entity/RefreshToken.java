package com.turkcell.gyt.managementService.entity;

import com.turkcell.gyt.managementService.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refresh_tokens")
public class RefreshToken extends BaseEntity<UUID> {
    private String revokedByIp;
    private LocalDateTime revokedDate;
    private String token;
    private LocalDateTime expirationDate;
    private String revokeReason;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
