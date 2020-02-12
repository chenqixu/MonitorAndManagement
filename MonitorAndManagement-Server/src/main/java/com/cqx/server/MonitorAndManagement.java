package com.cqx.server;

import com.cqx.agent.bean.AgentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MonitorAndManagement
 *
 * @author chenqixu
 */
public class MonitorAndManagement {
    private static final Logger logger = LoggerFactory.getLogger(MonitorAndManagement.class);

    public static void main(String[] args) {
        // 3个参数：ip、端口、命令
        if (args.length != 3) {
            logger.error("please check you input param , you need input [ip、port、cmd] . ");
        } else {
            String ip = args[0];
            String port = args[1];
            String[] cmd = {args[2]};
            MonitorAndManagementClient winClient
                    = MonitorAndManagementClient
                    .newbuilder().setIp(ip).setPort(Integer.valueOf(port)).build();
            AgentResult agentResult = winClient.exec(cmd, "C:\\");
            logger.info("resultCode：{}", agentResult.getResultCode());
            logger.info("successLog：{}", agentResult.getSuccessLog());
            logger.info("errLog：{}", agentResult.getErrLog());
        }
    }
}
