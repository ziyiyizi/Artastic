package com.javaee.artastic.Artastic.utils;

import java.util.HashSet;
import java.util.Set;


public class JavaTestMain {

	public static void main(String[] arg0) {
		String [] tags =  {"new","new","old"};
		Set<String> tagset = new HashSet<>();
		for(String tag : tags) {
			tagset.add(tag);
		}
		
		for(String tag2 : tagset) {
			System.out.println(tag2);
		}
	}
}
