import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class M1_07_블랙리스트 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		HashSet<Integer> black = new HashSet<>();

		// apart
		st = new StringTokenizer(br.readLine(), " ");
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int apartSum = height * width;
		int[][] apart = new int[height][width];
		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < width; j++) {
				apart[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// black
		st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < width; j++) {
				int cur = Integer.parseInt(st.nextToken());
				black.add(cur);
			}
		}
		
		int blackCnt = 0;
		for (int i = 0; i < apart.length; i++) {
			for (int j = 0; j < apart[i].length; j++) {
				if (black.contains(apart[i][j])) {
					blackCnt++;
				}
			}
		}
		
		sb.append(blackCnt).append("\n").append(apartSum-blackCnt);
		System.out.println(sb);
	}
}
