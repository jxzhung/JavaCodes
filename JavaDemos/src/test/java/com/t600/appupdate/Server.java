package com.t600.appupdate;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzhung on 2017/7/28.
 */
public class Server {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static void main(String[] args) {
        List<Server> serverList = new ArrayList<Server>();
        for (int i = 0; i < 8; i++) {
            Server server = new Server();
            server.setName("测试");
            server.setUrl("192.168.1.9:8888");
            serverList.add(server);
        }
        Gson gson = new Gson();
        String json = gson.toJson(serverList);
        System.out.println(json);
    }


}
