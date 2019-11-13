package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정올_2577_회전초밥_임희선 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		int[] visited = new int[d + 1];
		visited[c]++;
		int answer = 1;

		for (int i = 0; i < k; i++) {
			if (visited[sushi[i]] <= 0) { // 처음 고른 초밥이면
				answer++; // 가짓수++
			}
			visited[sushi[i]]++;
		}
		int max = answer;

		for (int i = 0; i < N; i++) {
			visited[sushi[i]]--;
			if (visited[sushi[i]] <= 0) // 같은 종류의 초밥이 더이상 없다면
				answer--;

			int num = i + k;
			if (num >= N)
				num -= N;

			if (visited[sushi[num]] <= 0) { // 처음 고른 초밥이면
				answer++; // 가짓수++
			}
			visited[sushi[num]]++;

			if (max < answer)
				max = answer;
		}

		System.out.println(max);
	}
}
