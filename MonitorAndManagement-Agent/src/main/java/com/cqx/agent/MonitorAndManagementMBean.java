package com.cqx.agent;

import com.cqx.agent.bean.AgentResult;

/**
 * MonitorAndManagementMBean
 *
 * @author chenqixu
 */
public interface MonitorAndManagementMBean {
    String exec(String cmd);

    String execs(String cmd);

    AgentResult exec(String[] cmd, String path);
}
