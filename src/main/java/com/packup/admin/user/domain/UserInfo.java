package com.packup.admin.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import com.packup.admin.common.enums.YnType;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_info")
public class UserInfo  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_admin_seq_gen")
    @SequenceGenerator(
            name = "user_admin_seq_gen",
            sequenceName = "user_admin_seq",
            allocationSize = 1
    )
    private Long seq;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "join_type", nullable = false, length = 1)
    private String joinType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ban_flag", columnDefinition = "yn_enum", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private YnType banFlag = YnType.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "adult_flag", columnDefinition = "yn_enum", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private YnType adultFlag = YnType.Y;

    @Enumerated(EnumType.STRING)
    @Column(name = "withdraw_flag", columnDefinition = "yn_enum", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private YnType withdrawFlag = YnType.N;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserDetailInfo detailInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserPrefer prefer;

    @CreatedDate
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public void addDetailInfo(UserDetailInfo detailInfo) {
        this.detailInfo = detailInfo;
        detailInfo.assignUser(this);
    }

    public void addPrefer(UserPrefer prefer) {
        this.prefer = prefer;
        prefer.assignUser(this);
    }

    public void ban() {
        this.banFlag = YnType.Y;
        this.updatedAt = LocalDateTime.now();
    }

    public void unban() {
        this.banFlag = YnType.N;
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw() {
        this.withdrawFlag = YnType.Y;
        this.updatedAt = LocalDateTime.now();
    }

    public void restore() {
        this.withdrawFlag = YnType.N;
        this.updatedAt = LocalDateTime.now();
    }



}
