package com.cqx.webui.zookeeper;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperateStatusTest {

    private static Logger logger = LoggerFactory.getLogger(OperateStatusTest.class);

    @Test
    public void valueOf() {
        OperateStatus operateStatus = OperateStatus.valueOf("OP_STATUS");
        logger.info("code：{}，desc：{}", operateStatus.getCode(), operateStatus.getDesc());
    }

    @Test
    public void parser() {
        OperateStatus operateStatus = OperateStatus.parser("stop");
        logger.info("code：{}，desc：{}", operateStatus.getCode(), operateStatus.getDesc());
    }
}