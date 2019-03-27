package com.cqx.agent;

import com.cqx.agent.bean.AgentResult;
import com.cqx.agent.util.ExecShell;

/**
 * MonitorAndManagement
 *
 * @author chenqixu
 */
public class MonitorAndManagement implements MonitorAndManagementMBean {

    @Override
    public String exec(String cmd) {
        ExecShell execShell = ExecShell.builder();
        int resultcode = execShell.run(cmd);
        return "" + resultcode;
    }

    @Override
    public String execs(String cmd) {
        ExecShell execShell = ExecShell.builder();
        int resultcode = execShell.runs(cmd);
        return "" + resultcode;
    }

    @Override
    public AgentResult exec(String[] cmd, String path) {
        ExecShell execShell = ExecShell.builder();
        int resultcode = execShell.run(cmd, path);
        return AgentResultBuilder(resultcode, execShell);
    }

    private AgentResult AgentResultBuilder(int resultcode, ExecShell execShell) {
        StringBuilder success_sb = new StringBuilder();
        StringBuilder error_sb = new StringBuilder();
        execShell.writeSuccessLog(success_sb);
        execShell.writeErrLog(error_sb);
        return new AgentResult("" + resultcode, success_sb.toString(), error_sb.toString());
    }
}
