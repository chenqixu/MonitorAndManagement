package com.cqx.webui.toolui.service;

import com.cqx.webui.toolui.bean.KafkaSinglePartitionSyncBean;
import com.cqx.webui.toolui.bean.TaskBean;
import com.cqx.webui.toolui.bean.ToolUIRequestBean;
import com.cqx.webui.toolui.bean.ToolUIResponseBean;
import com.cqx.webui.toolui.dao.TaskServiceDao;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务管理服务
 *
 * @author chenqixu
 */
@EnableAutoConfiguration
@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Resource
    private TaskServiceDao taskServiceDao;

    /**
     * 创建任务
     *
     * @param kafkaSinglePartitionSyncBean
     * @return
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ToolUIResponseBean createTask(@RequestBody KafkaSinglePartitionSyncBean kafkaSinglePartitionSyncBean) {
        return taskServiceDao.createTask(kafkaSinglePartitionSyncBean);
    }

    /**
     * 根据类型查询任务
     *
     * @param toolUIRequestBean
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/queryByType", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskBean> queryTaskByType(@RequestBody ToolUIRequestBean toolUIRequestBean) throws TException {
        return taskServiceDao.queryTaskByType(toolUIRequestBean.getTask_type());
    }
}
