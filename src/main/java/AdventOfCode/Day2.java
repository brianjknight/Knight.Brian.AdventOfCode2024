package AdventOfCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {
	
	public List<List<Integer>> convertInput() throws IOException {
		List<List<Integer>> reports = new ArrayList<>();
		
		BufferedReader r = new BufferedReader(new FileReader("src/main/resources/day2-input.txt"));
		
		String line;
		
		while((line = r.readLine()) != null) {
			
			List<Integer> curRep = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList(); 
			
			reports.add(curRep);
		}
		
		return reports;
	}
	
	public boolean isSafeIncreasing(List<Integer> report) {
	
		// check increasing
		for (int i=0; i<report.size()-1; i++) {
			int a = report.get(i);
			int b = report.get(i+1);
			
			if (b-a < 1 || b-a > 3) return false;
		}

		return true;
	}
	
	public boolean isSafeIncDampener(List<Integer> report) {
		
		// check increasing
		for (int i=0; i<report.size()-1; i++) {
			int a = report.get(i);
			int b = report.get(i+1);
			
			if (b-a < 1 || b-a > 3) {
				
				List<Integer> copyA = new ArrayList<>(report);
				copyA.remove(i);
				List<Integer> copyB = new ArrayList<>(report);
				copyB.remove(i+1);
				
				if (this.isSafeIncreasing(copyA) || this.isSafeIncreasing(copyB)) return true;
				else return false;
			}
		}

		return true;
	}
	
	public boolean isSafeDecreasing(List<Integer> report) {
		// check decreasing
		for (int i=0; i<report.size()-1; i++) {
			int a = report.get(i);
			int b = report.get(i+1);
			
			if (a-b < 1 || a-b > 3) return false;
		}
		
		return true;
	}
	
	public boolean isSafeDecDampener(List<Integer> report) {
		
		// check increasing
		for (int i=0; i<report.size()-1; i++) {
			int a = report.get(i);
			int b = report.get(i+1);
			
			if (a-b < 1 || a-b > 3) {
				
				List<Integer> copyA = new ArrayList<>(report);
				copyA.remove(i);
				List<Integer> copyB = new ArrayList<>(report);
				copyB.remove(i+1);
				
				if (this.isSafeDecreasing(copyA) || this.isSafeDecreasing(copyB)) return true;
				else return false;
			}
		}

		return true;
	}	
	
	
	
	public static void main(String[] args) throws IOException {
		
		Day2 d2 = new Day2();
		
		List<List<Integer>> reports = d2.convertInput();
		
		int totalSafe = 0;
		
		for (List<Integer> rep : reports) {
			if (d2.isSafeDecreasing(rep) || d2.isSafeIncreasing(rep)) totalSafe++;
		}
		
		System.out.println("Safe reports: " + totalSafe);
		
		System.out.println();
		System.out.println("Part Two:");
		
		int safeDampened = 0;
		for (List<Integer> rep : reports) { 
			if (d2.isSafeDecDampener(rep) || d2.isSafeIncDampener(rep)) safeDampened++;
		}
		System.out.println("safe dampened reports: " + safeDampened);
	}
}


