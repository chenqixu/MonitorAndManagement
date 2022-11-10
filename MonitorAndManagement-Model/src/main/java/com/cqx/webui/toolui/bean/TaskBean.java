package com.cqx.webui.toolui.bean;

/**
 * TaskBean
 *
 * @author chenqixu
 */
public class TaskBean {
    private String task_name;
    private String task_type;
    private String task_status;
    private String task_is_error;
    private long task_lag;
    private long task_run_time;

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getTask_is_error() {
        return task_is_error;
    }

    public void setTask_is_error(String task_is_error) {
        this.task_is_error = task_is_error;
    }

    public long getTask_lag() {
        return task_lag;
    }

    public void setTask_lag(long task_lag) {
        this.task_lag = task_lag;
    }

    public long getTask_run_time() {
        return task_run_time;
    }

    public void setTask_run_time(long task_run_time) {
        this.task_run_time = task_run_time;
    }
}
