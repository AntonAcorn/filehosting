package com.acorn.model;

import com.acorn.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
public class Account extends Schema {

    private String telegramId;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDateTime dateCreated;

    private RoleName roleName;

    private boolean isDeleted = false;
}
