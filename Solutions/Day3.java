import java.io.File;
import java.util.*;
import java.nio.file.Paths;

public class Day3 {

    public static int part1() {
        try {
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day3.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            String val = input.nextLine();
            int length = val.length();
            int[] zeroes = new int[length];
            int[] ones = new int[length];
            Arrays.fill(zeroes, 0);
            Arrays.fill(ones, 0);

            while (input.hasNextLine()) {
                for (int i = 0; i < val.length(); i++) {
                    if (val.charAt(i) == '0') {
                        zeroes[i]++;
                    }
                    if (val.charAt(i) == '1') {
                        ones[i]++;
                    }
                }
                val = input.nextLine();
            }
            input.close();

            int gamma = 0,
                epsilon = 0;

            for(int i = 0; i < length; i++) {
                if (zeroes[i] < ones[i]) {
                    gamma += Math.pow(2,length - i - 1);

                } else {
                    epsilon += Math.pow(2,length - i - 1);
                }
            }
            int solution = gamma * epsilon;
            return solution;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int part2() {
        try {
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day3.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            List<String> ogen = new ArrayList<String>();
            List<String> coscrub = new ArrayList<String>();
            List<String> remove = new ArrayList<String>();
            String val;
            while (input.hasNextLine()) {
                val = input.nextLine();
                ogen.add(val);
                coscrub.add(val);
            }
            input.close();
            
            int length = ogen.get(0).length(),
                zeroes = 0,
                ones = 0;
            char toRemove = ' ';
            
            while (ogen.size() > 1) {
                for(int index = 0; index < length; index++) {
                    toRemove = ' ';
                    ones = 0;
                    zeroes = 0;
                    remove.clear();
                    for(int line = 0; line < ogen.size(); line++) {
                        if(ogen.get(line).charAt(index) == '1') {
                            ones++;
                        } else {
                            zeroes++;
                        }
                    }
                    if (ones < zeroes) {
                        toRemove = '1';
                    } else if (ones > zeroes) {
                        toRemove = '0';
                    } else {
                        toRemove = '0';
                    }
                    for(int line = 0; line < ogen.size(); line++) {
                        if(ogen.get(line).charAt(index) == toRemove) {
                            remove.add(ogen.get(line));
                        }
                    }
                    ogen.removeAll(remove);
                    if(ogen.size() == 1) break;
                }
            }
            
            while (coscrub.size() > 1) {
                for(int index = 0; index < length; index++) {
                    toRemove = ' ';
                    ones = 0;
                    zeroes = 0;
                    remove.clear();
                    for(int line = 0; line < coscrub.size(); line++) {
                        if(coscrub.get(line).charAt(index) == '1') {
                            ones++;
                        } else {
                            zeroes++;
                        }
                    }
                    if (ones < zeroes) {
                        toRemove = '0';
                    } else if (ones > zeroes){
                        toRemove = '1';
                    } else {
                        toRemove = '1';
                    }
                    for(int line = 0; line < coscrub.size(); line++) {
                        if(coscrub.get(line).charAt(index) == toRemove) {
                            remove.add(coscrub.get(line));
                        }
                    }
                    coscrub.removeAll(remove);
                    if(coscrub.size() == 1) break;
                }
            }

            int odec = 0;
            for(int i = 0; i < length; i++) {
                if (ogen.get(0).charAt(i) == '1') {
                    odec += Math.pow(2,length - i - 1);
                }
            }
            int cdec = 0;
            for(int i = 0; i < length; i++) {
                if (coscrub.get(0).charAt(i) == '1') {
                    cdec += Math.pow(2,length - i - 1);
                }
            }
            int solution = odec * cdec;
            return solution;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println("Day 3 Part 1 Solution: " + part1());
        System.out.println("Day 3 Part 2 Solution: " + part2());
    }
}
