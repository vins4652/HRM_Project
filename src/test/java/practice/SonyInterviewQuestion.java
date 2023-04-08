package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SonyInterviewQuestion {

	public static void main(String[] args) {
		// Call only one method at a time
//		reverseNameUsing_StringBuilder();
//		reverseNameUsing_CharArray();
//		printInPyramid();
//		int[] arr = { 1, 6, 8 };
//		printMissingNumUsing_Array(arr, 10);
//		printMissingNumUsing_SetCollection(arr, 10);
//		print_possible_substring();
//		print_possible_substring_without_duplicates();
		print_Ascci_of_Higher_alpa_of_String();
//		print_first_duplicate_char_with_OneForLoop();

	}

	public static void reverseNameUsing_StringBuilder() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the name to reverse");
		String name = s.nextLine();
		StringBuilder sb = new StringBuilder(name);
		StringBuilder revName = sb.reverse();
		System.out.println("Reversed name is : " + revName);
		s.close();
	}

	public static void reverseNameUsing_CharArray() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the name to reverse");
		String name = s.nextLine();
		char[] name_array = name.toCharArray();
		String revName = "";
		for (int i = name_array.length; i > 0; i--) {
			revName += name_array[i - 1];
		}
		System.out.println("Reversed name is : " + revName);
		s.close();
	}

	public static void printInPyramid() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the name to print in pyramid");
		String name = s.nextLine();

		for (int i = 0; i <= name.length(); i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(name.charAt(j));
			}
			System.out.println();
		}
		s.close();
	}

	public static void printMissingNumUsing_Array(int[] input, int limit) {
		int[] num = input;
		int a = 1;
		int n = limit;
		int[] res = new int[n - num.length];
		int resIndex = 0;

		for (int i = 0; i < num.length; i++) {
			for (int j = 1; j <= n; j++) {
				if (num[i] != a) {
					res[resIndex] = a;
					resIndex++;
					a++;
				}

				if (num[i] == a) {
					a++;
					break;
				}
			}
		}

		while (a <= n) {
			res[resIndex] = a;
			resIndex++;
			a++;
		}

		System.out.println("Missing Numbers in array upto given limit");
		for (int i : res) {
			System.out.println(i);
		}

	}

	public static void printMissingNumUsing_SetCollection(int[] input, int limit) {
		int[] num = input;
		int a = 1;
		int n = limit;
		Set<Integer> SI = new HashSet<Integer>();

		for (int i = 0; i < num.length; i++) {
			for (int j = 1; j <= n; j++) {
				if (num[i] != a) {
					SI.add(a);
					a++;
				}

				if (num[i] == a) {
					a++;
					break;
				}
			}
		}

		while (a <= n) {
			SI.add(a);
			a++;
		}
		System.err.println("Missing Numbers in array upto given limit");
		for (Integer integer : SI) {
			System.err.println(integer);
		}
	}

	public static void print_possible_substring() {

		String input = "aabbbaccds";

//		for (int i = 0; i < input.length(); i++) {
//			for (int j = i+1; j < input.length(); j++) {
//				System.out.println(input.substring(j-1, j+1));
//			}
//		}

		for (int j = 1; j < input.length(); j++) {
			System.out.println(input.substring(j - 1, j + 1));
		}
	}

	public static void print_possible_substring_without_duplicates() {

		String input = "aabbbaccds";

		Set<String> outputs = new HashSet<String>();
		for (int j = 1; j < input.length(); j++) {
			outputs.add(input.substring(j - 1, j + 1));
		}

		for (String output : outputs) {
			System.err.println(output);
		}

	}

	public static void print_Ascci_of_Higher_alpa_of_String() {
		Scanner s = new Scanner(System.in);
		System.out.println("enter a string");
		String str = s.nextLine();
		// String str = "dabDhgjB";
		List<Character> lowercase = new ArrayList<Character>();
		List<Character> uppercase = new ArrayList<Character>();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				lowercase.add(str.charAt(i));
			} else {
				uppercase.add(str.charAt(i));
			}
		}
		char asci = 0;
		System.out.println("Alphabet which are present in both uppercase and lowercase");
		for (int i = 0; i < lowercase.size(); i++) {
			for (int j = 0; j < uppercase.size(); j++) {
				if (lowercase.get(i) == uppercase.get(j) + 32) {
					System.out.print(uppercase.get(j));
					char res = uppercase.get(j);
					if (asci < res) {
						asci = res;
					}
				}
			}
		}
		System.out.println();// just to sent the cursor to the next line
		System.out.println("Charector Whose Ascii value is more in all : " + asci);
		s.close();
	}

	public static void print_first_duplicate_char_with_OneForLoop() {

		String str = "selenium";
		char[] name = str.toLowerCase().toCharArray();

		// Creates an empty hashset to add a single char in a loop
		HashSet<Character> h = new HashSet<Character>();

		// Traverse the char array from left to right
		for (int i = 0; i <= name.length - 1; i++) {
			char c = name[i];

			// If char is already in hash set, it will print and break the loop
			// first time hashset will be empty
			if (h.contains(c)) {		//1) char c = s --> h.contains(s) --> false (HashSet is empty)
				System.out.println(c);	//2) char c = e --> h.contains(e) --> false (HashSet has only 's')
				break;					//3) char c = l --> h.contains(l) --> false (HashSet has 's','e')
			} 							//4) char c = e --> h.contains(e) --> true, break (HashSet has 's','e','l')
			else {						//1) Else add element to hash set(HashSet has 's' in it now)
				h.add(c);				//2) Else add element to hash set(HashSet has 's','e' in it now)
			}							//3) Else add element to hash set(HashSet has 's','e','l' in it now)
		}

	}

}
