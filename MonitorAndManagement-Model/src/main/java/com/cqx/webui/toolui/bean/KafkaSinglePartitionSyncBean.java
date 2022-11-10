package com.cqx.webui.toolui.bean;

/**
 * KafkaSinglePartitionSyncBean
 *
 * @author chenqixu
 */
public class KafkaSinglePartitionSyncBean extends ToolUIRequestBean {
    private String table_name;
    private String primary_keys;
    private int analyze_hour;
    private String redis_conf;
    private String srcBean;
    private String adbBean;
    private String ogg_topic;
    private String flat_topic;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getPrimary_keys() {
        return primary_keys;
    }

    public void setPrimary_keys(String primary_keys) {
        this.primary_keys = primary_keys;
    }

    public String getRedis_conf() {
        return redis_conf;
    }

    public void setRedis_conf(String redis_conf) {
        this.redis_conf = redis_conf;
    }

    public String getSrcBean() {
        return srcBean;
    }

    public void setSrcBean(String srcBean) {
        this.srcBean = srcBean;
    }

    public String getAdbBean() {
        return adbBean;
    }

    public void setAdbBean(String adbBean) {
        this.adbBean = adbBean;
    }

    public int getAnalyze_hour() {
        return analyze_hour;
    }

    public void setAnalyze_hour(int analyze_hour) {
        this.analyze_hour = analyze_hour;
    }

    public String getOgg_topic() {
        return ogg_topic;
    }

    public void setOgg_topic(String ogg_topic) {
        this.ogg_topic = ogg_topic;
    }

    public String getFlat_topic() {
        return flat_topic;
    }

    public void setFlat_topic(String flat_topic) {
        this.flat_topic = flat_topic;
    }
}
