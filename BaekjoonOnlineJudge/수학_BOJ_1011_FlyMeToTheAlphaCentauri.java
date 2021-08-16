import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * distance move         count max
 * 4        1 2 1        3     2
 * 5        1 2 1 1      4     2
 * 7        1 2 2 1 1    5     2
 * 9        1 2 3 2 1    5     3
 */
public class 수학_BOJ_1011_FlyMeToTheAlphaCentauri {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = end - start;
			
			int max = (int)Math.sqrt(distance);
			
			if (max == Math.sqrt(distance)) // distance가 max의 제곱인 경우
				System.out.println(max * 2 - 1);
			else if (distance <= max * max + max) // max 제곱값 + max 범위 사이에 있는 경우
				System.out.println(max * 2);
			else // max 제곱값 + max ~ 다음 제곱값 사이
				System.out.println(max * 2 + 1);
		}
	}
}
