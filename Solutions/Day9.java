import java.io.*;
import java.util.*;
import java.nio.file.Paths;

class Basin {
    public static int size(int[][] arr, Point p) {
        return Basin.size(arr, p.y, p.x);
    }
    
    public static int size(int[][] arr, int row, int col) {
        int sum = 1;
        arr[col][row] = 9;
        try {
            if(arr[col-1][row] != 9) {
                sum += Basin.size(arr, row, col-1);
            }
        } catch (Exception e) {}
        try {
            if(arr[col+1][row] != 9) {
                sum += Basin.size(arr, row, col+1);
            }
        } catch (Exception e) {}
        try {
            if(arr[col][row-1] != 9) {
                sum += Basin.size(arr, row-1, col);
            }
        } catch (Exception e) {}
        try {
            if(arr[col][row+1] != 9) {
                sum += Basin.size(arr, row+1, col);
            }
        } catch (Exception e) {}
        return sum;
    }

}

class Point {
    int x, y;
    public Point(int a, int b) {
        x = a;
        y = b;
    }
}


public class Day9 {
    public static int part1() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day9.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            List<String> temp = new ArrayList<String>();
            while(input.hasNextLine()) {
                temp.add(input.nextLine());
            }
            int heighth = temp.size(),
                width = temp.get(0).length();
            int[][] grid = new int[heighth][width];
            for(int row = 0; row < heighth; row++) {
                for(int col = 0; col < width; col++) {
                    grid[row][col] = Integer.parseInt(temp.get(row).substring(col,col+1));
                }
            }
            
            boolean lowPoint;
            int sum = 0;
            List<Integer> around = new ArrayList<Integer>();
            for(int row = 0; row < heighth; row++) {
                for(int col = 0; col < width; col++) {
                    lowPoint = true;
                    around.clear();
                    for(int row2 = row-1; row2 <= row+1; row2++) {
                        for(int col2 = col-1; col2 <= col+1; col2++) {
                            try {
                                if(row != row2 || col != col2) around.add(grid[row2][col2]);
                            } catch (Exception e) {}
                        }
                    }
                    for(int n : around) {
                        if(n <= grid[row][col]) lowPoint = false;
                    }
                    if(lowPoint) sum += grid[row][col] + 1;
                }
            }
            
            input.close();
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static int part2() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day9.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            List<String> temp = new ArrayList<String>();
            while(input.hasNextLine()) {
                temp.add(input.nextLine());
            }
            input.close();
            int heighth = temp.size(),
                width = temp.get(0).length();
            int[][] grid = new int[heighth][width];
            for(int row = 0; row < heighth; row++) {
                for(int col = 0; col < width; col++) {
                    grid[row][col] = Integer.parseInt(temp.get(row).substring(col,col+1));
                }
            }
            
            boolean lowPoint;
            List<Point> lowPoints = new ArrayList<Point>();
            List<Integer> around = new ArrayList<Integer>();
            for(int row = 0; row < heighth; row++) {
                for(int col = 0; col < width; col++) {
                    lowPoint = true;
                    around.clear();
                    for(int row2 = row-1; row2 <= row+1; row2++) {
                        for(int col2 = col-1; col2 <= col+1; col2++) {
                            try {
                                if(row != row2 || col != col2) around.add(grid[row2][col2]);
                            } catch (Exception e) {}
                        }
                    }
                    for(int n : around) {
                        if(n <= grid[row][col]) lowPoint = false;
                    }
                    if(lowPoint) lowPoints.add(new Point(row,col));
                }
            }
            List<Integer> sizes = new ArrayList<Integer>();
            
            for (Point p : lowPoints) {
                sizes.add(Basin.size(grid,p));
            }
            int product = 1;
            int largest = 0,
                indexOfLargest = -1;
            for(int n = 0; n < 3; n++) {
                for(int i = 0; i < sizes.size(); i++) {
                    if(sizes.get(i) > largest) {
                        largest = sizes.get(i);
                        indexOfLargest = i;
                    }
                }
                product *= largest;
                sizes.remove(indexOfLargest);
                largest = 0;
                indexOfLargest = -1;
            }
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Day 9 Part 1: " + part1());
        System.out.println("Day 9 Part 2: " + part2());
    }
}
