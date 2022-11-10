package com.cqx.webui.toolui.util;

/**
 * HtmlBuilder
 *
 * @author chenqixu
 */
public class HtmlBuilder {

    public String model(String path) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"zh-CN\">");
        sb.append("<meta charset=\"UTF-8\">");
        sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        sb.append("<title>流程创建</title>");
        sb.append("<meta name=\"description\" content=\"Source code generated using layoutit.com\">");
        sb.append("<meta name=\"author\" content=\"LayoutIt!\">");
        sb.append("<link href=\"").append(path).append("/css/bootstrap.min.css\" rel=\"stylesheet\">");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div class=\"container\" style=\"width: 100%;\">");
        sb.append("    <div class=\"row clearfix\">");
        sb.append("        <div class=\"col-md-12 column\">");
        sb.append("            <h3><span class=\"label label-primary\">同步</span></h3>");
        sb.append("        </div>");
        sb.append("    </div>");
        sb.append("    <div class=\"row clearfix\">");
        sb.append("        <div class=\"col-md-12 column\">");
        sb.append("            <ul class=\"breadcrumb\">");
        sb.append("                <li>");
        sb.append("                    <a href=\"javascript:void(0);\" onclick=\"window.location.href='new.html'\">库对库同步</a>");
        sb.append("                </li>");
        sb.append("                <li>");
        sb.append("                    <a>库导出到文件</a>");
        sb.append("                </li>");
        sb.append("                <li>");
        sb.append("                    <a>oracle库导出到文件</a>");
        sb.append("                </li>");
        sb.append("            </ul>");
        sb.append("        </div>");
        sb.append("    </div>");
        sb.append("    <div class=\"row clearfix\">");
        sb.append("        <div class=\"col-md-12 column\">");
        sb.append("            <h3><span class=\"label label-primary\">其他</span></h3>");
        sb.append("        </div>");
        sb.append("    </div>");
        sb.append("    <div class=\"row clearfix\">");
        sb.append("        <div class=\"col-md-12 column\">");
        sb.append("            <ul class=\"breadcrumb\">");
        sb.append("                <li>");
        sb.append("                    <a>元数据查询</a>");
        sb.append("                </li>");
        sb.append("            </ul>");
        sb.append("        </div>");
        sb.append("    </div>");
        sb.append("</div>");
        sb.append("<script src=\"").append(path).append("/js/jquery-2.1.4.min.js\"></script>");
        sb.append("<script src=\"").append(path).append("/js/bootstrap.min.js\"></script>");
        sb.append("<script src=\"").append(path).append("/js/index.js\"></script>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
