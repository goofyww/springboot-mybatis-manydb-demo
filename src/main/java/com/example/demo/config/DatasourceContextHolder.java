package com.example.demo.config;

import com.example.demo.comm.DataSources;

public class DatasourceContextHolder {

    public static final String DEFAULT_DATASOURCE = DataSources.PRIMARY;

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static void setDB(String dbType) {
        HOLDER.set(dbType);
    }

    public static String getDB() {
        return HOLDER.get();
    }

    public static void clearDB() {
        HOLDER.remove();
    }

}

