package com.cqx.webui.zookeeper;

import com.cqx.agent.bean.AgentResult;
import com.cqx.server.MonitorAndManagementClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * ZookeeperUI
 *
 * @author chenqixu
 */
public class ZookeeperUI {
    private static final String[] zookeeper_arr = {"2181", "2182", "2183", "2184", "2185", "2186"};
    private static final String name = "zookeeper";
    private static Logger logger = LoggerFactory.getLogger(ZookeeperUI.class);
    private MonitorAndManagementClient monitorAndManagementClient200;
    private MonitorAndManagementClient monitorAndManagementClient201;
    private MonitorAndManagementClient monitorAndManagementClient202;

    public ZookeeperUI() {
        monitorAndManagementClient200 = MonitorAndManagementClient
                .newbuilder().setIp("10.1.8.200").setPort(10999).build();
        monitorAndManagementClient201 = MonitorAndManagementClient
                .newbuilder().setIp("10.1.8.201").setPort(10999).build();
        monitorAndManagementClient202 = MonitorAndManagementClient
                .newbuilder().setIp("10.1.8.202").setPort(10999).build();
    }

    public String JSPbuilder() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < zookeeper_arr.length; i++) {
            String id = name + zookeeper_arr[i];
            String status_id = " id=\"" + id + "status\"";
            if (i % 2 == 0)
                sb.append("<tr class=\"success\"><td" + status_id + ">初始化</td>");
            else
                sb.append("<tr><td" + status_id + ">初始化</td>");
            sb.append("<td>" + id + "</td>");
            sb.append("<td><div class=\"btn-group\">");
            sb.append("<button class=\"btn\">操作</button>");
            sb.append("<button data-toggle=\"dropdown\" class=\"btn dropdown-toggle\"><span class=\"caret\"></span></button>");
            sb.append("<ul class=\"dropdown-menu\">");
            sb.append(buildLi(zookeeper_arr[i], OperateStatus.OP_START));
            sb.append(buildLi(zookeeper_arr[i], OperateStatus.OP_STOP));
            sb.append(buildLi(zookeeper_arr[i], OperateStatus.OP_RESTART));
            sb.append("</ul></div></td></tr>");
        }
        return sb.toString();
    }

    private String buildLi(String id, OperateStatus operateStatus) {
        StringBuffer sb = new StringBuffer();
        String name_id = name + id;
        sb.append("<li><a id=\"" + name_id + operateStatus.getCode()
                + "\" href=\"#\" onclick=\"" + operateStatus.getCode()
                + "('" + id + "');\">" + operateStatus.getDesc() + "</a></li>");
        return sb.toString();
    }

    public void operate(String type, String id, HttpServletResponse response) {
        logger.info("type：{}，id：{}", type, id);
        String path = "/home/zookeeper/port/zookeeper-3.4.6/bin/";
        String _path = path.replaceAll("port", id);
        logger.info("id：{}，_path：{}", id, _path);
        /**
         * 注意，这里如果用su -，日志就会写在/home/zookeeper/下
         * 如果用su，日志就会正常
         */
        String[] cmd = {"su", "zookeeper", "-c", ""};
        switch (OperateStatus.parser(type)) {
            case OP_START:
                cmd[3] = _path + buildCmd(Const.ZK_CMD, type);
                break;
            case OP_STOP:
                cmd[3] = _path + buildCmd(Const.ZK_CMD, type);
                break;
            case OP_RESTART:
                cmd[3] = _path + buildCmd(Const.ZK_CMD, type);
                break;
            case OP_STATUS:
                cmd[3] = _path + buildCmd(Const.ZK_CMD, type);
                break;
            default:
                logger.warn("no adaptation Operate！");
                throw new RuntimeException("no adaptation Operate！please contact the administrator !");
        }
        logger.info("cmd：{}，cmd.len：{}", cmd, cmd.length);
        AgentResult agentResult200 = monitorAndManagementClient200.exec(cmd, _path);
        AgentResult agentResult201 = monitorAndManagementClient201.exec(cmd, _path);
        AgentResult agentResult202 = monitorAndManagementClient202.exec(cmd, _path);
        boolean flag = agentResult200.getResultCode().equals("0") &&
                agentResult201.getResultCode().equals("0") &&
                agentResult202.getResultCode().equals("0");
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            String status_id = name + id + "status";
            StringBuffer sbexec = new StringBuffer();
            StringBuffer sbtd = new StringBuffer();
            sbtd.append("$('#" + status_id + "').html(\"");
            switch (OperateStatus.parser(type)) {
                case OP_START:
                    sbtd.append(getOperateStatus(flag));
                    if (flag) {
                        sbexec.append(changeBtnStatus(id, Const.START, Const.REMOVEATTR));
                        sbexec.append(changeBtnStatus(id, Const.STOP, Const.ATTR));
                    }
                    break;
                case OP_STOP:
                    sbtd.append(getOperateStatus(flag));
                    if (flag) {
                        sbexec.append(changeBtnStatus(id, Const.START, Const.ATTR));
                        sbexec.append(changeBtnStatus(id, Const.STOP, Const.REMOVEATTR));
                    }
                    break;
                case OP_RESTART:
                    sbtd.append(getOperateStatus(flag));
                    if (flag) {
                        sbexec.append(changeBtnStatus(id, Const.START, Const.REMOVEATTR));
                        sbexec.append(changeBtnStatus(id, Const.STOP, Const.ATTR));
                    } else {
                        sbexec.append(changeBtnStatus(id, Const.START, Const.ATTR));
                        sbexec.append(changeBtnStatus(id, Const.STOP, Const.REMOVEATTR));
                    }
                    break;
                case OP_STATUS:
                    sbtd.append(getOperateRunStatus(flag));
                    if (flag) {
                        sbexec.append(changeBtnStatus(id, Const.START, Const.REMOVEATTR));
                    } else {
                        sbexec.append(changeBtnStatus(id, Const.STOP, Const.REMOVEATTR));
                    }
                    break;
                default:
                    logger.warn("no adaptation Operate！");
                    throw new RuntimeException("no adaptation Operate！please contact the administrator !");
            }
            sbtd.append("\");");
            sbexec.append("loadModal(\"hide\");");
            out.print(sbtd.toString());
            out.print(sbexec.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String changeBtnStatus(String id, String type, String operate) {
        StringBuffer sb = new StringBuffer();
        String name_id = name + id;
        if (operate.equals("removeAttr")) {
            sb.append("$('#" + name_id + type + "').removeAttr(\"onclick\");");
            sb.append("$('#" + name_id + type + "').parent().addClass(\"disabled\");");
        } else if (operate.equals("attr")) {
            sb.append("$('#" + name_id + type + "').attr(\"onclick\", \"start('" + id + "');\");");
            sb.append("$('#" + name_id + type + "').parent().removeClass(\"disabled\");");
        }
        return sb.toString();
    }

    private String getOperateStatus(boolean operate) {
        return operate == true ? OperateStatus.SUCCESS.getDesc() : OperateStatus.FAIL.getDesc();
    }

    private String getOperateRunStatus(boolean operate) {
        return operate == true ? OperateStatus.STATUS_RUN.getDesc() : OperateStatus.STATUS_STOP.getDesc();
    }

    private String buildCmd(String base_cmd, String operate) {
        return base_cmd + Const.BLANK_SPACEK + operate;
    }
}
