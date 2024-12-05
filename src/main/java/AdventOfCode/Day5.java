package AdventOfCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day5 {

    public static final String fileName = "src/main/resources/day5-input.txt";
    public static Map<Integer, List<Integer>> day5rules = new HashMap<>();

    public void setRules() throws IOException {
        Map<Integer, List<Integer>> rules = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;

        while( !(line = reader.readLine()).isBlank() ) {
            String[] keyValue = line.split("\\|");
            Integer key = Integer.parseInt(keyValue[0]);
            List<Integer> value =  rules.getOrDefault(key, new ArrayList<>());
            value.add(Integer.parseInt(keyValue[1]));

            rules.put(key, value);
            day5rules.put(key, value);
        }
    }

    public List<List<Integer>> getUpdates() throws IOException {
        List<List<Integer>> updates = new ArrayList<>();

        boolean isRule = true;

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;

        while ( (line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                isRule = false;
                continue;
            }
            if (isRule) continue;

            List<Integer> curUpdate = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
            updates.add(curUpdate);
        }

        return updates;
    }

    public boolean isInOrder(List<Integer> update) {

        for (int i=update.size()-1; i>0; i--) {
            int key = update.get(i);
            List<Integer> curRules = day5rules.get(key);

            if (curRules != null) {
                List<Integer> sublist = update.subList(0, i);

                for (Integer x : curRules) {
                    if (sublist.contains(x))
                        return false;
                }
            }
        }

        return true;
    }

    public Integer getMiddle(List<Integer> input) {
        return input.get(input.size()/2);
    }

    public List<List<Integer>> sort(List<List<Integer>> outOfOrder) {
        List<List<Integer>> sorted = new ArrayList<>();

        for (List<Integer> list : outOfOrder) {
            List<Integer> temp = new ArrayList<>(list);
            temp.sort(new UpdateComparator());
            sorted.add(temp);
        }

        return sorted;
    }


    public static void main(String[] args) throws IOException {

        Day5 d5 = new Day5();
        d5.setRules();

        List<List<Integer>> updates = d5.getUpdates();
        List<List<Integer>> outOfOrder = new ArrayList<>();

        int total = 0;

        for (List<Integer> list : updates) {
            if (d5.isInOrder(list)) {
                total += d5.getMiddle(list);
            } else {
                outOfOrder.add(list);
            }

        }

        System.out.println("Part 1 total: " + total);

        // Part Two
        List<List<Integer>> sorted = d5.sort(outOfOrder);
        int reordered = sorted.stream().mapToInt(d5::getMiddle).sum();
        System.out.println("Part 2: " + reordered);
    }
}

class UpdateComparator implements Comparator<Integer> {

    public int compare(Integer a, Integer b) {

        List<Integer> ruleA = Day5.day5rules.get(a);
        if (ruleA != null && ruleA.contains(b)) {
            return -1;
        }

        List<Integer> ruleB = Day5.day5rules.get(b);
        if (ruleB != null && ruleB.contains(a)) {
            return 1;
        }

        return 0;
    }
}
