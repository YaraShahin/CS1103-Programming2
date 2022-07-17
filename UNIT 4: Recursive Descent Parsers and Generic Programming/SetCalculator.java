import java.util.*;

public class SetCalculator {
	public static TreeSet<Integer> nums1 = new TreeSet<>();
	public static TreeSet<Integer> nums2 = new TreeSet<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		int count = 0;
		int dd = 1;

		//while for each input line
		while (sc.hasNextLine()) {			
			//defining the variables for this problem
			String s = sc.nextLine();
			if (s.equals("")) break;
			nums1.clear();
			nums2.clear();
			
			count++;
			System.out.println("Processing problem "+count);

			//removing all spaces from the whole string so processing is easier
			s = removeSpaces(s);
			System.out.println(s);

			//filling first set, getting the operation character, filling the second set
			int ret = getSet(s, 1);
			if (ret!=-1) {
				ret++;
				char op = s.charAt(ret);
				if(op!='-' && op!='+' && op  !='*') {
					System.out.println("Wrong format. Expected + or - or *");
					continue;
				}
				else {
					ret++;
					ret = getSet(s.substring(ret), 2);
					if (ret!=-1) {
						switch(op) {
						case '+':
							nums1.addAll(nums2);
							System.out.println(nums1);
							break;
						case '-':
							nums1.removeAll(nums2);
							System.out.println(nums1);
							break;
						case '*': 
							nums1.retainAll(nums2);
							System.out.println(nums1);
							break;
						}
					}
				}
			}
		}
		System.out.println("Terminated.");
	}

	private static String removeSpaces(String s) {
		String ans = "";
		for (int i = 0; i<s.length(); i++)
			if (s.charAt(i)!=' ') ans += s.charAt(i);
		return ans;
	}

	//false is fails
	private static int getSet(String s, int id) {
		int c = 0;
		
		if (s.charAt(c)!='[') {
			System.out.println("Wrong format. Expected an opening square bracket");
			return -1;
		}
		c++;
		
		String num = "";
		while(s.charAt(c)!=']') {
			char ch = s.charAt(c);
			c++;
			
			if (ch!='0' && ch!='1' &&ch!='2' && ch!='3' &&ch!='4' && ch!='5'&&ch!='6'&&ch!='7'&& ch!='8'&& ch!='9'&&ch!=',') {
				System.out.println("Wrong format. Expected a number or a comma ");
				return -1;
			}
			if (ch==',') {
				if (num.length()>0) {
					if (id==1) nums1.add(Integer.parseInt(num));
					else nums2.add(Integer.parseInt(num));
					num = "";
				}
				else {
					System.out.println("Wrong format. No number before comma. ");
					return -1;
				}
			}
			else num+=ch;
		}
		if (num.length()>0) {
			if (id==1) nums1.add(Integer.parseInt(num));
			else nums2.add(Integer.parseInt(num));
		}
		else {
			System.out.println("Wrong format. Expected number. ");
			return -1;
		}
		return c;
	}
}
