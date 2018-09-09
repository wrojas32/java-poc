package com.poc.string;

import java.util.Set;
import java.util.TreeSet;

public class MostRepeatedWord {

	public static void main(String[] args) {
		String sentence = "three sad tigers eat wheat in three sad dishes, all tigers are sad and hungry";
		String[] words = sentence.split(" ");
		Set<CounterWords> CounterList = new TreeSet<CounterWords>();
		CounterWords counterObj;
		int count = 0;
		for (String word : words) {
			count = 0;
			for (String word2 : words) {
				if(word.equals(word2)){
					count++;
				}
			}
			counterObj = new CounterWords();
			counterObj.setCount(count);
			counterObj.setWord(word);
			CounterList.add(counterObj);
		}
		
		int maxUsed = 0;
		String wordMostUsed = "";
		for (CounterWords counterWords : CounterList) {
			System.out.println(counterWords.getCount()+":"+counterWords.getWord());
			if(counterWords.getCount()>maxUsed){
				maxUsed = counterWords.getCount();
				wordMostUsed = counterWords.getWord();
			}
		}
		System.out.println("word most used:"+wordMostUsed);
		
	}
	
	static class CounterWords implements Comparable<CounterWords>{
		private int count;
		private String word;
		
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		@Override
		public int compareTo(CounterWords o) {
			if(count == o.getCount() && word.equals(o.getWord())){
				return 0;
			}else{
				return 1;
			}
		}
		
	}

}
