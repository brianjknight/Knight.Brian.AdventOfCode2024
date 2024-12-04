package AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

	public List<String> convertInput() throws IOException {
		return Files.readAllLines(Path.of("src/main/resources/day4-input.txt"));
	}
	
	public char[][] get2D(List<String> input) {
		
		char[][] input2D = new char[input.size()][input.get(0).length()];
		
		for (int i=0; i<input.size(); i++) {
			input2D[i] = input.get(i).toCharArray();
		}
		
		return input2D;
	}
	
	public List<String> transposeInput(List<String> input) {
		List<String> transposed = new ArrayList<>();
		
		for (int i=0; i<input.get(0).length(); i++) {
			StringBuilder sb = new StringBuilder();
			
			for (int j=0; j<input.size(); j++) {
				sb.append(input.get(j).charAt(i));
			}
			
			transposed.add(sb.toString());
		}
		
		return transposed;
	}
	
	public List<String> getDiagonalLeftRight(char[][] input2D) {
		
		List<String> diagLeftToRight = new ArrayList<>();
		
		int maxRow = input2D.length;
		int maxCol = input2D[0].length;

		// Diagonals Left to Right increasing columns
		for (int col=0; col<maxCol; col++) {
			StringBuilder diag = new StringBuilder();
			
			int r = 0;
			int c = col;
			
			while (r < maxRow && c < maxCol) {
				diag.append(input2D[r][c]);
				r++;
				c++;
			}
			
			diagLeftToRight.add(diag.toString());
		}
		
		// Diagonals Left to Right decreasing rows
		// first row already appended
		for (int row=1; row<maxRow; row++) {
			StringBuilder diag = new StringBuilder();
			
			int r=row;
			int c = 0;
			
			while (r < maxRow && c < maxCol) {
				diag.append(input2D[r][c]);
				r++;
				c++;
			}
			
			diagLeftToRight.add(diag.toString());
		}
		
		return diagLeftToRight;
	}
	
	public List<String> getDiagonalRightLeft(char[][] input2D) {
		List<String> diagRightToLeft = new ArrayList<>();
		
		int maxRow = input2D.length;
		int maxCol = input2D[0].length;
		
		// Diagonals Right to Left decreasing columns
		for (int col=maxCol-1; col>=0; col--) {
			StringBuilder diag = new StringBuilder();
			
			int r=0;
			int c=col;
			
			while (r < maxRow && c >= 0) {
				diag.append(input2D[r][c]);
				r++;
				c--;
			}
			
			diagRightToLeft.add(diag.toString());
		}
		
		// Diagonals Right to Left increasing rows
		for (int row=1; row<maxRow; row++) {
			StringBuilder diag = new StringBuilder();
			
			int r=row;
			int c=maxCol-1;
			
			while (r < maxRow && c >= 0) {
				diag.append(input2D[r][c]);
				r++;
				c--;
			}
			
			diagRightToLeft.add(diag.toString());
		}
		
		return diagRightToLeft;
	}
	
	public long findXmas(List<String> allConverted) {
		
		long total = 0;
		
		Pattern f = Pattern.compile("XMAS");
		Pattern b = Pattern.compile("SAMX");

		for (String s: allConverted) {
			Matcher mF = f.matcher(s);
			Matcher mB = b.matcher(s);
			
			total += mF.results().count();
			total += mB.results().count();
		}
		
		return total;
	}
	
	public int findMasMasX(char[][] input2D) {
		int total = 0;
		
		int maxRow = input2D.length;
		int maxCol = input2D[0].length;
		
		for (int row=1; row < maxRow-1; row++) {
			for (int col=1; col < maxCol-1; col++) {
				// Find A char
				if (input2D[row][col] == 'A') {

					// Check for MAS MAS x in array
					char ul = input2D[row-1][col-1];
					char ur = input2D[row-1][col+1];
					char bl = input2D[row+1][col-1];
					char br = input2D[row+1][col+1];
					
					if ( ( (ul=='M' && br=='S') || (ul=='S' && br=='M') ) && 
						 ( (ur=='M' && bl=='S') || (ur=='S' && bl=='M') )
						) {
						total++;
					}
				}
			}
		}
		
		return total;
	}
	
	public static void main(String[] args) throws IOException {
		
		Day4 d4 = new Day4();
		
		List<String> input = d4.convertInput();
		List<String> transposed = d4.transposeInput(input);
		
		char[][] input2D = d4.get2D(input);
		
		List<String> diagLeftToRight = d4.getDiagonalLeftRight(input2D);
		List<String> diagRightToLeft = d4.getDiagonalRightLeft(input2D);
		
		List<String> allLines = new ArrayList<>();
		allLines.addAll(input);
		allLines.addAll(transposed);
		allLines.addAll(diagLeftToRight);
		allLines.addAll(diagRightToLeft);
		
		long totalPart1 = d4.findXmas(allLines);
		System.out.println("Part 1: " + totalPart1);
		System.out.println();
		
		int totalPart2 = d4.findMasMasX(input2D);
		System.out.println("Part 2: " + totalPart2);
		
	}
}


