import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10828 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stack = new int[N + 1];

		int top = -1;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String s = st.nextToken();
			switch (s) {
			case "push":
				stack[++top] = Integer.parseInt(st.nextToken());
				break;
			case "top":
				if (top == -1)
					System.out.println(-1);
				else
					System.out.println(stack[top]);
				break;
			case "size":
				System.out.println(top + 1);
				break;
			case "empty":
				if (top == -1)
					System.out.println(1);
				else
					System.out.println(0);
				break;
			case "pop":
				if (top == -1)
					System.out.println(-1);
				else
					System.out.println(stack[top--]);
				break;
			}
		}
	}
}
