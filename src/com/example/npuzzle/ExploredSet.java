package com.example.npuzzle;

import java.util.ArrayList;

public class ExploredSet {
    private ArrayList<Node> nodes;

    ExploredSet() {
        nodes = new ArrayList<Node>();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public boolean check(Node node) {
        for (Node testNode : nodes) {
            if (testNode.getState().toArray() == node.getState().toArray()) {
                System.out.println("Matched: exset");
                return true;
            }
        }
        return false;
    }

    public void remove(Node node) {
        for (Node testNode : nodes) {
            if (testNode.getState() == node.getState()) {
                nodes.remove(testNode);
                nodes.trimToSize();
                return;
            }
        }
    }

    public Node getFront() {
        Node temp = nodes.remove(0);
        nodes.trimToSize();
        return temp;
    }
}
