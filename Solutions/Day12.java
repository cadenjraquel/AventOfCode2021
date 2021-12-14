import java.io.*;
import java.util.*;
import java.nio.file.Paths;

class Path {
    public static List<List<String>> paths = new ArrayList<List<String>>();
    String one = "", two = "", type;
    public Path(String[] arr) {
        one = arr[0];
        two = arr[1];
    }
    public static void nextPaths(List<Path> connections, ArrayList<String> currentPath) {
        if(currentPath.get(currentPath.size()-1).equals("end")) {
            paths.add(currentPath);
            return;
        }
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < connections.size(); i++) {
            Path p = connections.get(i);
            if(p.one.equals(currentPath.get(currentPath.size()-1)) && Path.NotReturning(currentPath, p.two)) {
                temp = (ArrayList)currentPath.clone();
                temp.add(p.two);
                nextPaths(connections, temp);
            }
            if(p.two.equals(currentPath.get(currentPath.size()-1)) && Path.NotReturning(currentPath, p.one)) {
                temp = (ArrayList)currentPath.clone();
                temp.add(p.one);
                nextPaths(connections, temp);
            }
        }
    }

    public static void nextPaths2(List<Path> connections, ArrayList<String> currentPath) {
        if(currentPath.get(currentPath.size()-1).equals("end")) {
            paths.add(currentPath);
            return;
        }
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < connections.size(); i++) {
            Path p = connections.get(i);
            if(p.one.equals(currentPath.get(currentPath.size()-1)) && Path.NotReturning2(currentPath, p.two)) {
                temp = (ArrayList)currentPath.clone();
                temp.add(p.two);
                nextPaths2(connections, temp);
            }
            if(p.two.equals(currentPath.get(currentPath.size()-1)) && Path.NotReturning2(currentPath, p.one)) {
                temp = (ArrayList)currentPath.clone();
                temp.add(p.one);
                nextPaths2(connections, temp);
            }
        }
    }

    public static boolean NotReturning(List<String> currentPath, String destination) {
        if(currentPath.get(currentPath.size()-1).equals("end")) return false;
        for(String str : currentPath) {
            if(str.equals(destination.toLowerCase())) return false;
        }
        return true;
    }

    public static boolean NotReturning2(List<String> currentPath, String destination) {
        if(currentPath.get(currentPath.size()-1).equals("end")) return false;
        boolean twice = false;
        for(int a = 0; a < currentPath.size(); a++) {
            for(int b = 0; b < currentPath.size(); b++) {
                if(currentPath.get(a).equals(currentPath.get(b).toLowerCase()) && a != b) {
                    twice = true;
                }
            }
        }
        if(twice || destination.equals("start")) {
            for(String str : currentPath) {
                if(str.equals(destination.toLowerCase())) return false;
            }  
            return true;
        } else {
            int check = 0;
            for(String str : currentPath) {
                    if(str.equals(destination.toLowerCase())) check++;
            }
            if(check >= 2) return false;  
            return true;
        }
    }
}

public class Day12 {
    public static int part1() {
        Path.paths.clear();
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day12.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            List<Path> connections = new ArrayList<Path>();
            while(input.hasNextLine()) {
                connections.add(new Path(input.nextLine().split("-")));
            }
            input.close();
            ArrayList<String> start = new ArrayList<String>();
            start.add("start");
            Path.nextPaths(connections,start);
            return Path.paths.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static int part2() {
        Path.paths.clear();
        try {
            /// creates a new file from input data which stores the puzzle input
            File file = new File(Paths.get("").toAbsolutePath().toString() + "\\Data\\Day12.txt");

            /// creates a scanner object to read from the puzzle input
            Scanner input = new Scanner(file);
            List<Path> connections = new ArrayList<Path>();
            while(input.hasNextLine()) {
                connections.add(new Path(input.nextLine().split("-")));
            }
            input.close();
            ArrayList<String> start = new ArrayList<String>();
            start.add("start");
            Path.nextPaths2(connections,start);
            return Path.paths.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Day 12 Part 1: " + part1());
        System.out.println("Day 12 Part 2: " + part2());
    }
}
