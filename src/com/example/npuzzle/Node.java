package com.example.npuzzle;


import java.util.ArrayList;

public class Node {
    private ArrayList<Integer> state;
    private Integer size;
    private Node parent;
    private Double displacement;
    private boolean rule;//true is manhattan distance, false is misplaced;


    Node(ArrayList<Integer> init_state, Node parent_id, boolean hRule) {
        setRule(hRule);
        setState(init_state);
        setSize(init_state.size());
        setParent(parent_id);
        calcCostGoal(init_state.size());
    }

    Node(Node node) {
        setSize(node.getSize());
        setRule(node.isRule());
        setParent(node.getParent());
        setState(node.getState());
        calcCostGoal(node.getState().size());
    }

    private void calcCostGoal(Integer size) {
        displacement = 0.0;
        Integer rowSize = new Double(Math.sqrt(size)).intValue();
        if (rule) {
            for (int i = 0; i < size; i++) {
                Integer valueAtPos = state.get(i);
                Integer col = i / rowSize;
                Integer row = i % rowSize;
                Integer desiredCol = valueAtPos / rowSize;
                Integer desiredRow = valueAtPos % rowSize;
                displacement += (Math.abs(col - desiredCol) + Math.abs(row - desiredRow));
            }
        } else {
            for (int i = 0; i < size; i++) {
                Integer valueAtPos = state.get(i);
                if (valueAtPos != i) {
                    displacement += 1;
                }
            }
        }
    }

    boolean isGoal() {
        Integer size = getSize();
        for (int i = 0; i < size; i++) {
            Integer valueAtPos = state.get(i);
            if (valueAtPos != i) {
                return false;
            }
        }
        return true;
    }

    ArrayList<Integer> getState() {
        return state;
    }

    private void setState(ArrayList<Integer> state) {
        this.state = state;
    }

    private Node getParent() {
        return parent;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public void print() {
        System.out.println("State: " + state + ", PathCost: " + displacement + ", Parent: " + parent);
    }

    private boolean isRule() {
        return rule;
    }

    private void setRule(boolean rule) {
        this.rule = rule;
    }

    Double getDisplacement() {
        return displacement;
    }

    private Integer getSize() {
        return size;
    }

    private void setSize(Integer size) {
        this.size = size;
    }

    private void prettyPrint() {
        Integer rowSize = new Double(Math.sqrt(getSize())).intValue();
        for (int i = 0; i < rowSize + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < getSize(); i++) {
            if (i % rowSize == 0) {
                System.out.print("|");
            }
            System.out.print(state.get(i));
            if (i % rowSize == rowSize - 1) {
                System.out.println("|");
            }
        }
        for (int i = 0; i < rowSize + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    void traceBack(int i) {
        i++;
        if (getParent() != null) {
            getParent().traceBack(i);
        } else {
            System.out.println(i + " moves in solution.");
        }
        prettyPrint();
    }
}
