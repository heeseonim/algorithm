import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_6782_현주가좋아하는제곱근놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());
			int cnt = 0;
			while (N != 2) {
				long num = (long) Math.sqrt(N);
				if (num * num == N) { // 루트값이 정수인지 비교
					N = num; // 루트값 저장
				} else { // 정수가 아닐 때 하나 더 증가한 제곱근의 값으로 계산
					cnt += (num + 1) * (num + 1) - N; // 건너뛰었으므로 카운트 계산해줌
					N = num + 1; // 루트값 저장
				}
				cnt++; // 루트를 씌우는 작업
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
