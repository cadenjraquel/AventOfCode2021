import java.io.*;
import java.util.*;
import java.nio.file.Paths;

class Segments {
    public static String sort(String str) {
        char[] sorted = str.toCharArray();
        Arrays.sort(sorted);
        return new String(sorted);
    } 
    
    public static String removeFrom(String a, String b) {
        String str = "";
        boolean add;
        for(char cha : a.toCharArray()) {
            add = true;
            for(char c : b.toCharArray()) {
                if(cha == c) {
                    add = false;
                }
            }
            if(add) str += cha;
        }
        return str;
    }
}

public class Day8 {
    public static int part1() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day8.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            String[] digits;
            int count = 0;
            String toParse = "";
            while(input.hasNextLine()) {
                toParse = input.nextLine();
                for(int i = 0; i < toParse.length(); i++) {
                    if(toParse.charAt(i) == '|') {
                        toParse = toParse.substring(i+2);
                        break;
                    }
                }
                digits = toParse.split(" ");
                for(int i = 0; i < digits.length; i++) {
                    if(digits[i].length() == 2) count++;
                    if(digits[i].length() == 4) count++;
                    if(digits[i].length() == 3) count++;
                    if(digits[i].length() == 7) count++;
                }
            }
            input.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static int part2() {
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day8.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            
            HashMap<Integer,String> key = new HashMap<Integer,String>();
            HashMap<String,Integer> cipher = new HashMap<String,Integer>();
            String toParse = "",
                   temp;
            String[] clues,
                     enciphered = new String[4];
            int sum = 0;
            while(input.hasNextLine()) {
                key.clear();
                cipher.clear();
                toParse = input.nextLine();
                for(int i = 0; i < toParse.length(); i++) {
                    if(toParse.charAt(i) == '|') {
                        enciphered = toParse.substring(i+2).split(" ");
                        toParse = toParse.substring(0,i);
                        break;
                    }
                }
                clues = toParse.split(" ");
                for(int i = 0; i < clues.length; i++) {
                    if(clues[i].length() == 2) {
                        key.put(1,Segments.sort(clues[i]));
                        cipher.put(Segments.sort(clues[i]),1);
                    }
                    if(clues[i].length() == 4) {
                        key.put(4,Segments.sort(clues[i]));
                        cipher.put(Segments.sort(clues[i]),4);
                    }
                    if(clues[i].length() == 3) {
                        key.put(7,Segments.sort(clues[i]));
                        cipher.put(Segments.sort(clues[i]),7);
                    }
                    if(clues[i].length() == 7) {
                        key.put(8,Segments.sort(clues[i]));
                        cipher.put(Segments.sort(clues[i]),8);
                    }
                }
                /// gets segment a
                Segments.removeFrom(key.get(7),key.get(1));
                
                /// seg count of 5 - 7 == 2 gives 3
                for(int i = 0; i < 10; i++) {
                    if(clues[i].length() == 5) {
                        if(Segments.removeFrom(clues[i],key.get(7)).length() == 2) {
                            key.put(3,Segments.sort(clues[i]));
                            cipher.put(Segments.sort(clues[i]),3);
                        }
                    }
                }
                
                /// seg count of 6 - 7 == 4 gives 6
                for(int i = 0; i < 10; i++) {
                    if(clues[i].length() == 6) {
                        if(Segments.removeFrom(clues[i],key.get(7)).length() == 3) {
                            key.put(0,Segments.sort(clues[i]));
                            cipher.put(Segments.sort(clues[i]),0);
                        }
                        if(Segments.removeFrom(clues[i],key.get(3)).length() == 2 && !(Segments.removeFrom(clues[i],key.get(7)).length() == 3)) {
                            key.put(6,Segments.sort(clues[i]));
                            cipher.put(Segments.sort(clues[i]),6);
                        }
                        if(Segments.removeFrom(clues[i],key.get(3)).length() == 1) {
                            key.put(9,Segments.sort(clues[i]));
                            cipher.put(Segments.sort(clues[i]),9);
                        }
                    }
                }
                
                /// seg count of 5 - 3 = 1 gives 2 and 5
                /// 2,5 - 4 == 1,0 gives 2,5
                for(int i = 0; i < 10; i++) {
                    if(clues[i].length() == 5) {
                        if(Segments.removeFrom(clues[i],key.get(3)).length() == 1) {
                            if(Segments.removeFrom(Segments.removeFrom(clues[i],key.get(3)),key.get(4)).length() == 1) {
                                key.put(2,Segments.sort(clues[i]));
                                cipher.put(Segments.sort(clues[i]),2);
                            }
                            if(Segments.removeFrom(Segments.removeFrom(clues[i],key.get(3)),key.get(4)).length() == 0) {
                                key.put(5,Segments.sort(clues[i]));
                                cipher.put(Segments.sort(clues[i]),5);
                            }
                        }
                    }
                }
                temp = "";
                for(int i = 0; i < 4; i++) {
                    temp += "" + cipher.get(Segments.sort(enciphered[i]));
                }
                sum += Integer.parseInt(temp);
                
            }
            input.close();
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Day 8 Part 1: " + part1());
        System.out.println("Day 8 Part 2: " + part2());
    }
}
