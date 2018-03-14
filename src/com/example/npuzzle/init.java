package com.example.npuzzle;

import java.util.ArrayList;
import java.util.Collections;

public class init {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.print("Usage:Program [-D(isplacement)|-M(anhattan)][-O(utput)|-S(ilent)]");
            System.exit(2);
        }
        Integer size = 3;
        boolean rule = true;
        boolean silent = true;
        if (args[0].equals("-D")) {
            rule = false;
        }
        if (args[1].equals("-O")) {
            silent = false;
        }

        ArrayList<Integer> startState = initPuzzle(size);
        while (!testPuzzle(startState)) {
            startState = initPuzzle(size);
        }
        System.out.println("Found Valid Puzzle.");
        Node start = new Node(startState, null, rule);
        n_by_n_puzzle current_puzzle = new n_by_n_puzzle(start, rule);
        Node answer = current_puzzle.search();
        if (answer != null) {
            System.out.println("Found Solution.");
            if (!silent) answer.traceBack(0);
            if (rule) {
                System.out.println("Used Manhattan Heuristic.");
            } else {
                System.out.println("Used Displaced Tiles Heuristic.");
            }
            System.out.println("Took: " + current_puzzle.getIterations() + " iterations.");
            System.out.println("Explored: " + current_puzzle.getExploredSetSize() + " nodes.");
            System.out.println("Frontier is: " + current_puzzle.getFrontierSize() + " nodes long.");
        } else {
            System.out.println("No Solution");
        }
    }


    private static boolean testPuzzle(ArrayList<Integer> puzzle) {
        Integer rowSize = new Double(Math.sqrt(puzzle.size())).intValue();
        Integer size = puzzle.size();
        Integer count = 0;
        Integer temp;
        Integer thisCount = 0;
        Integer blankFromBottom = 0;
        Integer currentValue;
        Integer i, j;
        boolean gridWidthOdd = false;
        boolean inversionsEven = true;
        boolean blankOddFromBottom = false;

        if (rowSize % 2 == 1) {
            gridWidthOdd = true;
        }
        for (i = 0, j = 0; i < size - 1; i++) {
            currentValue = puzzle.get(j++);
            if (currentValue == 0) {
                blankFromBottom = new Double((rowSize - 1) - (i / rowSize)).intValue();
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
        if (count % 2 == 1) {
            inversionsEven = false;
        }
        if (blankFromBottom % 2 == 0) {
            blankOddFromBottom = true;
        }
        return (gridWidthOdd && inversionsEven) || (!gridWidthOdd && (blankOddFromBottom == inversionsEven));
    }

    private static ArrayList<Integer> initPuzzle(Integer size) {
        ArrayList<Integer> startState = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            startState.add(i);
        }
        Collections.shuffle(startState);
        return startState;
    }
}
