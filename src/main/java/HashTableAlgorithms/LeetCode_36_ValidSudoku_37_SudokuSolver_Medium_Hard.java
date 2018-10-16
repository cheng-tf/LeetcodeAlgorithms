package HashTableAlgorithms;

public class LeetCode_36_ValidSudoku_37_SudokuSolver_Medium_Hard {
    /******独数_LeetCode_36_ValidSudoku_37_SudokuSolver_Medium_Hard*******/

    /**
     * 独数_LeetCode_36_ValidSudoku_37_SudokuSolver_Medium_Hard
     * T1 : 有效的独数_LeetCode_36_ValidSudoku
     * T2 : 独数解法_LeetCode_37_SudokuSolver
     */
    /*********T1_有效的独数_LeetCode_36_ValidSudoku*******/
    /**
     * T1_有效的独数_LeetCode_36_ValidSudoku
     * 难度：Medium
     * DateTime:2018-10-15
     * https://leetcode.com/problems/valid-sudoku/description/
     * 题目介绍：
     * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated
     * according to the following rules:
     * <p>
     * 1. Each row must contain the digits 1-9 without repetition.
     * 2. Each column must contain the digits 1-9 without repetition.
     * 3. Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * <p>
     * 省略，见LeetCode。
     * A partially filled sudoku which is valid.
     * <p>
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * [
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     * Output: true
     * Example 2:
     * <p>
     * Input:
     * [
     * ["8","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     * Output: false
     * Explanation: Same as Example 1, except with the 5 in the top left corner being
     * modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
     * Note:
     * <p>
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     * The given board contain only digits 1-9 and the character '.'.
     * The given board size is always 9x9.
     * <p>
     * 思路分析：
     */
    public boolean isValidSudoku(char[][] board) {

        return true;
    }

    /*********T2_独数解法_LeetCode_37_SudokuSolver*******/
    /**
     * T2_独数解法_LeetCode_37_SudokuSolver
     * 难度：Hard
     * DateTime:2018-10-15
     * https://leetcode.com/problems/sudoku-solver/description/
     * 题目介绍：
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     * <p>
     * A sudoku solution must satisfy all of the following rules:
     * <p>
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * Empty cells are indicated by the character '.'.
     * 省略，见LeetCode。
     * Note:
     * <p>
     * The given board contain only digits 1-9 and the character '.'.
     * You may assume that the given Sudoku puzzle will have a single unique solution.
     * The given board size is always 9x9.
     * <p>
     * <p>
     * 思路分析：
     */

    public void solveSudoku(char[][] board) {

    }
}
