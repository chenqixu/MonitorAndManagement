package com.cqx.server;

import com.cqx.agent.bean.AgentResult;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringTokenizer;

public class MonitorAndManagementClientTest {

    private static Logger logger = LoggerFactory.getLogger(MonitorAndManagementClientTest.class);
    private MonitorAndManagementClient monitorAndManagementClient200;
    private MonitorAndManagementClient monitorAndManagementClient201;
    private MonitorAndManagementClient monitorAndManagementClient202;

    @Before
    public void setUp() {
        monitorAndManagementClient200 = MonitorAndManagementClient
                .newbuilder().setIp("10.1.8.200").setPort(10999).build();
        monitorAndManagementClient201 = MonitorAndManagementClient
                .newbuilder().setIp("10.1.8.201").setPort(10999).build();
        monitorAndManagementClient202 = MonitorAndManagementClient
                .newbuilder().setIp("10.1.8.202").setPort(10999).build();
    }

    @Test
    public void exec() {
        AgentResult agentResult = monitorAndManagementClient200.exec("su - zookeeper -c 'ls -l'");
        logger.info("resultCode：{}", agentResult.getResultCode());
        logger.info("successLog：{}", agentResult.getSuccessLog());
        logger.info("errLog：{}", agentResult.getErrLog());
//        System.out.println(monitorAndManagementClient200.exec("/home/zookeeper/2181/zookeeper-3.4.6/bin/zkServer.sh status"));
//        System.out.println(monitorAndManagementClient200.exec("/home/zookeeper/2182/zookeeper-3.4.6/bin/zkServer.sh status"));
    }

    @Test
    public void execs() {
        System.out.println(monitorAndManagementClient200.execs("su - zookeeper -c 'pwd'"));
    }

    @Test
    public void start2182() {
        String[] cmd = {"su", "-", "zookeeper", "-c", "/home/zookeeper/2182/zookeeper-3.4.6/bin/zkServer.sh status"};
        AgentResult agentResult = monitorAndManagementClient200.exec(cmd, "/home/zookeeper/2182/zookeeper-3.4.6/bin/");
        logger.info("resultCode：{}", agentResult.getResultCode());
        logger.info("successLog：{}", agentResult.getSuccessLog());
        logger.info("errLog：{}", agentResult.getErrLog());
//        System.out.println(monitorAndManagementClient200.exec(cmd, "/home/zookeeper/2182/zookeeper-3.4.6/bin/"));
//        System.out.println(monitorAndManagementClient200.exec("su - zookeeper -c \"/home/zookeeper/2182/zookeeper-3.4.6/bin/zkServer.sh stop\"", "/home/zookeeper/2182/zookeeper-3.4.6/bin/"));
//        System.out.println(monitorAndManagementClient201.exec("/home/zookeeper/2185/zookeeper-3.4.6/bin/zkServer.sh stop", "/home/zookeeper/2185/zookeeper-3.4.6/bin/"));
//        System.out.println(monitorAndManagementClient202.exec("/home/zookeeper/2185/zookeeper-3.4.6/bin/zkServer.sh stop", "/home/zookeeper/2185/zookeeper-3.4.6/bin/"));
    }

    @Test
    public void stop2185() {
//        System.out.println(monitorAndManagementClient200.exec("/home/zookeeper/2185/zookeeper-3.4.6/bin/zkServer.sh stop", "/home/zookeeper/2185/zookeeper-3.4.6/bin/"));
//        System.out.println(monitorAndManagementClient201.exec("/home/zookeeper/2185/zookeeper-3.4.6/bin/zkServer.sh stop", "/home/zookeeper/2185/zookeeper-3.4.6/bin/"));
//        System.out.println(monitorAndManagementClient202.exec("/home/zookeeper/2185/zookeeper-3.4.6/bin/zkServer.sh stop", "/home/zookeeper/2185/zookeeper-3.4.6/bin/"));
    }

    @Test
    public void status() {
        String path = "/home/zookeeper/port/zookeeper-3.4.6/bin/";
        String[] port_arr = {"2181", "2182", "2183", "2184", "2185", "2186"};
//        String[] port_arr = {"2182"};
        for (String port : port_arr) {
            String _path = path.replaceAll("port", port);
            String[] cmd = {"su", "zookeeper", "-c", _path + "zkServer.sh status"};
            AgentResult agentResult = monitorAndManagementClient200.exec(cmd, _path);
            logger.info("resultCode：{}", agentResult.getResultCode());
            logger.info("successLog：{}", agentResult.getSuccessLog());
            logger.info("errLog：{}", agentResult.getErrLog());
        }
    }

    @Test
    public void splitTest() {
        StringTokenizer itr = new StringTokenizer("3,aaa,b,3,w,q,A c");
        while (itr.hasMoreTokens()) {
            System.out.println(itr.nextToken());
        }
    }

    @Test
    public void write() {
        StringBuilder sb = new StringBuilder();
        write(sb);
        System.out.println(sb);
    }

    public void write(StringBuilder sb) {
        sb.append("aaa");
    }
}