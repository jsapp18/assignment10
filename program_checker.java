import java.io.*;
import java.util.*;

//checks for mismatched symbols
public class program_checker{
	private static void symbol_checker(Stack<Character> stack, Character letter){
		if ((stack.peek() == '(' && letter == ')') || (stack.peek() == '[' && letter == ']') || (stack.peek() == '{' && letter == '}')){
			stack.pop();	
		}
		else if ((stack.peek() != '(' && letter == ')') || (stack.peek() != '[' && letter == ']') || (stack.peek() != '{' && letter == '}')){
			System.out.println("The Java source-code does not have" + " correct pairs.");
			System.exit(1);
		}
	}

    //the test code
    public static void main(String[] args) throws IOException {
		if (args.length != 1){
			System.out.println("programchecker");
			System.exit(0);
		}
		File file = new File(args[0]);
		if (!file.exists()){
			System.out.println("The refrenced file does not exist : (");
			System.exit(1);
		}
		Stack<Character> symbol_check = new Stack<>();
		try(
			Scanner grabber = new Scanner(file);
		){
			while (grabber.hasNext()) {
				String line = grabber.nextLine();
				for (int i = 0; i < line.length(); i++) {
					char letter = line.charAt(i);
					if (letter == '(' || letter == '{' || letter == '['){
						symbol_check.push(letter);
					}else if (letter == ')' || letter == '}' || letter == ']'){
						symbol_checker(symbol_check, letter);
					}
				}
			}
		}
		System.out.println("The cdoe " + (symbol_check.isEmpty() ? "has" : "doesn't have") + " correct of symbols.");	
	}
}