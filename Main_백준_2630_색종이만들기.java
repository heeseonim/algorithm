import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2630_색종이만들기 {
	public static int[][] map;
	public static int blue, white;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		white = 0;
		blue = 0;
		find(N, 0, 0); // 한 변의 길이, (0, 0) 좌표부터 시작
		System.out.println(white);
		System.out.println(blue);
	} // end of main

	// 정사각형 찾기
	public static void find(int N, int start, int end) {
		int num = map[start][end]; // 탐색할 좌표의 값 저장 (0 or 1)
		for (int i = start; i < start + N; i++) {
			for (int j = end; j < end + N; j++) {
				if (num != map[i][j]) { // 다르다면 범위 줄여서 탐색
					// 정사각형이므로 N/2로 크기 줄여서 다시 탐색
					// 남는 길이 오른쪽, 아래 탐색
					find(N / 2, start, end);
					find(N / 2, start + N / 2, end);
					find(N / 2, start, end + N / 2);
					find(N / 2, start + N / 2, end + N / 2);
					return; // 다르면 개수 세주지 않음
				}
			}
		}

		// 이중 for문이 종료되면 색깔에 따라 개수를 세줌
		if (num == 0) {
			white++;
		} else {
			blue++;
		}
	}
} // end of class
