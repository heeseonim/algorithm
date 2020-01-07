import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9012 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
ex:		for (int tc = 0; tc < T; tc++) {
			String s = br.readLine();
			
			char[] stack = new char[s.length()];
			int top = -1;
			
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '(') {
					stack[++top] = c;
				} else { // ')' 의 경우
					if (top == -1) { // 스택이 비어있는 상태라면
						System.out.println("NO");
						continue ex;
					}
					if (stack[top] == '(') {
						top--;
					}
				}
			}

			if (top == -1)
				System.out.println("YES");
			else // 남아있다면
				System.out.println("NO");
		}
	}
}
