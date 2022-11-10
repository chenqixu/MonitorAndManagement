package com.cqx.webui.toolui.bean;

/**
 * ToolUIResponseBean
 *
 * @author chenqixu
 */
public class ToolUIResponseBean {
    private int ret;// 0：成功；1：失败
    private String desc;// 备注

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
