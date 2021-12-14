import java.io.*;
import java.util.*;
import java.nio.file.Paths;

class Octopus {
    public static void flash(int[][] arr, Point p) {
        Octopus.flash(arr, p.y, p.x);
    }
    
    public static void flash(int[][] arr, int a, int b) {
        arr[a][b] = 0;
        for(int row = a-1; row <= a+1; row++) {
            for(int col = b-1; col <= b+1; col++) {
                try {
                    if(arr[row][col] != 0) arr[row][col]++;
                    if(arr[row][col] > 9) Octopus.flash(arr, row, col);
                } catch (Exception e) {}
            }
        }
    }

}

class Point {
    int x, y;
    public Point(int a, int b) {
        x = a;
        y = b;
    }
}


public class Day11 {
    public static int part1() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day11.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            List<String> temp = new ArrayList<String>();
            while(input.hasNextLine()) {
                temp.add(input.nextLine());
            }
            input.close();
            int[][] grid = new int[10][10];
            for(int row = 0; row < 10; row++) {
                for(int col = 0; col < 10; col++) {
                    grid[row][col] = Integer.parseInt(temp.get(row).substring(col,col+1));
                }
            }
            int flashes = 0;
            for(int step = 0; step < 100; step++) {
                for(int row = 0; row < 10; row++) {
                    for(int col = 0; col < 10; col++) {
                        grid[row][col]++;
                    }
                }
                for(int row = 0; row < 10; row++) {
                    for(int col = 0; col < 10; col++) {
                        if(grid[row][col] > 9) Octopus.flash(grid, row, col);
                    }
                }
                for(int row = 0; row < 10; row++) {
                    for(int col = 0; col < 10; col++) {
                        if(grid[row][col] == 0) {
                            flashes++;
                        }
                    }
                }
            }
            
            return flashes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static int part2() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day11.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            List<String> temp = new ArrayList<String>();
            while(input.hasNextLine()) {
                temp.add(input.nextLine());
            }
            input.close();
            int[][] grid = new int[10][10];
            for(int row = 0; row < 10; row++) {
                for(int col = 0; col < 10; col++) {
                    grid[row][col] = Integer.parseInt(temp.get(row).substring(col,col+1));
                }
            }
            boolean allflash = false;
            int step = 0;
            for(int i = 1; !allflash; i++) {
                step = i;
                for(int row = 0; row < 10; row++) {
                    for(int col = 0; col < 10; col++) {
                        grid[row][col]++;
                    }
                }
                for(int row = 0; row < 10; row++) {
                    for(int col = 0; col < 10; col++) {
                        if(grid[row][col] > 9) Octopus.flash(grid, row, col);
                    }
                }
                allflash = true;
                for(int row = 0; row < 10; row++) {
                    for(int col = 0; col < 10; col++) {
                        if(grid[row][col] != 0) {
                            allflash = false;
                        }
                    }
                }
            }
            return step;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Day 11 Part 1: " + part1());
        System.out.println("Day 11 Part 2: " + part2());
    }
}
