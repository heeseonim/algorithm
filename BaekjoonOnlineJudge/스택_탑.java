import java.io.*;
import java.util.*;

public class 스택_탑 {
	public static class top {
		int index;
		int height;

		public top(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		Stack<top> stack = new Stack<>();

		for (int i = 1; i <= N; i++) {
			top t = new top(i, Integer.parseInt(st.nextToken()));
			while (true) {
				if (stack.isEmpty()) { // 스택이 비어있다면
					sb.append(0).append(' '); // 0 출력
					stack.add(t);
					break;
				} else { // 스택이 비어있지 않다면
					if (stack.peek().height < t.height) { // 자기 높이보다 작다면
						stack.pop(); // 제거
					} else {
						sb.append(stack.peek().index).append(' '); // 인덱스 출력
						stack.add(t);
						break;
					}
				}
			}
		}
		
		System.out.println(sb.toString());

	}
}
