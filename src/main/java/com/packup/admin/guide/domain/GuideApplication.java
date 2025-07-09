package com.packup.admin.guide.domain;

import com.packup.admin.common.domain.BaseEntity;
import com.packup.admin.user.domain.UserInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "guide_application")
public class GuideApplication extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Column(name = "content", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> content;

    @Column(name = "id_document_url", nullable = false)
    private String idDocumentUrl;

    @Column(name = "status_code", nullable = false, length = 50)
    private String statusCode;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "confirm_admin_seq")
    private Long confirmedAdminSeq;

    public void updateStatus(String statusCode) {
        this.statusCode = statusCode;
    }
}
