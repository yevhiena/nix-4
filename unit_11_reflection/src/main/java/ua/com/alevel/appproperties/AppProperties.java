package ua.com.alevel.appproperties;

import ua.com.alevel.annotation.PropertyKey;



public class AppProperties {

    @PropertyKey("connections.limit")
    public int maxConnections;

    @PropertyKey("connections.db.url")
    public String urlOfConnection;

    @PropertyKey("logger.file.maxSize")
    public int maxFileSize;

    public String filePath;

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
