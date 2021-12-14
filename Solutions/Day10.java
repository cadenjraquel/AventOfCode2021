import java.io.File;
import java.util.*;
import java.nio.file.Paths;

public class Day10 {
    public static int part1() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day10.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            List<String> lines = new ArrayList<String>();
            while(input.hasNextLine()) {
                lines.add(input.nextLine());
            }
            input.close();

            Map<String,Integer> scores = new HashMap<String,Integer>();
            scores.put(")",3);
            scores.put("]",57);
            scores.put("}",1197);
            scores.put(">",25137);
            Map<String,String> pairs = new HashMap<String,String>();
            pairs.put("(",")");
            pairs.put("[","]");
            pairs.put("{","}");
            pairs.put("<",">");
            
            List<String> expected = new ArrayList<String>();
            int score = 0;
            for (String str : lines) {
                expected.clear();
                for(int i = 0; i < str.length(); i++) {
                    if(str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{' || str.charAt(i) == '<') {
                        expected.add(pairs.get(str.substring(i,i+1)));
                    }
                    
                    if(str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}' || str.charAt(i) == '>') {
                        if(!str.substring(i,i+1).equals(expected.get(expected.size()-1))) {
                            score += scores.get(str.substring(i,i+1));
                            break;
                        } else {
                            expected.remove(expected.size()-1);
                        }
                    }
                }
            }
            /// returns solution
            return score;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long part2() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day10.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            List<String> lines = new ArrayList<String>();
            while(input.hasNextLine()) {
                lines.add(input.nextLine());
            }
            input.close();

            Map<String,Integer> scores = new HashMap<String,Integer>();
            scores.put(")",1);
            scores.put("]",2);
            scores.put("}",3);
            scores.put(">",4);
            Map<String,String> pairs = new HashMap<String,String>();
            pairs.put("(",")");
            pairs.put("[","]");
            pairs.put("{","}");
            pairs.put("<",">");
            
            List<String> expected = new ArrayList<String>();
            List<Long> finishscores = new ArrayList<Long>();
            long temp;
            boolean corrupt;
            for (String str : lines) {
                expected.clear();
                corrupt = false;
                for(int i = 0; i < str.length(); i++) {
                    if(str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{' || str.charAt(i) == '<') {
                        expected.add(pairs.get(str.substring(i,i+1)));
                    }
                    
                    if(str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}' || str.charAt(i) == '>') {
                        if(!str.substring(i,i+1).equals(expected.get(expected.size()-1))) {
                            corrupt = true;
                            break;
                        } else {
                            expected.remove(expected.size()-1);
                        }
                    }
                }
                if(!corrupt) {
                    temp = 0;
                    while (expected.size() != 0) {
                        temp *= 5;
                        temp += scores.get(expected.get(expected.size()-1));
                        expected.remove(expected.size()-1);
                    }
                    finishscores.add(temp);
                }
            }
            int smallest, biggest;
            while(finishscores.size() != 1) {
                smallest = 0;
                biggest = 0;
                for(int i = 0; i < finishscores.size(); i++) {
                    if(finishscores.get(i) > finishscores.get(biggest)) {
                        biggest = i;
                    }
                }
                finishscores.remove(biggest);

                for(int i = 0; i < finishscores.size(); i++) {
                    if(finishscores.get(i) < finishscores.get(smallest)) {
                        smallest = i;
                    }
                }
                finishscores.remove(smallest);
            }
            /// returns solution
            return finishscores.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println("Day 10 Part 1 Solution: " + part1());
        System.out.println("Day 10 Part 2 Solution: " + part2());
    }
}
