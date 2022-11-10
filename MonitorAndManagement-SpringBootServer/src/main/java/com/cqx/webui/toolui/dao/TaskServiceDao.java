package com.cqx.webui.toolui.dao;

import backtype.storm.generated.TopologySummary;
import com.cqx.common.utils.config.YamlParser;
import com.cqx.common.utils.jstorm.TopologyUtils;
import com.cqx.webui.toolui.bean.KafkaSinglePartitionSyncBean;
import com.cqx.webui.toolui.bean.TaskBean;
import com.cqx.webui.toolui.bean.ToolUIResponseBean;
import com.cqx.webui.toolui.utils.KafkaSinglePartitionSyncUtil;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TaskServiceDao
 *
 * @author chenqixu
 */
@Repository("com.cqx.webui.toolui.dao.TaskServiceDao")
public class TaskServiceDao {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceDao.class);

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Value("${jstorm.conf}")
    private String jstorm_conf;

    public ToolUIResponseBean createTask(KafkaSinglePartitionSyncBean kafkaSinglePartitionSyncBean) {
        ToolUIResponseBean responseBean = new ToolUIResponseBean();
        // 先查询表中是否已经配置同名任务
        kafkaSinglePartitionSyncBean.getTask_name();
        String sql = "select count(1) from toolui_task where task_name=?";
        jdbcTemplate.query(sql
                , new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, kafkaSinglePartitionSyncBean.getTask_name());
                    }
                }
                , new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            responseBean.setRet(1);
                            responseBean.setDesc(String.format("任务 %s 已经存在，不需要创建", kafkaSinglePartitionSyncBean.getTask_name()));
                            logger.info("{}", responseBean.getDesc());
                        } else {
                            Map params = new HashMap<>();
//                            // 查询Redis配置
//                            Map redis = queryConf(kafkaSinglePartitionSyncBean.getRedis_conf());
//                            // 查询adbBean配置
//                            Map adbBean = queryConf(kafkaSinglePartitionSyncBean.getAdbBean());
//                            adbBean.put("name", "adbBean");
//                            // 查询srcBean配置
//                            Map srcBean = queryConf(kafkaSinglePartitionSyncBean.getSrcBean());
//                            srcBean.put("name", "srcBean");
//                            // 查询默认的kafka配置
//                            Map kafkaParams = queryConf("kafka-217");
//                            // 拼接kafka参数
//                            Map kafka = new HashMap<>();
//                            kafka.put("KafkaSinglePartitionSyncADB", kafkaParams);
//                            // 往最终map写入redis配置
//                            params.putAll(redis);
//                            // 往最终map写入kafka配置
//                            params.putAll(kafka);
//                            // 往最终map写入dbbeans配置
//                            params.put("dbbeans", new Map[]{adbBean, srcBean});



                            // 拼接yaml文件
                            KafkaSinglePartitionSyncUtil kafkaSinglePartitionSyncUtil = new KafkaSinglePartitionSyncUtil(kafkaSinglePartitionSyncBean.getTask_name(), params, kafkaSinglePartitionSyncBean);

                            LobHandler lobHandler = new DefaultLobHandler();
                            String insertSql = "insert into toolui_task(task_name,task_type,task_param) values(?,?,?)";
                            int ret = jdbcTemplate.execute(insertSql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                                @Override
                                protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                                    ps.setString(1, kafkaSinglePartitionSyncBean.getTask_name());
                                    ps.setString(2, kafkaSinglePartitionSyncBean.getTask_type());
                                    lobCreator.setClobAsString(ps, 3, YamlParser.builder().dump(kafkaSinglePartitionSyncUtil.getParams()));
                                }
                            });
                            responseBean.setRet(ret > 0 ? 0 : 1);// 大于0表示执行成功
                            responseBean.setDesc(String.format("新增任务 %s，结果：%s", kafkaSinglePartitionSyncBean.getTask_name(), ret));
                            logger.info("{}", responseBean.getDesc());
                        }
                    }
                });
        return responseBean;
    }

    /**
     * 通过名称查询配置信息
     *
     * @param conf_name
     * @return
     */
    public Map queryConf(String conf_name) {
        String queryConfSql = "select conn_param_name,conn_param_value from toolui_conn_params where conn_id in(select conn_id from toolui_conn_manager where conn_name=?)";
        Map conf = new HashMap<>();
        jdbcTemplate.query(queryConfSql
                , new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, conf_name);
                    }
                }
                , new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        conf.put(rs.getString(1), rs.getString(2));
                    }
                });
        return conf;
    }

    public List<TaskBean> queryTaskByType(String task_type) throws TException {
        List<TaskBean> taskBeanList = new ArrayList<>();
        String sql = "select task_name from toolui_task where task_type=?";
        List<String> taskList = new ArrayList<>();
        jdbcTemplate.query(sql
                , new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, task_type);
                    }
                }
                , new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        taskList.add(rs.getString(1));
                    }
                }
        );

        // 去jstorm查询运行状态
        try (TopologyUtils topologyUtils = new TopologyUtils(jstorm_conf)) {
            Map<String, TopologySummary> map = topologyUtils.getTopologiesMap();
            for (String task : taskList) {
                TaskBean taskBean = new TaskBean();
                taskBean.setTask_name(task);
                taskBean.setTask_type(task_type);
                TopologySummary topologySummary = map.get(task);
                if (topologySummary != null) {
                    logger.info("topologySummary：{}", topologySummary);
                    taskBean.setTask_status(topologySummary.get_status());
                    taskBean.setTask_run_time(topologySummary.get_uptimeSecs());
                    taskBean.setTask_is_error(topologySummary.get_errorInfo());
                } else {
                    taskBean.setTask_status("stop");
                    taskBean.setTask_is_error("无");
                }
                taskBeanList.add(taskBean);
            }
        }

        return taskBeanList;
    }
}
