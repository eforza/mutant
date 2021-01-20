package com.xmen.mutant.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Matrix uitls methods.
 *
 * @author esteban
 */
public class MatrixOperations {

    public static List<String> transpose(List<String> table) {
        List<String> transposedList = new ArrayList<String>();

        final int n = table.size();
        for (int i = 0; i < n; i++) {
            StringBuilder tempRow = new StringBuilder();

            for (String row : table) {
                tempRow.append(row.charAt(i));
            }
            transposedList.add(tempRow.toString());
        }
        return transposedList;
    }
    public static List<String> diagonalsLeftToBottom(List<String> grid) {
        List<String> diagonals = new ArrayList<>();

        for (int j = 0; j <= grid.size() + grid.size() - 2; j++) {
            StringBuilder diagonal = new StringBuilder();
            for (int k = 0; k <= j; k++) { // cols
                int l = j - k; //  rows
                int mirror = grid.size() - l;
                if (mirror >= 0 && mirror < grid.size() && k < grid.size()) {
                    diagonal.append(grid.get(mirror).charAt(k));
                }
            }
            if (diagonal.length() > 3) {
                diagonals.add(diagonal.toString());
            }
        }
        return diagonals;

    }

    public static List<String> diagonalsLeftToTop(List<String> grid) {
        List<String> diagonals = new ArrayList<>();

        for (int j = 0; j <= grid.size() + grid.size() - 2; j++) {
            StringBuilder diagonal = new StringBuilder();

            for (int k = 0; k <= j; k++) { // col
                int l = j - k; //  row
                if (l < grid.size() && k < grid.size()) {
                    diagonal.append(grid.get(l).charAt(k));
                }
            }
            if (diagonal.length() > 3) {
                diagonals.add(diagonal.toString());
            }
        }
        return diagonals;
    }


}
