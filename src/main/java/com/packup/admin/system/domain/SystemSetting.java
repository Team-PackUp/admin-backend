package com.packup.admin.system.domain;

import com.packup.admin.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "system_setting")
public class SystemSetting extends BaseEntity {

    @Column(name = "language", nullable = false, length = 20)
    private String language;

    public void updateLanguage(String language) {
        this.language = language;
    }
}