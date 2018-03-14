package com.example.npuzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class Frontier {
    private ArrayList<Node> nodes;

    Frontier() {
        nodes = new ArrayList<>();
    }

    void add(Node node) {
        int i;
        for (i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getDisplacement() > node.getDisplacement()) {
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

    void remove(Node node) {
        for (Node testNode : nodes) {
            if (Arrays.equals(testNode.getState().toArray(), node.getState().toArray())) {
                nodes.remove(testNode);
                nodes.trimToSize();
                return;
            }
        }
    }

    Node getFront() {
        Node temp = nodes.remove(0);
        nodes.trimToSize();
        return temp;
    }

    Node getNode(Node node) {
        for (Node testNode : nodes) {
            if (Arrays.equals(testNode.getState().toArray(), node.getState().toArray())) {
                return testNode;
            }
        }
        return null;//shouldn't ever get here...i hope
    }

    boolean isEmpty() {
        return nodes.isEmpty();
    }

    public void print() {
        System.out.println("**************Frontier***************");
        for (Node node : nodes) {
            System.out.println(node.getState());
        }
        System.out.println("**********************************");
    }

    Integer getSize() {
        return nodes.size();
    }
}
