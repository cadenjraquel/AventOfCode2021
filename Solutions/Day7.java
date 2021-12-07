import java.util.*;
import java.io.*;
import java.nio.file.Paths;

public class Day7 {
    public static int part1() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day7.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);

            List<Integer> positions = new ArrayList<Integer>();
            String lineToParse = input.nextLine(),
                temp = "";
            input.close();
            int max = 0;
            for(int i = 0; i < lineToParse.length(); i++) {
                if(lineToParse.charAt(i) == ',') {
                    positions.add(Integer.parseInt(temp));
                    if(max < Integer.parseInt(temp)) {
                        max = Integer.parseInt(temp);
                    }
                    temp = "";
                } else {
                    temp += lineToParse.charAt(i);
                }
            }
            positions.add(Integer.parseInt(temp));

            int fuel,
                leastFuel = Integer.MAX_VALUE;
            for(int i = 0; i <= max; i++) {
                fuel = 0;
                for(int n : positions) {
                    fuel += (int)Math.abs(n - i);
                }
                if(fuel < leastFuel) leastFuel = fuel;
            }

            return leastFuel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int part2() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day7.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);

            List<Integer> positions = new ArrayList<Integer>();
            String lineToParse = input.nextLine(),
                temp = "";
            input.close();
            int max = 0;
            for(int i = 0; i < lineToParse.length(); i++) {
                if(lineToParse.charAt(i) == ',') {
                    positions.add(Integer.parseInt(temp));
                    if(max < Integer.parseInt(temp)) {
                        max = Integer.parseInt(temp);
                    }
                    temp = "";
                } else {
                    temp += lineToParse.charAt(i);
                }
            }
            positions.add(Integer.parseInt(temp));

            int fuel,
                leastFuel = Integer.MAX_VALUE;
            for(int i = 0; i <= max; i++) {
                fuel = 0;
                for(int n : positions) {
                    fuel += (int)(0.5 * (Math.abs(n - i) + 1) * Math.abs(n - i)) ;
                }
                if(fuel < leastFuel) leastFuel = fuel;
            }

            return leastFuel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Day 7 Part 1 Solution: " + part1());
        System.out.println("Day 7 Part 2 Solution: " + part2());
    }
}
