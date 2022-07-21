import java.util.*;
import javax.swing.JFileChooser;
import java.io.*;

public class SpellCheck {

	public static void main(String[] args) {
		HashSet<String> hs = new HashSet<>();
		TreeSet<String> wrong = new TreeSet<>();
		try {
			//getting the dictionary
			Scanner filein = new Scanner(new File("words.txt"));			
			while (filein.hasNext()) {
				String tk = filein.next();
				tk = tk.toLowerCase();
				hs.add(tk);
			}
			System.out.println("Size of HashSet: "+hs.size());
			
			//getting the user input file
			File usr = getInputFileNameFromUser();
			Scanner usrin = new Scanner(usr);
			usrin.useDelimiter("[^a-zA-Z]+");
			while (usrin.hasNext()) {
				String word = usrin.next();
				word = word.toLowerCase();
				if (!hs.contains(word) && !wrong.contains(word)) {
					wrong.add(word);
					System.out.print(word+": ");
					printTreeSet(corrections(word, hs));
					System.out.println("");
				}
			}
		}
		catch(Exception e) {
			System.out.println("error.");
		}
	}

	/**
	 * Lets the user select an input file using a standard file
	 * selection dialog box.  If the user cancels the dialog
	 * without selecting a file, the return value is null.
	 */
	static File getInputFileNameFromUser() {
		JFileChooser fileDialog = new JFileChooser();
		fileDialog.setDialogTitle("Select File for Input");
		int option = fileDialog.showOpenDialog(null);
		if (option != JFileChooser.APPROVE_OPTION)
			return null;
		else
			return fileDialog.getSelectedFile();
	}
	
	static TreeSet<String> corrections(String badWord, HashSet dictionary) {
		TreeSet<String> crct = new TreeSet<>();
		ArrayList<String> sugg = new ArrayList<>();
		int n = badWord.length();
		String temp = null;
		
		for (int i = 0; i<n; i++) {
			if (i<n-1)sugg.add(badWord.substring(0,i)+badWord.substring(i+1));      //Delete any one of the letters from the misspelled word
			else sugg.add(badWord.substring(0,i));
			
			if(i==n-2) sugg.add(badWord.substring(0,i)+badWord.charAt(i+1)+badWord.charAt(i));             //Swap any two neighboring characters in the misspelled word.
			if(i<n-2) sugg.add(badWord.substring(0,i)+badWord.charAt(i+1)+badWord.charAt(i)+badWord.substring(i+2));
			
			if(i>0 && i<n-1) {
				if(dictionary.contains(badWord.substring(0,i)) && dictionary.contains(badWord.substring(i))) crct.add(badWord.substring(0,i)+" "+badWord.substring(i));  
			}
			for(char j = 'a'; j<='z'; j++) {
				sugg.add(badWord.substring(0,i)+j+badWord.substring(i+1));  //Change any letter in the misspelled word to any other letter.
				sugg.add(badWord.substring(0,i)+j+badWord.substring(i));    //Insert any letter at any point in the misspelled word.
			}
		}
		for(String i : sugg) {
			if(dictionary.contains(i)) crct.add(i);
		}
		if (crct.isEmpty()) crct.add("(no suggestions)");
		return crct;
	}
	
	static void printTreeSet(TreeSet<String> ts) {
		for(String i:ts) System.out.print(i+" ");
	}

}
