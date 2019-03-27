package com.cqx.server.util;

/**
 * Control
 *
 * @author chenqixu
 */
public enum Control {
    start("start"),
    stop("stop"),
    status("status");
    private final String code;

    private Control(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
