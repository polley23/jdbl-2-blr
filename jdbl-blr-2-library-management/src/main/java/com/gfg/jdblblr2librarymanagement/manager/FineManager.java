package com.gfg.jdblblr2librarymanagement.manager;

public interface FineManager {
    Integer getForUser(String username) throws Exception;
    void payForUser(String username, Integer amount) throws Exception;
}
