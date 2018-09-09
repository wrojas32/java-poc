package com.poc.string;

import java.util.Arrays;

public class InvertLastChar {

	public static void main(String[] args) {
		String initial = new String("abcdefg");
		int lenght = initial.length();
		char last = initial.charAt(lenght-1);
		char[] array = initial.toCharArray();
		array[lenght-1]= array[lenght-2] ;
		array[lenght-2] = last;
		System.out.println(Arrays.toString(array));
		
	}

}
