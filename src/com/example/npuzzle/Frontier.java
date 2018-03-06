package com.example.npuzzle;

import java.util.ArrayList;

public class Frontier {
    private ArrayList<Node> nodes;

    Frontier() {
        nodes = new ArrayList<>();
    }

    public void add(Node node) {
        for (Node testNode : nodes) {
            if (testNode.getPathCost() > node.getPathCost()) {
                break;
            }
        }
        nodes.add(node);
    }

    public boolean check(Node node) {
        for (Node testNode : nodes) {
            if (testNode.getState().equals(node.getState())) {
                System.out.println("Matched: front");
                return true;

            }
        }
        return false;
    }

    public void remove(Node node) {
        for (Node testNode : nodes) {
            if (testNode.getState().toArray() == node.getState().toArray()) {
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

    public Node getNode(Node node) {
        for (Node testNode : nodes) {
            if (testNode.getState().toArray() == node.getState().toArray()) {
                return testNode;
            }
        }
        return null;//shouldn't ever get here
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }
}
