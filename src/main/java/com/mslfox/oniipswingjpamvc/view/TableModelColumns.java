package com.mslfox.oniipswingjpamvc.view;

import com.mslfox.oniipswingjpamvc.model.UserCredentialDTO;
import lombok.Getter;

import static com.mslfox.oniipswingjpamvc.utility.DateTimeFormatter.formatDateTime;


public enum TableModelColumns {
    ID("ID", UserCredentialDTO::getId),
    LOGIN("LOGIN", UserCredentialDTO::getLogin),
    PASSWORD("PASSWORD", UserCredentialDTO::getPassword),
    EMAIL("EMAIL", UserCredentialDTO::getEmail),
    IP("IP(IPv4)", UserCredentialDTO::getIp),
    CREATION_DATE("CREATION DATE", (userCredentialDTO) -> formatDateTime(userCredentialDTO.getCreationDate())),
    EDIT_DATE("EDIT DATE", (userCredentialDTO) -> formatDateTime(userCredentialDTO.getEditDate())),
    ACCESS_LEVEL("ACCESS LEVEL", UserCredentialDTO::getRole);
    private final String columnName;
    @Getter
    private final Command<UserCredentialDTO> command;

    TableModelColumns(String columnName, Command<UserCredentialDTO> gettingColumnValueCommand) {
        this.columnName = columnName;
        this.command = gettingColumnValueCommand;
    }

    @Override
    public String toString() {
        return columnName;
    }
}
