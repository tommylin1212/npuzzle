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

    private void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }


    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void printNodes() {
        for (Node node : this.nodes) {
            node.print();
        }
    }

    public ArrayList<Node> getNext(Node current) {
        ArrayList<Integer> originalState = current.getState();
        int blankPos = 0;
        while (true) {
            if (current.getState().get(blankPos++) == 0) break;
        }
        blankPos -= 1;
        int pos = new Double(blankPos + 1 % Math.sqrt(current.getState().size())).intValue();
        ArrayList<Integer> validIndices = new ArrayList<>();
        if (pos == 0) {
            Integer blankPosLeft = blankPos - 1;
            Integer blankPosDown = new Double(blankPos + Math.sqrt((current.getState().size()))).intValue();
            Integer blankPosUp = new Double(blankPos - Math.sqrt(current.getState().size())).intValue();
            validIndices.add(blankPosDown);
            validIndices.add(blankPosLeft);
            validIndices.add(blankPosUp);
        } else if (pos == new Double(Math.sqrt(current.getState().size())).intValue() - 1) {
            Integer blankPosRight = blankPos + 1;
            Integer blankPosDown = new Double(blankPos + Math.sqrt((current.getState().size()))).intValue();
            Integer blankPosUp = new Double(blankPos - Math.sqrt(current.getState().size())).intValue();
            validIndices.add(blankPosDown);
            validIndices.add(blankPosRight);
            validIndices.add(blankPosUp);
        } else {
            Integer blankPosLeft = blankPos - 1;
            Integer blankPosRight = blankPos + 1;
            Integer blankPosDown = new Double(blankPos + Math.sqrt((current.getState().size()))).intValue();
            Integer blankPosUp = new Double(blankPos - Math.sqrt(current.getState().size())).intValue();
            validIndices.add(blankPosDown);
            validIndices.add(blankPosLeft);
            validIndices.add(blankPosRight);
            validIndices.add(blankPosUp);
        }
        ArrayList<Node> newNodes = new ArrayList<>();
        ArrayList<Integer> toRemove = new ArrayList<>();
        for (Integer move : validIndices) {
            if ((move >= current.getState().size()) || (move < 0)) {
                toRemove.add(move);
            }
        }
        for (Integer index : toRemove) {
            validIndices.remove(index);
        }
        validIndices.trimToSize();
        for (Integer move : validIndices) {
            ArrayList<Integer> newState = new ArrayList<>(originalState);
            newState = swap(newState, move, blankPos);
            newNodes.add(new Node(newState, current.getId()));

        }
        return newNodes;
    }

    private ArrayList<Integer> swap(ArrayList<Integer> current, Integer from, Integer to) {
        Integer atFrom = current.get(from);
        Integer atTo = current.get(to);
        current.set(from, atTo);
        current.set(to, atFrom);
        return current;
    }
}

