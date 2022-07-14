package turing;

public class Tape {
	Cell current;
	
	Tape(){
		current = new Cell();
		current.content = ' ';
	}
	
	//returns the pointer that points to the current cell.
	public Cell getCurrentCell() {
		return current;
	}
	
	//returns the char from the current cell.
	public char getContent() {
		return current.content;
	}
	
	//changes the char in the current cell to the specified value.
	public void setContent(char ch) {
		current.content = ch;
	}
	
	/*
	 * moves the current cell one position to the left along the tape. 
	 * Note that if the current cell is the leftmost cell that exists, 
	 * then a new cell must be created and added to the tape at the left 
	 * of the current cell, and then the current cell pointer can be moved 
	 * to point to the new cell. The content of the new cell should be a 
	 * blank space.
	 */
	public void moveLeft() {
		if(current.prev == null) {
			current.prev = new Cell();
			current.prev.content = ' ';
			current.prev.next = current;
			current = current.prev;
		}
		else current = current.prev;
	}
	
	/*
	 * moves the current cell one position to the right along the tape. 
	 * Note that if the current cell is the rightmost cell that exists, 
	 * then a new cell must be created and added to the tape at the right 
	 * of the current cell, and then the current cell pointer can be moved 
	 * to point to the new cell. The content of the new cell should be a 
	 * blank space.
	 */
	public void moveRight() {
		if(current.next == null) {
			current.next = new Cell();
			current.next.content = ' ';
			current.next.prev = current;
			current = current.next;
		}
		else current = current.next;
	}
	
	/*
	 * returns a String consisting of the chars from all the cells on the tape, 
	 * read from left to right, except that leading or trailing blank characters 
	 * should be discarded. The current cell pointer should not be moved by 
	 * this method; it should point to the same cell after the method is called 
	 * as it did before. You can create a different pointer to move along the tape 
	 * and get the full contents.
	 */
	public String getTapeContents() {
		Cell runner = current;
		String left = getTapeLeft(runner);
		String right = "";
		while(runner.next!=null) {
			runner = runner.next;
			if (runner.content!=' ') right+=runner.content;
		}
		return left+current.content+right;	
	}
	
	public String getTapeLeft(Cell runner) {
		String t = "";
		if (runner.prev==null) {
			if (runner.content!=' ') t += runner.content;
		}
		else t += getTapeLeft(runner.prev) + runner.content;
		return t;
	}

}
