package AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
	
	String convertInput() throws IOException {
		
		return Files.readString(Path.of("src/main/resources/day3-input.txt"));
	}
	
	List<String> getMultipliers(String input) {
		
		List<String> multipliers = new ArrayList<>();
		
		Pattern p = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
		
		Matcher m = p.matcher(input);
		
		while(m.find()) {
			multipliers.add(m.group());
		}
		
		return multipliers;
	}
	
	long calculate(List<String> multipliers) {
		
		long total = 0;
		
		for (String s : multipliers) {
			
			if(!s.startsWith("do")) {
				String[] arr = s.replaceAll("[mul\\(\\)]", "").split(",");
				
				total += Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
			}
			
		}
	
		return total;
	}
	
	long caclulateEnabled(List<String> multipliers) {
		long total = 0;
		boolean enabled = true;
		
		for (String s : multipliers) {
			if(s.equals("do()")) enabled = true;
			if(s.equals("don't()")) enabled = false;
			
			if(!s.startsWith("do") && enabled) {
				String[] arr = s.replaceAll("[mul\\(\\)]", "").split(",");
				
				total += Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
			}
		}
		
		return total;
	}
	
	public static void main(String[] args) throws IOException {

		Day3 d3 = new Day3();
		
		String input = d3.convertInput();
		
		List<String> multipliers = d3.getMultipliers(input);
		
		long total = d3.calculate(multipliers);
		
		System.out.println("Part 1 total: " + total);
		
		long totalTwo = d3.caclulateEnabled(multipliers);
		
		System.out.println("Part 2 total: " + totalTwo);
	}
}


