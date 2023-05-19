package org.example;

public class Sudoku {
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        //三个二维数组代表可否是用index进行填充
        int[][] rowAvailable = new int[9][9];//rowAvailable[i][j]代表对第i+1行还可不可以再出现j+1 0代表可以 1代表不可以
        int[][] colAvailable = new int[9][9];//colAvailable[i][j]代表对第i+1列还可不可以再出现j+1
        int[][][] subBoxAvailable = new int[3][3][9];//subBoxAvailable[i][j][k]代表i,j这个九宫格还可不可以再出现k+1
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int cur = board[i][j] - '0';
                    rowAvailable[i][cur - 1] = 1;
                    colAvailable[j][cur - 1] = 1;
                    subBoxAvailable[i / 3][j / 3][cur - 1] = 1;
                }
            }
        }
        boolean ans = recursiveSolve(rowAvailable, colAvailable, subBoxAvailable, board, 0, 0);
    }

    private boolean recursiveSolve(int[][] rowAvailable, int[][] colAvailable, int[][][] subBoxAvailable, char[][] board, int i, int j) {//对于board中的i,j开始填数独，能正确填完返回true
        if (j == 9) {//如果到了一行的最右侧，转到下一行，如果到了右下角返回true
            i++;
            j = 0;
            if (i == 9) {
                return true;
            }
        }
        if (board[i][j] == '.') {
            for (int insert = 1; insert <= 9; insert++) {//对一个有空的地方，从1 - 9开始尝试，1 - 9中available的才进入递归
                if (rowAvailable[i][insert-1] != 0 || colAvailable[j][insert-1] != 0 || subBoxAvailable[i / 3][j / 3][insert-1] != 0) {
                    continue;
                } else {
                    board[i][j] = (char) ('0' + insert);//把这个数填入数独
                    rowAvailable[i][insert - 1] = 1;//更新三个available表格
                    colAvailable[j][insert - 1] = 1;
                    subBoxAvailable[i/3][j/3][insert - 1] = 1;
                    if (recursiveSolve(rowAvailable, colAvailable, subBoxAvailable, board, i, j + 1)) {//如果后续的递归能正确返回，返回true，如果不能正确返回，把更新过的三个available表格再改回来
                        return true;
                    } else {
                        board[i][j] = '.';
                        rowAvailable[i][insert - 1] = 0;
                        colAvailable[j][insert - 1] = 0;
                        subBoxAvailable[i/3][j/3][insert - 1] = 0;
                    }
                }
            }
        } else {
            return recursiveSolve(rowAvailable, colAvailable, subBoxAvailable, board, i, j+1);//当前地方没有空，对下一个空进行尝试
        }
        return false;
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        sudoku.solveSudoku(board);
        System.out.println(" ");
    }

}
