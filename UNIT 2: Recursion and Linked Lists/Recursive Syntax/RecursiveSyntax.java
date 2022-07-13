public class RecursiveSyntax {
	
	public static String[] conjunction = new String[] {"and", "or", "but", "because"};
	public static String[] proper = new String[] {"Fred", "Jane", "Richard Nixon", "Miss America"};
	public static String[] common = new String[] {"man", "woman", "fish", "elephant","unicorn"};
	public static String[] determiner = new String[] {"a", "the", "every", "some"};
	public static String[] adjective = new String[] {"big", "tiny", "pretty", "bald"};
	public static String[] intransitive = new String[] {"runs", "jumps", "talks", "sleeps"};
	public static String[] transitive = new String[] {"loves", "hates", "sees", "knows", "looks for", "finds"};

	public static void main(String[] args) {
		System.out.println(sentence(1,10));
	}
	
	public static String randomItem(String[] listOfStrings) {
		int n = (int) ((listOfStrings.length-1)*Math.random());
		return listOfStrings[n];
	}
	
	public static String sentence(int p1, int p2) {
		int n = (int) ((p1+p2)*Math.random());
		if(n<=p1) {
			return simple_sentence() + " ";
		}
		else{
			int p_1 = (int) (3*Math.random());
			int p_2 = (int) (10*Math.random());
			return simple_sentence() + " " + randomItem(conjunction) + " " + sentence(p_1, p_2);
		}
	}
	
	public static String simple_sentence() {
		return noun_phrase() + " " + verb_phrase();
	}
	
	public static String noun_phrase() {
		int p = (int) (5*Math.random());
		switch (p) {
		case 0:
			return randomItem(proper);
		case 1:
			return randomItem(determiner) + " " + randomItem(common);
		case 2:
			return randomItem(determiner) + " " + randomItem(common) + " who " + verb_phrase();
		case 3:
			int ad = (int) (3*Math.random());
			String st = randomItem(determiner) + " ";
			for (int i = 0; i<ad; i++) {
				st += randomItem(adjective) + " ";
			}
			return st + randomItem(common);
		default:
			int a = (int) (3*Math.random());
			String s = randomItem(determiner) + " ";
			for (int i = 0; i<a; i++) {
				s += randomItem(adjective) + " ";
			}
			return s + " " + randomItem(common) + " who " + verb_phrase();
		}
	}
	
	public static String verb_phrase() {
		int p = (int) (4*Math.random());
		switch (p) {
		case 0:
			return randomItem(intransitive);
		case 1:
			return " " + randomItem(transitive) + " " + noun_phrase();
		case 2:
			return " is " + randomItem(adjective);
		default:
			return " believes that "+simple_sentence();
		}
	}

}
