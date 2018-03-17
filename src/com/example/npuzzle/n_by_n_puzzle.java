package com.example.npuzzle;

import java.util.ArrayList;

class n_by_n_puzzle {
    private long iterations = 0;
    private Frontier frontier = new Frontier();
    private ExploredSet exploredSet = new ExploredSet();
    private boolean rule;//true is manhattan,false is displacement

    n_by_n_puzzle(Node start, boolean hrule) {
        setRule(hrule);
        frontier.add(start);
    }

    private ArrayList<Node> getNext(Node current) {
        ArrayList<Integer> originalState = current.getState();
        int blankPos = 0;
        ArrayList<Node> newNodes = new ArrayList<>();
        ArrayList<Integer> toRemove = new ArrayList<>();
        Integer rowLength = new Double(Math.sqrt(current.getState().size())).intValue();
        int pos = new Double(blankPos % rowLength).intValue();
        ArrayList<Integer> validIndices = new ArrayList<>();

        while (true) {
            if (current.getState().get(blankPos++) == 0) break;
        }
        blankPos -= 1;
        if (pos == 0) {//right side
            Integer blankPosRight = blankPos + 1;
            validIndices.add(blankPosRight);
        } else if (pos == rowLength - 1) {//left side
            Integer blankPosLeft = blankPos - 1;
            validIndices.add(blankPosLeft);
        } else {//middle column
            Integer blankPosLeft = blankPos - 1;
            Integer blankPosRight = blankPos + 1;
            validIndices.add(blankPosLeft);
            validIndices.add(blankPosRight);
        }
        Integer blankPosDown =blankPos+rowLength;
        Integer blankPosUp = blankPos-rowLength;
        validIndices.add(blankPosUp);
        validIndices.add(blankPosDown);

        for (Integer move : validIndices) {//find out of range indices to remove
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
            newNodes.add(new Node(newState, current, rule));

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

    private void setRule(boolean rule) {
        this.rule = rule;
    }

    Node search() {

        while (true) {

            if (frontier.isEmpty()) return null;
            Node testNode = new Node(frontier.getFront());
            if (testNode.isGoal()) {
                return testNode;
            }
            exploredSet.add(testNode);
            ArrayList<Node> nextNodes = new ArrayList<>(getNext(testNode));
            for (Node child : nextNodes) {
                iterations++;
                if (!exploredSet.check(child)) {
                    if (frontier.check(child) && ((frontier.getNode(child)).getDisplacement() > child.getDisplacement())) {
                        System.out.println("In front with higher.");
                        frontier.remove(child);//this looks like it does nothing but it does trust me
                    }//A* remove if already there and cost is higher.
                    else {
                        frontier.add(child);
                    }
                }
            }
        }
    }

    long getIterations() {
        return iterations;
    }

    Integer getExploredSetSize() {
        return exploredSet.getSize();
    }

    Integer getFrontierSize() {
        return frontier.getSize();
    }
}

