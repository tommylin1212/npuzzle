package com.example.npuzzle;


import java.util.ArrayList;

public class Node {
    private ArrayList<Integer> state;
    private String id;
    private Integer size;
    private Node parent;
    private Double pathCost;
    private Double displacement;
    private boolean rule;//true is manhattan distance, false is misplaced;


    Node(ArrayList<Integer> init_state, Node parent_id, boolean hRule) {
        setRule(hRule);
        setState(init_state);
        setSize(init_state.size());
        setId(((Integer) (System.identityHashCode(this))).toString());//unique id
        setParent(parent_id);
        calcCostGoal(init_state.size());
        if (parent_id != null) {
            setPathCost(getParent().getPathCost() + getDisplacement());
        } else {
            setPathCost(getDisplacement());
        }
    }

    Node(Node node) {
        setPathCost(node.getPathCost());
        setSize(node.getSize());
        setRule(node.isRule());
        setId(node.getId());
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
                //System.out.println("I: "+i+", Col: "+col+", DesCol: "+desiredCol+", Row: "+row+", DesRow: "+desiredRow+", Displacement: " + displacement);
            }
        } else {
            for (int i = 0; i < size; i++) {
                Integer valueAtPos = state.get(i);
                if (valueAtPos != i) {
                    displacement += 1;
                    //System.out.println("I: "+i+", Value: "+valueAtPos+", Displacement: " + displacement);
                }
            }
        }
    }

    public boolean isGoal() {
        Integer size = getSize();
        for (int i = 0; i < size; i++) {
            Integer valueAtPos = state.get(i);
            if (valueAtPos != i) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> getState() {
        return state;
    }

    private void setState(ArrayList<Integer> state) {
        this.state = state;
    }

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private Node getParent() {
        return parent;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public void print() {
        System.out.println("Id: " + id + ", State: " + state + ", Displacement: " + displacement + ", Parent: " + parent);
    }

    private boolean isRule() {
        return rule;
    }

    private void setRule(boolean rule) {
        this.rule = rule;
    }

    private Double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    public Double getPathCost() {
        return pathCost;
    }

    private void setPathCost(Double pathCost) {
        this.pathCost = pathCost;
    }

    private Integer getSize() {
        return size;
    }

    private void setSize(Integer size) {
        this.size = size;
    }

    private void prettyPrint() {
        Integer rowsize = new Double(Math.sqrt(getSize())).intValue();
        for (int i = 0; i < rowsize + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < getSize(); i++) {
            if (i % rowsize == 0) {
                System.out.print("|");
            }
            System.out.print(state.get(i));
            if (i % rowsize == rowsize - 1) {
                System.out.println("|");
            }
        }
        for (int i = 0; i < rowsize + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void traceBack() {
        if (getParent() != null) {
            getParent().traceBack();
        }
        prettyPrint();
    }
}
