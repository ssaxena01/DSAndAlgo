package Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.Scanner;

public class SimpleBloomFilter {
	
	private BitSet bitset = new BitSet();
	
	public void add(String val){
		int hash = val.hashCode();
		int index = Math.abs(hash/( Integer.MAX_VALUE/5));
		bitset.set(index);
	}
	
	public boolean find(String val){
		int hash = val.hashCode();
		int index = Math.abs(hash/(Integer.MAX_VALUE/5));
		boolean isPresent = bitset.get(index);
		
		return isPresent;
	}

	public static void main(String args[]) {
		SimpleBloomFilter sbFilter = new SimpleBloomFilter();
		
		try {
			Scanner scanner = new Scanner(new File("/usr/share/dict/words"));
			
			while(scanner.hasNextLine()){
				sbFilter.add(scanner.nextLine());
			}
			
			scanner.close();
			
			System.out.println("Enter words to lookup");
			
			Scanner s = new Scanner(System.in);
			
			while(s.hasNextLine()){
				if(sbFilter.find(s.nextLine())){
					System.out.println("Probably present");
				} else {
					System.out.println("ABsolutely not present ");
				}
			}
			 
			s.close();
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
