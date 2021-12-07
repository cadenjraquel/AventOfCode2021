import java.util.*;
import java.io.*;
import java.nio.file.Paths;

class Lanternfish {
    int timer = -1;

    public Lanternfish(int num) {
        timer = num;
    }

    public void age() {
        if(timer == 0) {
            timer = 6;
        } else {
            timer--;
        }
    }

    public int check() {
        return timer;
    }
}

public class Day6 {
    public static int part1() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day6.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);

            /// parse puzzle input into Lanternfish object list
            List<Lanternfish> population = new ArrayList<Lanternfish>();
            String str = input.nextLine();
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) != ',') {
                    population.add(new Lanternfish(Integer.parseInt(str.substring(i,i+1))));
                }
            }

            /// loops through 80 days
            int length;
            for(int i = 0; i < 80; i++) {
                length = population.size();
                for(int n = 0; n < length; n++) {
                    if(population.get(n).check() == 0) {
                        population.add(new Lanternfish(8));
                    }
                    population.get(n).age();
                }
            }

            input.close();
            return population.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long part2() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day6.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);

            /// parse puzzle input into Lanternfish object list
            long[] population = new long[9];
            Arrays.fill(population, 0);
            String str = input.nextLine();
            input.close();
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) != ',') {
                    population[Integer.parseInt(str.substring(i,i+1))] += 1;
                }
            }
            /// loops through 80 days
            int days = 0;
            long spawn = 0;
            while (days < 256) {
                days++;
                spawn = population[0];
                for(int i = 0; i < population.length - 1; i++) {
                    population[i] = population[i+1];
                }
                population[6] += spawn;
                population[8] = spawn;
            }
            
            long sum = 0;
            for(int i = 0; i < 9; i++) {
                sum += population[i];
            }
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Day 6 Part 1 Solution: " + part1());
        System.out.println("Day 6 Part 2 Solution: " + part2());
    }
}
