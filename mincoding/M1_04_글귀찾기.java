import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class M1_04_글귀찾기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int H, W, n;
	static char[][] arr;
	static String target;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		arr = new char[H][W];
		for (int i = 0; i < H; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		n = Integer.parseInt(br.readLine());
		target = br.readLine();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (isSame(i,j)) {
					sb.append('(').append(i).append(',').append(j).append(')').append("\n");
				}
			}
		}
		
		System.out.println(sb);

	}

	// 시작좌표, 검사할 길이
	public static boolean isSame(int r, int c) {
		boolean flag = true;
		int cnt = 0;

		for (int i = r; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (i == r && j < c)
					continue;
				if (arr[i][j] != target.charAt(cnt)) {
					return false;
				}
				cnt++;
				if (cnt == n) {
					return flag;
				}
			}
		}

		if (cnt != n)
			flag = false;
		
		return flag;
	}
}
