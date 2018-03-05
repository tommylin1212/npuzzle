package com.example.npuzzle;

import java.util.ArrayList;

public class n_by_n_puzzle {
    private ArrayList<Node> nodes;

    n_by_n_puzzle(Node start) {
        setNodes(new ArrayList<Node>());//init list
        addNode(start);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    private void addNode(Node node) {
        this.nodes.add(node);
    }

    public void printNodes() {
        for (Node node : this.nodes) {
            node.print();
        }
    }
}

