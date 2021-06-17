import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class M1_추가8_아파트인테리어패턴 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M;
	static int[] target;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		target = new int[M];
		arr = new int[M];
		int cnt = 1;

		if (N == 1) {
			System.out.println(cnt);
			return;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				if (i == 0) {
					target[j] = Integer.parseInt(st.nextToken());
				} else {
					arr[j] = Integer.parseInt(st.nextToken());
				}
				if (isSame()) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	public static boolean isSame() {
		for (int i = 0; i < M; i++) {
			if (arr[i] != target[i])
				return false;
		}

		return true;
	};
}
