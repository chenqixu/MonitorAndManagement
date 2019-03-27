<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqx.webui.zookeeper.ZookeeperUI"%>
<%
String action = request.getParameter("action");
String id = request.getParameter("id");
ZookeeperUI zkui = new ZookeeperUI();
zkui.operate(action, id, response);
%>