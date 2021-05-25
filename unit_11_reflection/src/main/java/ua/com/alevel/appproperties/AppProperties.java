package ua.com.alevel.appproperties;

import ua.com.alevel.annotation.PropertyKey;



public class AppProperties {

    @PropertyKey("connections.limit")
    public int maxConnections;

    @PropertyKey("connections.db.url")
    public String urlOfConnection;

    public String filePath;

    @PropertyKey("logger.file.maxSize")
    public int maxFileSize;


    @Override
    public String toString() {
        return "AppProperties{" +
                "maxConnections=" + maxConnections +
                ", urlOfConnection='" + urlOfConnection + '\'' +
                ", maxFileSize=" + maxFileSize +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
