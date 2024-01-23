package com.acorn.persistence.rawdata.entity;

import com.acorn.persistence.AutoIncrementedEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "raw_data")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class RawData extends AutoIncrementedEntity {

    @Column(name = "update")
    @Type(type = "jsonb")
    private Update update;

}

