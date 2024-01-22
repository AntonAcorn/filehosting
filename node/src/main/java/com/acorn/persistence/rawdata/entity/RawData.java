package com.acorn.persistence.rawdata.entity;

import com.acorn.persistence.AutoIncrementedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "raw_data")
@FieldNameConstants
public class RawData extends AutoIncrementedEntity {


}
