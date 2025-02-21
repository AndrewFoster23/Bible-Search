//The Holy Bible
//English Standard Version 2001 ESV

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class BibleSearch {
	public static String currBook = "Gen";
	public static int totalInstances = 0;
	public static String[] books = {"Gen", "Exo", "Lev", "Num", "Deu", "Jos", "Jdg", "Rut", "1Sa", "2Sa",
			"1Ki", "2Ki", "1Ch", "2Ch", "Ezr", "Neh", "Est", "Job", "Psa", "Pro", "Ecc", "Sol",
			"Isa", "Jer", "Lam", "Eze", "Dan", "Hos", "Joe", "Amo", "Oba", "Jon", "Mic", "Nah",
			"Hab", "Zep", "Hag", "Zec", "Mal", "Mat", "Mar", "Luk", "Joh", "Act", "Rom", "1Co", "2Co",
			"Gal", "Eph", "Phi", "Col", "1Th", "2Th", "1Ti", "2Ti", "Tit", "Phm", "Heb", "Jam", "1Pe",
			"2Pe", "1Jo", "2Jo", "3Jo", "Jud", "Rev"};

	public static String[] fullBooks = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Ruth", "1 Samuel", "2 Samuel",
            "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra", "Nehemiah", "Esther", "Job", "Psalms", "Proverbs",
            "Ecclesiastes", "Song of Solomon", "Isaiah", "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Hosea", "Joel",
            "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
            "Matthew", "Mark", "Luke", "John", "Acts", "Romans", "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians",
            "Philippians", "Colossians", "1 Thessalonians", "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus",
            "Philemon", "Hebrews", "James", "1 Peter", "2 Peter", "1 John", "2 John", "3 John", "Jude", "Revelation"};

	public BibleSearch() {}

	public void searchWord(String word) {

		totalInstances = 0;

		ArrayList<String> verseList = new ArrayList<>();
		int counter = 0;

		try {
			Scanner fileReader = new Scanner(new File("BibleESV.txt"));

			while(fileReader.hasNext()) {	//Reads the .txt file line by line
				String line = fileReader.nextLine();

				String lowerCaseVerse = line.toLowerCase();
				String lowerWord = word.toLowerCase();
				boolean containsWord = contains(lowerCaseVerse, lowerWord);

				if(containsWord) {
					line = bookReplace(line);
					verseList.add(line);
					counter++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("\n" + counter + " verses contain " + word + "\n");
		System.out.println(totalInstances + " total instances found" + "\n");

		if(totalInstances > 0) {
			System.out.println("Your text file has been created!\n");
			writeArrayToFile(verseList, word, counter);
		}

	}

	public void randomVerse() {
		int step = (int)(1 + Math.random() * 31103);
		int count = 0;

		try {
			Scanner fileReader = new Scanner(new File("BibleESV.txt"));

			while(fileReader.hasNext()) {
				count++;
				String line = fileReader.nextLine();
				//System.out.println(line);
				if(count == step) {
					line = bookReplace(line);
					System.out.println("\n" +line + "\n");
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String bookReplace(String verse) {
		String ver = "";

		for(int i = 0; i < 66; i++) {
			if(verse.substring(0,3).equals(books[i])) {
				ver = fullBooks[i] + verse.substring(3);
				break;
			}
		}
		return ver;
	}

	public boolean contains(String verse, String word) {
		boolean contains = false;
		for(int i = 0; i < verse.length() - word.length(); i++) {
			if(verse.substring(i, i + word.length()).compareTo(word) == 0) {
				totalInstances++;
				contains = true;
			}
		}
		return contains;
	}

	public boolean containsDash(String verseRef) {
		boolean contains = false;
		for(int i = 0; i < verseRef.length(); i++) {
			if(verseRef.substring(i, i + 1).compareTo("-") == 0) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	public boolean bookCheck(String verseRef) {
		String verse = "";
		for(int i = 0; i < verseRef.length(); i++) {
			if(!verseRef.substring(i, i + 1).equals(" ")) {
				verse += verseRef.substring(i, i + 1);
			}else {
				break;
			}
		}

		for(int i = 0; i < 66; i++) {
			if(verse.equals(fullBooks[i])) {
				return true;
			}
		}
		return false;
	}

	public void printArrayList(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
		    System.out.println(list.get(i) + "\n");
		}
	}

	public void writeArrayToFile(ArrayList<String> list, String word, int counter) {
		File file = new File(word + "_Search.txt");
		FileWriter fw;

		try {
			fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);

			pw.println(counter + " verses contain " + word + "\n");
			pw.println(totalInstances + " total instances of " + word + " were found!\n");

			for(int i = 0; i < list.size(); i++) {
//			    pw.println(list.get(i));
			    pw.println(list.get(i) + "\n");
			}

			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
