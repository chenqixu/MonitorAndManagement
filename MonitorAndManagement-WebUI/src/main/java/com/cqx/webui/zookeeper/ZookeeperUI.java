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
            sb.append("<li><a id=\"" + id + "start\" href=\"#\" onclick=\"start('" + zookeeper_arr[i] + "');\">启动</a></li>");
            sb.append("<li><a id=\"" + id + "stop\" href=\"#\" onclick=\"stop('" + zookeeper_arr[i] + "');\">停止</a></li>");
            sb.append("<li><a id=\"" + id + "restart\" href=\"#\" onclick=\"restart('" + zookeeper_arr[i] + "');\">重启</a></li>");
            sb.append("</ul></div></td></tr>");
        }
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
        String[] cmd = {"su", "zookeeper", "-c", _path + "zkServer.sh status"};
        switch (type) {
            case "start":
                cmd[3] = _path + "zkServer.sh start";
                break;
            case "stop":
                cmd[3] = _path + "zkServer.sh stop";
                break;
            case "restart":
                cmd[3] = _path + "zkServer.sh restart";
                break;
            case "status":
                cmd[3] = _path + "zkServer.sh status";
                break;
            default:
                break;
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
            PrintWriter out = null;
            out = response.getWriter();
            String status_id = name + id + "status";
            String a_id = name + id;
            StringBuffer sbexec = new StringBuffer("");
            StringBuffer sbtd = new StringBuffer("");
            sbtd.append("$('#" + status_id + "').html(\"");
            switch (type) {
                case "start":
                    sbtd.append(flag == true ? "运行中" : "已停止");
                    if (flag) {
                        sbexec.append("$('#" + a_id + "start').removeAttr(\"onclick\");");
                        sbexec.append("$('#" + a_id + "start').parent().addClass(\"disabled\");");
                        sbexec.append("$('#" + a_id + "stop').attr(\"onclick\", \"stop('" + id + "');\");");
                        sbexec.append("$('#" + a_id + "stop').parent().removeClass(\"disabled\");");
                    }
                    break;
                case "stop":
                    sbtd.append(flag == true ? "已停止" : "运行中");
                    if (flag) {
                        sbexec.append("$('#" + a_id + "stop').removeAttr(\"onclick\");");
                        sbexec.append("$('#" + a_id + "stop').parent().addClass(\"disabled\");");
                        sbexec.append("$('#" + a_id + "start').attr(\"onclick\", \"start('" + id + "');\");");
                        sbexec.append("$('#" + a_id + "start').parent().removeClass(\"disabled\");");
                    }
                    break;
                case "restart":
                    sbtd.append(flag == true ? "运行中" : "已停止");
                    if (flag) {
                        sbexec.append("$('#" + a_id + "start').removeAttr(\"onclick\");");
                        sbexec.append("$('#" + a_id + "start').parent().addClass(\"disabled\");");
                        sbexec.append("$('#" + a_id + "stop').attr(\"onclick\", \"stop('" + id + "');\");");
                        sbexec.append("$('#" + a_id + "stop').parent().removeClass(\"disabled\");");
                    } else {
                        sbexec.append("$('#" + a_id + "stop').removeAttr(\"onclick\");");
                        sbexec.append("$('#" + a_id + "stop').parent().addClass(\"disabled\");");
                        sbexec.append("$('#" + a_id + "start').attr(\"onclick\", \"start('" + id + "');\");");
                        sbexec.append("$('#" + a_id + "start').parent().removeClass(\"disabled\");");
                    }
                    break;
                case "status":
                    sbtd.append(flag == true ? "运行中" : "已停止");
                    if (flag) {
                        sbexec.append("$('#" + a_id + "start').removeAttr(\"onclick\");");
                        sbexec.append("$('#" + a_id + "start').parent().addClass(\"disabled\");");
                    } else {
                        sbexec.append("$('#" + a_id + "stop').removeAttr(\"onclick\");");
                        sbexec.append("$('#" + a_id + "stop').parent().addClass(\"disabled\");");
                    }
                    break;
                default:
                    break;
            }
            sbtd.append("\");");
            sbexec.append("loadModal(\"hide\");");
            out.print(sbtd.toString());
            out.print(sbexec.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
