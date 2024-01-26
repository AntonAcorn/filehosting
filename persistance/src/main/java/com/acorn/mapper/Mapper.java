package com.acorn.mapper;

import com.acorn.entity.BaseEntity;
import com.acorn.model.ISchema;

public abstract class Mapper <S extends ISchema, E extends BaseEntity>{

    public abstract S convertToSchema (E entity);

    public abstract void enrichEntityWithSchemaParams(E destination, S source);
}
