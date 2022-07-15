public class BNF {

	public static void main(String[] args) {
		for(int i = 0; i<5; i++) System.out.println(BNF());
	}

	static private String BNF() {
		String ret = "";
		int prop = (int) (Math.random()*3);
		if (prop<2) {
			int n = (int) (Math.random()*12) + 1;
			ret+=generateRandomString(n);
			return ret;
		}
		else {
			ret += "(";
			ret += BNF();
			ret += ")";
			return ret;
		}
	}

	static String generateRandomString (int n) {
		String s = "";
		for (int i = 0; i<n; i++) {
			int ascii = (int) (Math.random()*26+97);
			s += "" + (char) ascii;
		}
		return s;
	}

}
