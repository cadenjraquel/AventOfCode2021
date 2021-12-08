import java.io.*;
import java.util.*;
import java.nio.file.Paths;

class Line {
    public static int intersections = 0;
    int x1,
        x2,
        y1,
        y2;
    
    public Line(int a1, int b1, int a2, int b2) {
        x1 = a1;
        y1 = b1;
        x2 = a2;
        y2 = b2;
    }
    
    public void check(int[][] arr) {
        /// horizontal
        if(y1 == y2) {
            if(x1 < x2) {
                for(int i = x1; i <= x2; i++) {
                    arr[y1][i]++;
                    if(arr[y1][i] == 2) intersections++;
                }
            } else {
                for(int i = x2; i <= x1; i++) {
                    arr[y1][i]++;
                    if(arr[y1][i] == 2) intersections++;
                }
            }
        }
    
        /// vertical
        if(x1 == x2) {
            if(y1 < y2) {
                for(int i = y1; i <= y2; i++) {
                    arr[i][x1]++;
                    if(arr[i][x1] == 2) intersections++;
                }
            } else {
                for(int i = y2; i <= y1; i++) {
                    arr[i][x1]++;
                    if(arr[i][x1] == 2) intersections++;
                }
            }
        }
    }
    
    public void checkWithDiagonals(int[][] arr) {
        check(arr);
        /// diagonals
        if(x1 < x2) {
            if (y1 < y2) {
                for(int i = 0; i <= x2 - x1; i++) {
                    arr[y1 + i][x1 + i]++;
                    if(arr[y1 + i][x1 + i] == 2) intersections++;
                }
            }
            if (y1 > y2) {
                for(int i = 0; i <= x2 - x1; i++) {
                    arr[y1 - i][x1 + i]++;
                    if(arr[y1 - i][x1 + i] == 2) intersections++;
                }
            }
        }
        if(x1 > x2) {
            if (y1 < y2) {
                for(int i = 0; i <= x1 - x2; i++) {
                    arr[y1 + i][x1 -  i]++;
                    if(arr[y1 + i][x1 - i] == 2) intersections++;
                }
            }
            if (y1 > y2) {
                for(int i = 0; i <= x1 - x2; i++) {
                    arr[y1 - i][x1 - i]++;
                    if(arr[y1 - i][x1 - i] == 2) intersections++;
                }
            }
        }
    }
    
    public String toString() {
        return "(" + x1 + ", " + y1 + ") -> (" + x2 + ", " + y2 + ")";
    }
}

public class Day5 {
    public static int part1() {
        Line.intersections = 0;
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day5.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            String str,
                   temp;
            List<Line> lines = new ArrayList<Line>();
            int x1,
                x2,
                y1,
                y2,
                index;
            while (input.hasNextLine()) {
                str = input.nextLine();
                temp = "";
                index = 0;
                while(str.charAt(index) != ',') {
                    temp += str.charAt(index);
                    index++;
                }
                x1 = Integer.parseInt(temp);
                temp = "";
                index++;
                while(str.charAt(index) != ' ') {
                    temp += str.charAt(index);
                    index++;
                }
                y1 = Integer.parseInt(temp);
                temp = "";
                index++;
                index += 3;
                while(str.charAt(index) != ',') {
                    temp += str.charAt(index);
                    index++;
                }
                x2 = Integer.parseInt(temp);
                temp = "";
                index++;
                while(index < str.length()) {
                    temp += str.charAt(index);
                    index++;
                }
                y2 = Integer.parseInt(temp);
                temp = "";
                lines.add(new Line(x1,y1,x2,y2));
            }
            input.close();
            int[][] diagram = new int[1000][1000];
            for(Line line : lines) {
                line.check(diagram);
            }
            
            int count = 0;
            for(int row = 0; row < 1000; row++) {
                for(int col = 0; col < 1000; col ++) {
                    if(diagram[row][col] > 1) {
                            count++;
                    }
                }
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static int part2() {
        Line.intersections = 0;
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day5.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            String str,
                   temp;
            List<Line> lines = new ArrayList<Line>();
            int x1,
                x2,
                y1,
                y2,
                index;
            while (input.hasNextLine()) {
                str = input.nextLine();
                temp = "";
                index = 0;
                while(str.charAt(index) != ',') {
                    temp += str.charAt(index);
                    index++;
                }
                x1 = Integer.parseInt(temp);
                temp = "";
                index++;
                while(str.charAt(index) != ' ') {
                    temp += str.charAt(index);
                    index++;
                }
                y1 = Integer.parseInt(temp);
                temp = "";
                index++;
                index += 3;
                while(str.charAt(index) != ',') {
                    temp += str.charAt(index);
                    index++;
                }
                x2 = Integer.parseInt(temp);
                temp = "";
                index++;
                while(index < str.length()) {
                    temp += str.charAt(index);
                    index++;
                }
                y2 = Integer.parseInt(temp);
                temp = "";
                lines.add(new Line(x1,y1,x2,y2));
            }
            input.close();
            int[][] diagram = new int[1000][1000];
            for(Line line : lines) {
                line.checkWithDiagonals(diagram);
            }
            int count = 0;
            for(int row = 0; row < 1000; row++) {
                for(int col = 0; col < 1000; col++) {
                    if(diagram[row][col] > 1) {
                            count++;
                    }
                }
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Day 5 Part 1: " + part1());
        System.out.println("Day 5 Part 2: " + part2());
    }
}
