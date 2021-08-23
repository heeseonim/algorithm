import java.io.*;
import java.util.StringTokenizer;

public class FloydWarshall_2458_키순서 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] check = new boolean[N][N]; // 연결관계 체크

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			check[a][b] = true; // a < b, 화살표가 존재하므로, [b][a] 는 넣어주지 않음
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < N; j++) {
					if (k == j || i == j)
						continue;
					if (check[i][k] && check[k][j]) // k를 기점으로 연결되어 있다면
						check[i][j] = true; // i < j
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				if (!check[i][j] && !check[j][i]) {// i보다 크거나, i보다 작은 학생이 아무도 없다면
					flag = false;
					if (!flag)
						break;
				}
			}
			if (flag) // 모두 다 연결되어 있다면 정답에 카운트
				answer++;
		}

		System.out.println(answer);
	}
}
