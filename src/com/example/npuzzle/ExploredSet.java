package com.example.npuzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class ExploredSet {
    private ArrayList<Node> nodes;

    ExploredSet() {
        nodes = new ArrayList<>();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public boolean check(Node node) {
        for (Node testNode : nodes) {
            if (Arrays.equals(testNode.getState().toArray(), node.getState().toArray())) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("**************EXSET***************");
        for (Node node : nodes) {
            System.out.println(node.getState());
        }
        System.out.println("**********************************");
    }

    public Integer getSize() {
        return nodes.size();
    }

}
