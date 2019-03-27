package com.cqx.server;

import com.cqx.agent.bean.AgentResult;
import com.cqx.jmx.util.JMXClientFactory;

/**
 * MonitorAndManagementClient
 *
 * @author chenqixu
 */
public class MonitorAndManagementClient {
    private static final String beanname = "MonitorAndManagement";
    private int port;
    private String ip;

    private MonitorAndManagementClient() {
    }

    public static MonitorAndManagementClientParam newbuilder() {
        return new MonitorAndManagementClientParam();
    }

    private void setPort(int port) {
        this.port = port;
    }

    private void setIp(String ip) {
        this.ip = ip;
    }

    private void check() {
        if (port <= 0 || ip == null || ip.length() == 0) {
            throw new NullPointerException("port or ip is null!");
        }
    }

    public AgentResult exec(String cmd) {
        check();
        JMXClientFactory.JMXClientUtil jmxClientUtil = JMXClientFactory.startJMXClient(beanname, ip, port);
        String resultCode = jmxClientUtil.invoke("exec", new Object[]{cmd}, new String[]{"java.lang.String"});
        String successLog = jmxClientUtil.getAttributeByName("SuccessLog");
        String errLog = jmxClientUtil.getAttributeByName("ErrLog");
        return new AgentResult(resultCode, successLog, errLog);
    }

    public AgentResult execs(String cmd) {
        check();
        JMXClientFactory.JMXClientUtil jmxClientUtil = JMXClientFactory.startJMXClient(beanname, ip, port);
        String resultCode = jmxClientUtil.invoke("execs", new Object[]{cmd}, new String[]{"java.lang.String"});
        String successLog = jmxClientUtil.getAttributeByName("SuccessLog");
        String errLog = jmxClientUtil.getAttributeByName("ErrLog");
        return new AgentResult(resultCode, successLog, errLog);
    }

    public AgentResult exec(String[] cmd, String path) {
        check();
        JMXClientFactory.JMXClientUtil jmxClientUtil = JMXClientFactory.startJMXClient(beanname, ip, port);
        AgentResult agentResult = jmxClientUtil.invoke("exec", new Object[]{cmd, path}, new String[]{String[].class.getName(), "java.lang.String"});
        return agentResult;
    }

    public static class MonitorAndManagementClientParam {

        private MonitorAndManagementClient monitorAndManagementClient;

        private MonitorAndManagementClientParam() {
            monitorAndManagementClient = new MonitorAndManagementClient();
        }

        public MonitorAndManagementClient build() {
            return monitorAndManagementClient;
        }

        public MonitorAndManagementClientParam setPort(int port) {
            monitorAndManagementClient.setPort(port);
            return this;
        }

        public MonitorAndManagementClientParam setIp(String ip) {
            monitorAndManagementClient.setIp(ip);
            return this;

        }
    }
}
