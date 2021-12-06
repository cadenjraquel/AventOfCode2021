import java.io.File;
import java.util.Scanner;

public class Day2 {
    public static int part1() {
        int horiz = 0, depth = 0;
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File("C:\\Users\\TemTheShopkeeper\\Documents\\GitHub\\AventOfCode2021\\Data\\Day2.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            String check;
            while (input.hasNextLine()) {
                check = input.nextLine();
                if(check.substring(0,1).equals("f")) {
                    horiz += Integer.parseInt(check.substring(check.length()-1));
                }
                if(check.substring(0,1).equals("d")) {
                    depth += Integer.parseInt(check.substring(check.length()-1));
                }
                if(check.substring(0,1).equals("u")) {
                    depth -= Integer.parseInt(check.substring(check.length()-1));
                }
            }
            
            /// closes scanner object
            input.close();
            
            /// returns solution
            return horiz * depth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int part2() {
        int horiz = 0, depth = 0, aim = 0;
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File("C:\\Users\\TemTheShopkeeper\\Documents\\GitHub\\AventOfCode2021\\Data\\Day2.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            String check;
            while (input.hasNextLine()) {
                check = input.nextLine();
                if(check.substring(0,1).equals("f")) {
                    horiz += Integer.parseInt(check.substring(check.length()-1));
                    depth += aim * Integer.parseInt(check.substring(check.length()-1));
                }
                if(check.substring(0,1).equals("d")) {
                    aim += Integer.parseInt(check.substring(check.length()-1));
                }
                if(check.substring(0,1).equals("u")) {
                    aim -= Integer.parseInt(check.substring(check.length()-1));
                }
            }
            
            /// closes scanner object
            input.close();
            
            /// returns solution
            return horiz * depth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println("Day 2 Part 1 Solution: " + part1());
        System.out.println("Day 2 Part 2 Solution: " + part2());
    }
}
