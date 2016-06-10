package com.redwood.rp.core.util;
/**
 *=====================================================================
 * ACP Random Number Generator 
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
	
	public static final Random gen = new Random();
	
	/**
	 * @param n
	 * @param maxRange
	 * @param offset
	 * @return
	 */
	public static List<Integer> getRandomInterval(int n, int maxRange, int offset) {
		List<Integer> result = new ArrayList<Integer>(); 
		Set<Integer> used = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {   
			int newRandom;   
			do {   
				newRandom = gen.nextInt(maxRange) + offset; 
				} while (used.contains(newRandom));   
			result.add(new Integer(newRandom));
			used.add(newRandom);
			}
		Collections.sort(result);
		return result;
		}
	
	/**
	 * @param maxRange
	 * @param offset
	 * @return
	 */
	public static int getRandomNumber(int maxRange, int offset) {
		int newRandomNum = gen.nextInt(maxRange) + offset;
		return newRandomNum;
		
	}
	
	
	public static void main(String[] args) {   
		int num = getRandomNumber(10,0);
		System.out.println(num);  
		List<Integer> result = getRandomInterval(10,50,0);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));  
		}
	}
}
