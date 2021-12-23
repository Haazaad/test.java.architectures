package ru.haazad.java.architectures.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.haazad.java.architectures.enums.CommandAction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommandDto {

    private CommandAction action;
    private Object entity;

}
