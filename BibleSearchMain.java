import java.util.Scanner;

//May all glory be to God

public class BibleSearchMain {

	private static Scanner input;

	public static void main(String[] args) {
		while(true) {	//Continues until 6 is entered to exit the program
			Scanner input = new Scanner(System.in);

			int choice = -1;
			while(choice < 0) {
				System.out.println("Enter a number (1, 2, or 3) to select an option:");
				System.out.println("1. Search a word \n2. Random Verse "
						+ "\n3. Exit");

				String userChoice = input.nextLine();	//User number choice input

				try {
					choice = Integer.valueOf(userChoice);
				}catch(NumberFormatException e) {
					choice = -1;
					System.out.println("\nEnter a choice as an integer.\n");
				}
			}

			if(choice == 1) {
				System.out.print("Enter word: ");
				String bibleWord = input.nextLine();
				BibleSearch bible = new BibleSearch();
				bible.searchWord(bibleWord);

			}else if(choice == 2) {
				BibleSearch bible2 = new BibleSearch();
				bible2.randomVerse();
			}else if(choice == 3){
				break;	//Exits program main loop
			}else {
				System.out.println("\nChoose a valid number.\n");	//Occurs if user inputs an invalid value i.e. a string or an invalid number
			}
		}
	}
}
