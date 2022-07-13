
public class Employees {
	String lastName;
	String firstName;
	double hourlyWage;
	int yearsWithCompany;

	public static void main(String[] args) {
		Employees[] employee = new Employees[100];
		//for loop
		for (int i = 0; i<100; i++) {
			if(employee[i].yearsWithCompany>=20)
				System.out.println("Name: "+employee[i].firstName+" "+employee[i].lastName+" | Wage: "+employee[i].hourlyWage);
		}
		
		//for each loop
		for(Employees i: employee) {
			if(i.yearsWithCompany>=20) System.out.println("Name: "+i.firstName+" "+i.lastName+" | Wage: "+i.hourlyWage);
		}
	}

}
