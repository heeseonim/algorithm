package _PRACTICE;

import java.util.*;
import java.io.*;

public class 백트래킹_15684_사다리조작 {
	public static int N, M, H;
	public static boolean[][] visited;
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로선의 수
		M = Integer.parseInt(st.nextToken()); // 놓여있는 가로선의 수
		H = Integer.parseInt(st.nextToken()); // 가로선 놓을 수 있는 자리의 수
		visited = new boolean[H + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			visited[a][b] = true;
		}

		answer = 4;
		line(0, 1, 1);
		System.out.println(answer == 4 ? -1 : answer);
	}

	// 가로선을 놓는 함수
	public static void line(int cnt, int h, int n) {
		if (answer < cnt || cnt >= 4)
			return;

		if (game()) {
			answer = cnt < answer ? cnt : answer;
			return;
		}

		if (cnt == 3)
			return;

		for (int i = h; i <= H; i++) { // 가로선
			for (int j = n; j < N; j++) { // 세로선
				// 해당 위치에 가로선이 존재하거나, 오른쪽에 가로선 or 왼쪽에 가로선이 있으면 넘어가기
				if (visited[i][j] || visited[i][j + 1] || visited[i][j - 1])
					continue;

				visited[i][j] = true;
				line(cnt + 1, i, j + 2);
				visited[i][j] = false;
			}
			n = 1;
		}
	}

	// 사다리 게임
	public static boolean game() {
		for (int i = 1; i <= N; i++) { // 세로선
			int cur = i;
			for (int j = 1; j <= H; j++) { // 가로선
				// 현재의 값(cur)으로 배열을 조회해야 함!
				if (visited[j][cur]) {// 오른쪽으로 가로선이 연결되어 있으면 우측으로 이동
					cur += 1;
				} else if (cur > 0 && visited[j][cur - 1]) {// 왼쪽으로 가로선이 연결되어 있으면 좌측으로 이동
					cur -= 1;
				}
			}
			if (cur != i) // 가로선을 다 검사한 뒤, 원래 세로선의 값과 같지 않다면
				return false;
		}
		return true;
	}
}
