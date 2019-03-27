package com.cqx.agent;

import com.cqx.jmx.util.JMXFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MonitorAndManagementAgent
 *
 * @author chenqixu
 */
public class MonitorAndManagementAgent {
    private static Logger logger = LoggerFactory.getLogger(MonitorAndManagementAgent.class);

    public static void start(int port) {
        JMXFactory.startJMX("MonitorAndManagement", new MonitorAndManagement(), port);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            int port = 1099;
            try {
                port = Integer.valueOf(args[0]);
                start(port);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            logger.info("no enough args.");
        }
    }
}
