import java.io.File;
import java.util.Scanner;

public class Day1 {
    public static int part1() {
        /// create variable to store count of increasing consecutive numebrs
        int count = 0;
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File("C:\\Users\\TemTheShopkeeper\\Documents\\GitHub\\AventOfCode2021\\Data\\Day1.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            /// creates variable to store initial value in puzzle input
            int compareto = Integer.parseInt(input.nextLine());
            
            /// creates variable used for comparing consecutive values
            int temp;
            
            /// loop that ends when the puzzle input ends
            while (input.hasNextLine()) {
                /// sets the temp variable to the next line in puzzle input
                temp = Integer.parseInt(input.nextLine());
                
                /// checks if consecutive numbers are increasing
                if (temp > compareto) {
                    count++;
                }
                
                /// sets the first value to the current temp value
                compareto = temp;
            }
            
            /// closes scanner object
            input.close();
            
            /// returns solution
            return (count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int part2() {
        /// create variable to store count of instances of increasing consecutive sums
        int count = 0;
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File("C:\\Users\\TemTheShopkeeper\\Documents\\GitHub\\AventOfCode2021\\Data\\Day1.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            /// creates an array to store the three values of a window
            int[] temp = new int[3];
            
            /// fills the array with the first 3 values in the puzzle input
            for (int i = 2; i >= 0; i--) {
                temp[i] = Integer.parseInt(input.nextLine());
            }
            
            /// stores the sum of the first three values in the puzzle input
            int sum = temp[0] + temp[1] + temp[2];
            
            /// loop that ends once the puzzle input ends
            while (input.hasNextLine()) {
                /// shifts the window one line further
                temp[2] = temp[1];
                temp[1] = temp[0];
                temp[0] = Integer.parseInt(input.nextLine());
                
                /// checks for increasing consecutive sums
                if (sum < temp[0] + temp[1] + temp[2]) count++;
                
                /// sets the temporary sum value to the sum of the current window
                sum = temp[0] + temp[1] + temp[2];
            }
            /// closes scanner object
            input.close();
            
            /// returns solution
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println("Day 1 Part 1 Solution: " + part1());
        System.out.println("Day 1 Part 2 Solution: " + part2());
    }
}
