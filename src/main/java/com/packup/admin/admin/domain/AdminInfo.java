package com.packup.admin.admin.domain;

import com.packup.admin.common.domain.BaseEntity;
import com.packup.admin.common.enums.YnType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminInfo extends BaseEntity {

    @Column(name = "admin_id", nullable = false, unique = true, length = 255)
    private String adminId;

    @Column(name = "admin_password", nullable = false, length = 255)
    private String adminPassword;

    @Column(name = "auth_level", nullable = false)
    private Short authLevel = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "delete_flag", columnDefinition = "yn_enum", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private YnType deleteFlag = YnType.N;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
