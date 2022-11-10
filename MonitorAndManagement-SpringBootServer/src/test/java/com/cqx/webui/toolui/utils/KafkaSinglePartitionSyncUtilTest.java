package com.cqx.webui.toolui.utils;

import com.cqx.common.utils.config.YamlParser;
import com.cqx.webui.toolui.bean.KafkaSinglePartitionSyncBean;
import com.cqx.webui.toolui.dao.TaskServiceDao;
import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaSinglePartitionSyncUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(KafkaSinglePartitionSyncUtilTest.class);

    @Resource
    private TaskServiceDao taskServiceDao;

    @Test
    public void getParams() {
        KafkaSinglePartitionSyncBean kafkaSinglePartitionSyncBean = new KafkaSinglePartitionSyncBean();
        kafkaSinglePartitionSyncBean.setTask_name("kafka_single_partition_sync_res_piece_type_goods_consume");
        kafkaSinglePartitionSyncBean.setTask_type("kafka_single_partition_sync");
        kafkaSinglePartitionSyncBean.setTable_name("rl_res_piece_type_goods_consume");
        kafkaSinglePartitionSyncBean.setPrimary_keys("goods_inner_id,home_city");
        kafkaSinglePartitionSyncBean.setAnalyze_hour(3);
        kafkaSinglePartitionSyncBean.setRedis_conf("redis-10.48.134.140-6380");
        kafkaSinglePartitionSyncBean.setAdbBean("adb-lable_core");
        kafkaSinglePartitionSyncBean.setSrcBean("edc_cfg");

        Map params = new HashMap<>();
        // 查询Redis配置
        Map redis = taskServiceDao.queryConf(kafkaSinglePartitionSyncBean.getRedis_conf());
        // 查询adbBean配置
        Map adbBean = taskServiceDao.queryConf(kafkaSinglePartitionSyncBean.getAdbBean());
        adbBean.put("name", "adbBean");
        // 查询srcBean配置
        Map srcBean = taskServiceDao.queryConf(kafkaSinglePartitionSyncBean.getSrcBean());
        srcBean.put("name", "srcBean");
        // 查询默认的kafka配置
        Map kafkaParams = taskServiceDao.queryConf("kafka-217");

        Map kafka = new HashMap<>();
        kafka.put("KafkaSinglePartitionSyncADB", kafkaParams);
        params.putAll(redis);
        params.putAll(kafka);
        params.put("dbbeans", new Map[]{adbBean, srcBean});

        // 拼接yaml文件
        KafkaSinglePartitionSyncUtil kafkaSinglePartitionSyncUtil = new KafkaSinglePartitionSyncUtil(kafkaSinglePartitionSyncBean.getTask_name(), params, kafkaSinglePartitionSyncBean);
        logger.info("{}", YamlParser.builder().dump(kafkaSinglePartitionSyncUtil.getParams()));
    }

    @Test
    public void queryTaskByType() throws TException {
        taskServiceDao.queryTaskByType("kafka_single_partition_sync");
    }
}