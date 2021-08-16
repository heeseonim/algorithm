import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BruteForce_BOJ_1107_리모컨 {
	public static boolean[] button;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		button = new boolean[10];

		if (M != 0) { // 고장난 번호가 있을 때
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				button[Integer.parseInt(st.nextToken())] = true; // 고장 표시
			}
		}

		if (N == 100) { // 번호 동일할 경우 0 출력
			System.out.println(0);
			return;
		}

		int min = Math.abs(100 - N); // +,- 만 누르는 횟수
		for (int i = 0; i <= 1000000; i++) { // brute force
			int cnt = getCnt(i);
			if (cnt > 0) {
				int answer = Math.abs(i - N) + cnt; // +,- 누르는 횟수 + i 번호 누르는 횟수
				min = Math.min(min, answer);
			}
		}

		System.out.println(min);
	}

	public static int getCnt(int num) {
		int cnt = 0;

		if (num == 0 && !button[0]) // num 이 0이고, 해당 버튼이 고장나지 않았다면
			return 1;

		while (num > 0) {
			if (button[num % 10]) // 나머지 한자리수, 해당 번호가 고장이라면 0 반환
				return 0;

			// 번호가 고장나지 않은 경우
			num /= 10; // 한자리 앞으로 이동, 몫만 남기기
			cnt++; // 누르는 횟수 더해주기
		}

		return cnt;
	}
}
