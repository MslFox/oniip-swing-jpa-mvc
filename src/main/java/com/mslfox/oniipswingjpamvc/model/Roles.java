package com.mslfox.oniipswingjpamvc.model;

public enum Roles {
    ADMIN("Администратор"),
    USER("Пользователь"),
    GUEST("Гость"),
    NO_ROLE("Не установлено");
    private final String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}