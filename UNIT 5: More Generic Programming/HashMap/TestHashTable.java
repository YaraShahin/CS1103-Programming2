public class TestHashTable{

	public static void main(String[] args){
		HashMap table = new HashMap(2);  // Initial size of table is 2.
		
		System.out.println("Testing put(key,value)...");
		table.put("Two","Even");
		table.put("Three","Odd");
		table.put("Four","Even");
		table.put("Five","Odd");
		table.put("Eleven", "Odd");
		System.out.println("HashMap size: " + table.size());

		System.out.println("\nTesting get(key)...");
		System.out.println("The value for Eleven is " + table.get("Eleven"));
		System.out.println("The value for Two is " + table.get("Two"));

		System.out.println("\nTesting containsKey(key)...");
		System.out.println("The table contains Five: " + table.containsKey("Five"));
		System.out.println("The table contains five: " + table.containsKey("five"));
		
		System.out.println("\nTesting remove(key)...");
		table.remove("Four");
		table.remove("five");
		System.out.println("The table contains Four: " + table.containsKey("Four"));
		System.out.println("HashMap size: " + table.size());
	}

}