package com.example.npuzzle;


public class Node {
    private Integer[] state;


    public Node(Integer[] init_state) {
        state =init_state;
    }

    public Integer[] getState() {
        return state;
    }

    public void setState(Integer[] state) {
        this.state = state;
    }
}
