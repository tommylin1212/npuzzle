package com.example.npuzzle;


import java.util.ArrayList;

public class Node {
    private ArrayList<Integer> state;
    private String id;
    private String parent;


    public Node(ArrayList<Integer> init_state, String parent_id) {
        setState(init_state);
        setId(((Integer) (System.identityHashCode(this))).toString());//unique id
        setParent(parent_id);

    }

    public ArrayList<Integer> getState() {
        return state;
    }

    public void setState(ArrayList<Integer> state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void print() {
        System.out.print("State: " + state);
    }
}
