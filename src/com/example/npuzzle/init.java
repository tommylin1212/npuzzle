package com.example.npuzzle;

import java.util.ArrayList;
import java.util.Collections;

public class init {
    public static void main(String[] args) {
        ArrayList<Integer> startState = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            startState.add(i);
        }
        Collections.shuffle(startState);
        if (!testPuzzle(startState)) {
            System.out.print("Not ");
        }
        System.out.println("Solvable");
        Node start = new Node(startState, "-1");
        n_by_n_puzzle current_puzzle = new n_by_n_puzzle(start);
        current_puzzle.printNodes();
    }

    static boolean testPuzzle(ArrayList<Integer> puzzle) {
        Integer rowSize = new Double(Math.sqrt(puzzle.size())).intValue();
        Integer size = puzzle.size();
        Integer count = 0;
        Integer temp;
        Integer thisCount = 0;
        Integer blankFromBottom = 0;
        Integer currentValue;
        Integer i, j;

        boolean gridWidthOdd = false;
        if (rowSize % 2 == 1) {
            gridWidthOdd = true;
        }
        for (i = 0, j = 0; i < size - 1; i++) {
            currentValue = puzzle.get(j++);
            if (currentValue == 0) {
                blankFromBottom = (new Double((rowSize - 1) - (i / rowSize)).intValue());
                continue;
            }
            temp = j;
            thisCount = 0;
            while (temp <= size - 1) {
                if ((puzzle.get(temp) != 0) && (puzzle.get(temp) < currentValue)) {
                    thisCount++;
                }
                temp++;
            }
            count += thisCount;
        }
        boolean inversionsEven = true;
        if (count % 2 == 1) {
            inversionsEven = false;
        }
        boolean blankOddFromBottom = false;
        if (blankFromBottom % 2 == 1) {
            blankOddFromBottom = true;
        }
        return (gridWidthOdd && inversionsEven) || (!gridWidthOdd && (blankOddFromBottom == inversionsEven));
    }
}
