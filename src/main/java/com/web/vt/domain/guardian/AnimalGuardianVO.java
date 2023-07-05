package com.web.vt.domain.guardian;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.web.vt.domain.common.BaseVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AnimalGuardianVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -2010468697125940197L;

    private Long id;
    private String name;
    private String contact;
    private String address;
    private String remark;

    public AnimalGuardianVO(AnimalGuardian entity) {
        id = entity.id();
        name = entity.name();
        contact = entity.contact();
        address = entity.address();
        remark = entity.remark();
        createBy(entity.createdBy());
        createdAt(entity.createdAt());
        updatedBy(updatedBy());
        updatedAt(entity.updatedAt());
    }
}