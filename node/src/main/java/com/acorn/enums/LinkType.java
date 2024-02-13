package com.acorn.enums;

public enum LinkType {
    GET_PHOTO("/get-photo"),
    GET_DOC("/get-document");

    private final String linkType;

    LinkType(String linkType) {
        this.linkType = linkType;
    }

    @Override
    public String toString() {
        return linkType;
    }
}
