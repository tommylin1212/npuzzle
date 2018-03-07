package com.example.npuzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class ExploredSet {
    private ArrayList<Node> nodes;

    ExploredSet() {
        nodes = new ArrayList<>();
    }

    void add(Node node) {
        int i;
        for (i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getPathCost() > node.getPathCost()) {
                break;
            }
        }
        nodes.add(i, node);
    }

    boolean check(Node node) {
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

    Integer getSize() {
        return nodes.size();
    }

}
