import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class M1_05_할아버지의유산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int height, width, maxValue;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		arr = new int[height][width];

		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				for (int k = 0; k < arr.length; k++) {
					for (int l = 0; l < arr[i].length; l++) {
						score(i, j, k, l);
					}
				}
			}
		}

		System.out.println(maxValue);
	}

	// 두 개의 좌표로 직사각형 안의 점수 계산 : 점수를 게산하면서 0 있는지 검사
	public static void score(int r1, int c1, int r2, int c2) {
		int sum = 0;

		for (int i = r1; i <= r2; i++) {
			for (int j = c1; j <= c2; j++) {
				if (arr[i][j] == 0)
					return;
				sum += arr[i][j];
			}
		}

		if (maxValue < sum)
			maxValue = sum;
	}
}
