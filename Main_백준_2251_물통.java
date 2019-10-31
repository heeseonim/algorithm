import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_2251_물통 {
	public static class water {
		int a;
		int b;
		int c;

		public water(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	public static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		visited = new boolean[201][201][201];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		Queue<Integer> result = moveWater(a, b, c);
		int[] answer = new int[result.size()];
		int i = 0;
		while(!result.isEmpty()) {
			answer[i] = result.poll();
			i++;
		}
		Arrays.sort(answer);
		for(int ans : answer) {
			System.out.print(ans + " ");
		}
	}

	public static Queue<Integer> moveWater(int a, int b, int c) { // 물통의 크기 전달
		Queue<water> queue = new LinkedList<water>();
		Queue<Integer> result = new LinkedList<>(); // 결과값을 저장할 큐
		queue.add(new water(0, 0, c));

		while (!queue.isEmpty()) {
			water wt = queue.poll();
			int A = wt.a;
			int B = wt.b;
			int C = wt.c;
			if (visited[A][B][C])
				continue;
			visited[A][B][C] = true;
			if (A == 0) {
				result.add(C);
			}
			// A -> B
			if (A + B > b) {
				queue.add(new water(A + B - b, b, C));
			} else {
				queue.add(new water(0, A + B, C));
			}
			// A -> C
			if (A + C > c) {
				queue.add(new water(A + C - c, B, c));
			} else {
				queue.add(new water(0, B, A + C));
			}
			// B -> A
			if (B + A > a) {
				queue.add(new water(a, B + A - a, C));
			} else {
				queue.add(new water(B + A, 0, C));
			}
			// B -> C
			if (B + C > c) {
				queue.add(new water(A, B + C - c, c));
			} else {
				queue.add(new water(A, 0, B + C));
			}
			// C -> A
			if (C + A > a) {
				queue.add(new water(a, B, C + A - a));
			} else {
				queue.add(new water(C + A, B, 0));
			}
			// C -> B
			if (C + B > b) {
				queue.add(new water(A, b, C + B - b));
			} else {
				queue.add(new water(A, C + B, 0));
			}
		}
		return result;
	}
}
