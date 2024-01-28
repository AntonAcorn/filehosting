package com.acorn.model;

import com.acorn.entity.AccountState;
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

    private Long telegramId;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDateTime dateCreated;

    private AccountState accountState;

    private boolean isDeleted = false;

    private boolean isActive = false;
}
