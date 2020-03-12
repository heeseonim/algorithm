import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17211 {
	public static double[][] per;
	public static double GtoG, GtoB, BtoG, BtoB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 기분을 구할 날짜
		int feel = Integer.parseInt(st.nextToken()); // 현재 재현이의 기분

		st = new StringTokenizer(br.readLine());
		GtoG = Double.parseDouble(st.nextToken());
		GtoB = Double.parseDouble(st.nextToken());
		BtoG = Double.parseDouble(st.nextToken());
		BtoB = Double.parseDouble(st.nextToken());

		per = new double[2][N];
		if (feel == 0) {
			per[0][0] = GtoG;
			per[1][0] = GtoB;
		} else {
			per[0][0] = BtoG;
			per[1][0] = BtoB;
		}

		check(N);
		System.out.println(Math.round(per[0][N-1] * 1000));
		System.out.println(Math.round(per[1][N-1] * 1000));
	}

	public static void check(int n) {
		int index = 1;
		while (index != n) {
			per[0][index] = per[0][index - 1] * GtoG + per[1][index - 1] * BtoG;
			per[1][index] = per[0][index - 1] * GtoB + per[1][index - 1] * BtoB;
			index++;
		}
	}
}
