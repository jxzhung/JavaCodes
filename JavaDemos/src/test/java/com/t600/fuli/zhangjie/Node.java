package com.t600.fuli.zhangjie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class Node {
    @Expose
    private String name;
    private Object data;
    @Expose
    private String parentId;


    @Expose  private String id = null;
    @Expose  private List<Node> children = null;

    private Map<String,Node> keyMap = new HashMap<String,Node>();

    public Node(String id){
        this.id = id;
    }

    public Node insert(String id){
        Node child = null;
        if(children == null){
            children = new ArrayList<Node>();
        }

        if(keyMap.get(id) == null){
            child = new Node(id);
            children.add(child);
            keyMap.put(id, child);
        }else{
            return keyMap.get(id);
        }

        return child;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Node> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, Node> keyMap) {
        this.keyMap = keyMap;
    }
}