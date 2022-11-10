package com.cqx.webui.toolui.servlet;

import com.cqx.webui.toolui.util.HtmlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 流程创建
 *
 * @author chenqixu
 */
public class CreateServlet extends IServlet {
    private static final Logger logger = LoggerFactory.getLogger(CreateServlet.class);
    private HtmlBuilder htmlBuilder;

    @Override
    public void init() throws ServletException {
        htmlBuilder = new HtmlBuilder();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String web_path = request.getContextPath();
//        response.addHeader("P3P", "CP=CAO PSA OUR");//iframe框架下session丢失的问题
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.write(htmlBuilder.model(web_path));
//        writer.close();

        Map<String, String[]> parameterMap = request.getParameterMap();
        logger.info("parameterMap：{}", parameterMap);
        if (parameterMap != null) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                logger.info("{} {}", entry.getKey(), entry.getValue());
            }
        }

        String table_name = request.getParameter("table_name");
        logger.info("table_name：{}", table_name);
    }

    private String body() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    @Override
    public void close() {
        //null
    }
}
