
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판돌리기 {
	public static int N, M, T, total;
	public static int[] front;
	public static int[][] circle;
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 원판의 개수
		M = Integer.parseInt(st.nextToken()); // 적혀있는 수의 개수
		T = Integer.parseInt(st.nextToken()); // 명령의 수

		front = new int[N]; // front 위치를 저장할 배열
		circle = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				circle[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			rotate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		total = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				total += circle[i][j];

		System.out.println(total);
	}

	// command[i][0] 배수의 원판을 [i][1] 방향으로 [i][2]칸 회전
	public static void rotate(int num, int dir, int count) {
		visited = new boolean[N][M];

		int newCount = count;

		if (dir == 0)
			newCount = -count;

		// 회전
		for (int i = 1; num * i <= N; i++) {
			int f = front[num * i - 1];
			f += newCount;

			if (f < 0)
				f += M;
			else if (f >= M)
				f -= M;

			front[num * i - 1] = f;
		}

		// 왼쪽 오른쪽 같은 값 체크
		for (int i = 0; i < N; i++) {
			int f = front[i]; // i 원판의 front 위치
			for (int j = 0; j < M; j++) {
				if (f < 0)
					f += M;
				if (f >= M)
					f -= M;

				int cur = circle[i][f];

				int left = f - 1;
				if (left < 0)
					left += M;

				int right = f + 1;
				if (right >= M)
					right -= M;

				if (cur != 0 && (circle[i][left] == cur))
					visited[i][left] = true;
				if (cur != 0 && (circle[i][right] == cur))
					visited[i][right] = true;

				f++; // 오른쪽으로 이동
			}
		}

		if (M != 1) {
			// 아래 같은 값 체크
			for (int i = 0; i < N - 1; i++) {
				int f = front[i]; // i 원판의 front 위치
				int df = front[i + 1]; // 아래 원판의 front 위치

				while (true) {
					// front 쭉 돌면서 체크
					if (circle[i][f] != 0 && (circle[i][f] == circle[i + 1][df])) {
						visited[i][f] = true;
						visited[i + 1][df] = true;
					}

					f++;
					df++;

					if (f < 0)
						f += M;
					if (f >= M)
						f -= M;
					if (df < 0)
						df += M;
					if (df >= M)
						df -= M;

					if (f == front[i])
						break;
				}
			}
		}

		// 지우기
		delete();
	}

	public static void delete() {
		boolean flag = false; // 한 턴의 flag를 체크할 변수

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (visited[i][j]) {
					circle[i][j] = 0;
					flag = true;
				}

		if (!flag) {
			double sum = 0;
			int count = 0;

			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (circle[i][j] > 0) {
						sum += circle[i][j];
						count++;
					}

			double avg = sum / count;

			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++) {
					if (circle[i][j] == 0)
						continue;

					if (circle[i][j] > avg)
						circle[i][j]--;
					else if (circle[i][j] < avg)
						circle[i][j]++;
				}
		}
	}
}
