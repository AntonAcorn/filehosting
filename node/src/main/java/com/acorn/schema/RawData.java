package com.acorn.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder(toBuilder = true)
public class RawData extends Schema {

    public Long id;
    public Update update;

    @Override
    public Long getId() {
        return super.getId();
    }
}
