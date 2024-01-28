package com.acorn.service.comands;

public enum Commands {
    START("/start"),
    CANCEL("/cancel"),
    REGISTRATION("/register"),
    HELP("/help");

    private final String cmd;

    Commands(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return cmd;
    }
}
