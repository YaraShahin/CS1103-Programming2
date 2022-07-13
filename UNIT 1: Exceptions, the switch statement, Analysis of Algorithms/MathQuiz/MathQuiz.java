import java.util.Scanner;

public class MathQuiz {

	public static void main(String[] args) {
		int type, score = 0, right_answers = 0, right_answers_2 = 0;
		Problem problem;
		Scanner in  = new Scanner(System.in);
		
		for(int i = 1; i<11; i++) {
			//getting the type of question
			type = (int) (3*Math.random());
			switch (type){
			case 0:
				problem = new AdditionProblem();
				break;
			case 1:
				problem = new SubtractionProblem();
				break;
			case 2:
				problem = new MultiplicationProblem();
				break;
			default:
				problem = new DivisionProblem();
			}
			
			System.out.println("Question "+i+" out of 10:");
			
			//trial 1
			System.out.println(problem.getProblem());
			int usr = in.nextInt();
			int answer = problem.getAnswer();
			if (usr==answer) {
				score +=10;
				right_answers++;
				System.out.println("Correct!");
			}
			//trial 2
			else {
				System.out.println("Wrong! Try again: "+problem.getProblem());
				usr = in.nextInt();
				if (usr==answer) {
					score+=5;
					right_answers_2++;
					System.out.println("Correct!");
				}
				else {
					System.out.println("Wrong again! The correct answer is "+ problem.getAnswer());
				}
			}
		}
		System.out.println("The quiz is over!");
		System.out.println("Score: "+score);
		System.out.println("Questions you got right on the first trial: "+right_answers);
		System.out.println("Questions you got right on the second trial: "+right_answers_2);
		

	}

}
