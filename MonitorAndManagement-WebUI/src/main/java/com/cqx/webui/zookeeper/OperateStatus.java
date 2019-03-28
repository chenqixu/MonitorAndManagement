package com.cqx.webui.zookeeper;

/**
 * OperateStatus
 *
 * @author chenqixu
 */
public enum OperateStatus {
    SUCCESS("success", "执行成功"),
    FAIL("fail", "执行失败"),
    STATUS_RUN("status_run", "运行中"),
    STATUS_STOP("status_stop", "已停止"),
    OP_START("start", "启动"),
    OP_STOP("stop", "停止"),
    OP_RESTART("restart", "重启"),
    OP_STATUS("status", "状态");

    private final String code;
    private final String desc;

    OperateStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OperateStatus parser(String value) {
        for (OperateStatus operateStatus : OperateStatus.values()) {
            if (operateStatus.getCode().equals(value)) {
                return operateStatus;
            }
        }
        return null;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getCode() {
        return code;
    }
}
