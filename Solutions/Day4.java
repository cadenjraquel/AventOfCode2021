import java.io.File;
import java.util.*;
import java.nio.file.Paths;

class Board {
    int[][] board = new int[5][5];
    int[][] marked = new int[5][5];
    boolean bingo = false;

    /// creates an empty unmarked dad was here board
    public Board() {};

    /// Sets a space on the board to a specific number
    public void set(int row, int col, int val) {
        board[row][col] = val;
    }

    /// Returns the number at a space on the board
    public int get(int row, int col) {
        return board[row][col];
    }

    /// Converts board to string
    public String toString() {
        return Arrays.deepToString(board);
    }

    /// Creates a copy of current object
    public Board clone() {
        Board r = new Board();
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {
                r.board[row][col] = board[row][col];
            }
        }
        return r;
    }

    /// takes a drawn number and marks it as called on the board
    public void call(int num) {
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {
                if(board[row][col] == num) {
                    marked[row][col] = 1;
                    board[row][col] = 0;
                }
            }
        }
    }

    /// checks to see if the board has a bingo
    public boolean bingo() {
        if(bingo) return false;
        boolean win = false;
        
        /// check rows
        for(int row = 0; row < 5; row++) {
            win = true;
            for(int col = 0; col < 5; col++) {
                if(marked[row][col] != 1) {
                    win = false;
                }
            }
            if(win) {
                bingo = true;
                return true;
            }
        }

        /// check columns
        for(int col = 0; col < 5; col++) {
            win = true;
            for(int row = 0; row < 5; row++) {
                if(marked[row][col] != 1) {
                    win = false;
                }
            }
            if(win) {
                bingo = true;
                return true;
            }
        }
        /// else
        return false;
    }

    public int sum() {
        int sum = 0;
        for(int col = 0; col < 5; col++) {
            for(int row = 0; row < 5; row++) {
                sum += board[row][col];
            }
        }
        return sum;
    }
}

public class Day4 {
    public static int part1() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day4.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            /// Creates a string of the numbers being drawn
            List<Integer> nums = new ArrayList<Integer>();
            String lineToParse = input.nextLine(),
                   temp = "";
            
            for(int c = 0; c < lineToParse.length(); c++) {
                if(lineToParse.charAt(c) == ',') {
                    nums.add(Integer.parseInt(temp));
                    temp = "";
                } else {
                    temp += lineToParse.charAt(c);
                }
            }
            nums.add(Integer.parseInt(temp));

            /// Creates the string used to convert the numbers from Strings to ints
            String str;
            
            /// Creates ArrayList of Board objects
            List<Board> boards = new ArrayList<Board>();

            /// Creates Board object to add to the boards ArrayList
            Board board = new Board();

            /// Loops through whole file, each iteration takes a bingo sheet and converts it into a Board object
            while (input.hasNextLine()) {
                input.nextLine();
                for(int row = 0; row < 5; row++) {
                    str = input.nextLine();
                    for(int col = 0; col < 5; col++) {
                        board.set(row,col,Integer.parseInt(str.substring(3*col,3*col+2).replace(" ","")));
                    }
                }
                boards.add(board.clone());
            }

            int winningNum = 0,
                sum = 0;
            
            drawNumbers:
            for (int i = 0; i < nums.size(); i++) {
                for (Board obj : boards) {
                    obj.call(nums.get(i));
                    if(obj.bingo()) {
                        winningNum = nums.get(i);
                        sum = obj.sum();
                        break drawNumbers;
                    }
                }
            }
            input.close();
            return winningNum * sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int part2() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day4.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            /// Creates a string of the numbers being drawn
            List<Integer> nums = new ArrayList<Integer>();
            String lineToParse = input.nextLine(),
                temp = "";
            
            for(int c = 0; c < lineToParse.length(); c++) {
                if(lineToParse.charAt(c) == ',') {
                    nums.add(Integer.parseInt(temp));
                    temp = "";
                } else {
                    temp += lineToParse.charAt(c);
                }
            }
            nums.add(Integer.parseInt(temp));

            /// Creates the string used to convert the numbers from Strings to ints
            String str;
            
            /// Creates ArrayList of Board objects
            List<Board> boards = new ArrayList<Board>();

            /// Creates Board object to add to the boards ArrayList
            Board board = new Board();

            /// Loops through whole file, each iteration takes a bingo sheet and converts it into a Board object
            while (input.hasNextLine()) {
                input.nextLine();
                for(int row = 0; row < 5; row++) {
                    str = input.nextLine();
                    for(int col = 0; col < 5; col++) {
                        board.set(row,col,Integer.parseInt(str.substring(3*col,3*col+2).replace(" ","")));
                    }
                }
                boards.add(board.clone());
            }

            int winningNum = 0,
                sum = 0;
            for (int i = 0; i < nums.size(); i++) {
                for (Board obj : boards) {
                    obj.call(nums.get(i));
                    if(obj.bingo()) {
                        winningNum = nums.get(i);
                        sum = obj.sum();
                    }
                }
            }
            input.close();
            return winningNum * sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Day 4 Part 1 Solution: " + part1());
        System.out.println("Day 4 Part 2 Solution: " + part2());
    }
}
