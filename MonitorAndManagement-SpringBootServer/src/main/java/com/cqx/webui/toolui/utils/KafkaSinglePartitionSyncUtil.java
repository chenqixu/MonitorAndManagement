package com.cqx.webui.toolui.utils;

import com.cqx.webui.toolui.bean.KafkaSinglePartitionSyncBean;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaSinglePartitionSyncUtil
 *
 * @author chenqixu
 */
public class KafkaSinglePartitionSyncUtil {
    private Map params = new HashMap<>();

    public KafkaSinglePartitionSyncUtil(String topology_name, Map params, KafkaSinglePartitionSyncBean kafkaSinglePartitionSyncBean) {
        jstorm();
        topology(topology_name);
        spout();
        param(params, kafkaSinglePartitionSyncBean);
    }

    private void jstorm() {
        Map jstorm = new HashMap<>();
        jstorm.put("nimbus_host", "10.45.179.119");
        jstorm.put("nimbus_thrift_port", 17627);
        jstorm.put("storm_zookeeper_servers", new String[]{"10.48.134.152", "10.48.134.153", "10.48.134.154"});
        jstorm.put("storm_zookeeper_port", 2184);
        jstorm.put("storm_zookeeper_root", "/streampaas-6-jstorm");
        params.put("jstorm", jstorm);
    }

    private void topology(String topology_name) {
        Map topology = new HashMap<>();
        topology.put("worker_num", 1);
        topology.put("ack_num", 0);
        topology.put("worker_memory", 4294967296L);
        topology.put("cpu_slotNum", 100);
        topology.put("jvm_options", "");
        topology.put("name", topology_name);
        topology.put("ip", "10.45.179.119");
        params.put("topology", topology);
    }

    private void spout() {
        Map s1 = new HashMap<>();
        s1.put("name", "KafkaSinglePartitionSyncADB");
        s1.put("packagename", "com.nl.realtime.kafkatojdbc.spout");
        s1.put("parall", 1);
        Map s2 = new HashMap<>();
        s2.put("name", "JDBCAnalyzeSpout");
        s2.put("packagename", "com.nl.realtime.kafkatojdbc.spout");
        s2.put("parall", 1);
        params.put("spout", new Map[]{s1, s2});
    }

    private void param(Map _params, KafkaSinglePartitionSyncBean kafkaSinglePartitionSyncBean) {
        Map param = new HashMap<>();
        param.putAll(_params);
        // 表名
        param.put("table_name", kafkaSinglePartitionSyncBean.getTable_name());
        // 主键
        param.put("primary_keys", kafkaSinglePartitionSyncBean.getPrimary_keys());
        // 下发分组字段
        param.put("send_pks", kafkaSinglePartitionSyncBean.getPrimary_keys());
        // 定时信息收集时间-单位小时
        param.put("analyze_hour", Integer.valueOf(kafkaSinglePartitionSyncBean.getAnalyze_hour()));
        // 写入原始OGG话题
        param.put("ogg.topic", kafkaSinglePartitionSyncBean.getOgg_topic());
        // 写入扁平化话题
        param.put("flat.topic", kafkaSinglePartitionSyncBean.getFlat_topic());
        params.put("param", param);
    }

    public Map getParams() {
        return params;
    }
}
