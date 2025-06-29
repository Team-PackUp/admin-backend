package com.packup.admin.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.packup.admin.common.domain.BaseEntity;
import com.packup.admin.common.enums.YnType;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "user_detail_info")
public class UserDetailInfo extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", nullable = false, unique = true)
    private UserInfo user;

    private String gender;

    private String nation;

    private Integer age;

    @Column(unique = true)
    private String nickname;

    @Column(name = "fcm_token", unique = true)
    private String fcmToken;

    @Column(name = "profile_image_path")
    private String profileImagePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "marketing_flag", columnDefinition = "yn_enum", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private YnType marketingFlag = YnType.N;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "ban_reason")
    private String banReason;

    @Column(name = "ban_admin_seq")
    private Long banAdminSeq;

    public void assignUser(UserInfo userInfo) {
        this.user = userInfo;
    }

    public void updateBasicInfo(String gender, String nation, int age) {
        this.gender = gender;
        this.nation = nation;
        this.age = age;
        this.updatedAt = LocalDateTime.now();
    }

    public void recordBanInfo(String reason, Long adminSeq) {
        this.banReason = reason;
        this.banAdminSeq = adminSeq;
        this.updatedAt = LocalDateTime.now();
    }

    public void clearBanInfo() {
        this.banReason = null;
        this.banAdminSeq = null;
        this.updatedAt = LocalDateTime.now();
    }

}