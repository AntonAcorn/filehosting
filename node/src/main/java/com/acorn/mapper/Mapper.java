package com.acorn.mapper;

import com.acorn.entity.BaseEntity;
import com.acorn.model.ISchema;

public abstract class Mapper <S extends ISchema, E extends BaseEntity>{

    public abstract S convertToModel(E entity);

    public abstract void enrichEntityWithModelsParams(E destination, S source);
}
