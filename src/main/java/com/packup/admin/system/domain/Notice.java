package com.packup.admin.system.domain;

import com.packup.admin.common.domain.BaseEntity;
import com.packup.admin.common.enums.YnType;
import com.packup.admin.common.util.JsonbConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "notice")
public class Notice extends BaseEntity {

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> content;

    @Column(name = "admin_seq", nullable = false)
    private Long adminSeq;

    @Column(name = "notice_type", nullable = false, length = 6)
    private String noticeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "fcm_flag", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private YnType fcmFlag = YnType.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "delete_flag", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private YnType deleteFlag = YnType.N;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void update(String title, Map<String, Object> content, String noticeType, YnType fcmFlag) {
        this.title = title;
        this.content = content;
        this.noticeType = noticeType;
        this.fcmFlag = fcmFlag;
        this.updatedAt = LocalDateTime.now();
    }

    private Notice(String title, Map<String, Object> content, Long adminSeq, String noticeType, YnType fcmFlag, YnType deleteFlag) {
        this.title = title;
        this.content = content;
        this.adminSeq = adminSeq;
        this.noticeType = noticeType;
        this.fcmFlag = fcmFlag;
        this.deleteFlag = deleteFlag;
    }

    public static Notice of(String title, Map<String, Object> content, Long adminSeq, String noticeType, YnType fcmFlag, YnType deleteFlag) {
        return new Notice(title, content, adminSeq, noticeType, fcmFlag, deleteFlag );
    }
}
