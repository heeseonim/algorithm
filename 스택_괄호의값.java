import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2504 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		solve(str);
	}

	public static void solve(String str) {
		String[] array = str.split("");
		int len = array.length;
		int sum = 0;

		Stack<String> stack = new Stack<>();

		int check = 0;
		for (int i = 0; i < len; i++) {
			if (check == -1) {
				System.out.println(0);
				return;
			}

			if (array[i].equals("(") || array[i].equals("[")) {
				stack.push(array[i]);
			} else {
				if (stack.isEmpty()) {
					System.out.println(0);
					return;
				}

				if (array[i].equals(")")) {
					if (stack.peek().equals("(")) {
						stack.pop();
						stack.push("2");
					} else {
						check = stackInnerLoop(stack, "[", "(", 2);
					}
				} else {
					if(stack.peek().equals("[")) {
						stack.pop();
						stack.push("3");
					} else {
						check = stackInnerLoop(stack, "(", "[", 3);
					}
				}
			}
		}
		
		// 최종 스택 안에는 정수만 존재
		// 괄호가 남아있을 시 올바르지 않은 문자열
		while(!stack.isEmpty()) {
			if (stack.peek().equals("(") || stack.peek().equals("[") || stack.peek().equals(")") || stack.peek().equals("]")) {
				System.out.println(0);
				return;
			}
			sum += Integer.parseInt(stack.pop());
		}
		
		System.out.println(sum);
	} // end of solve
	
	public static int stackInnerLoop(Stack<String> stack, String s1, String s2, int value) {
		int result = 0;
		
		while(!stack.isEmpty()) {
			String top = stack.peek();
			
			if (top.equals(s1)) {
				return -1;
			} else if (top.equals(s2)) { // 끝을 만났을 때 - (, [
				stack.pop();
				result *= value;
				stack.push(String.valueOf(result));
				break;
			} else {
				result += Integer.parseInt(stack.pop());
			}
		}
		
		return result;
	}

} // end of class
