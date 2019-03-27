package com.cqx.server.inf;

/**
 * IServerDeal
 *
 * @author chenqixu
 */
public interface IServerDeal {

    void init();

    boolean start();

    boolean stop();

    boolean status();
}
