import java.util.*;
public class LinkedLists {

	public static void main(String[] args) {
		LinkedList <String> lnkd = new LinkedList<>();
		lnkd.add("Me");
		lnkd.add("Mom");
		lnkd.add("Sis");
		System.out.println(lnkd);
		lnkd.set(2, "Dad");
		System.out.println(lnkd);
		System.out.println("Dad at: "+ lnkd.indexOf("Dad"));
	}

}
