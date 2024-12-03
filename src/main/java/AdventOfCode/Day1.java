package AdventOfCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 {
	
	static List<Integer> left = new ArrayList<>();
	static List<Integer> right = new ArrayList<>();
	
	
	public static void convertInput() throws IOException {
		File f = new File("src/main/resources/day1-input.txt");
		BufferedReader r = new BufferedReader(new FileReader(f));
		
		String line;
		
		while((line = r.readLine()) != null) {
			String[] split = line.replaceAll("\s+", " ").split(" ");
			left.add(Integer.parseInt(split[0]));
			right.add(Integer.parseInt(split[1]));
		}
		
	}
	
	static int partTwo() {
	
		Map<Integer, Long> occurences = right.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		
		List<Long> similarity = left.stream().map(i -> i * occurences.getOrDefault(i, 0L)).toList();
		
		return similarity.stream().reduce(0L, (a,b) -> a+b).intValue();
	}
	
	public static void main(String[] args) throws IOException {
		convertInput();
		
		Collections.sort(left);
		Collections.sort(right);
		
		int sum = 0;
		
		for (int i=0; i<left.size(); i++) {
			sum += Math.abs(left.get(i) - right.get(i));
		}
		
		System.out.println("sum: " + sum);
		
		System.out.println("part2: " + partTwo());
	}
}


