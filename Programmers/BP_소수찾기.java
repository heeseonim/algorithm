import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author heeseonim
 *
 *         1. 한자리 숫자가 적힌 종이 조각 2. 종이 조각을 붙여 소수를 몇개 만들 수 있는가
 * 
 *         1. 순열 2. 소수인지 확인 3. 중복된 수 들어가지 않도록 Set 활용
 */
public class BP_소수찾기 {
	public static Set<Integer> set;
	public static int answer;

	public static void main(String[] args) {
		System.out.println(solution("222"));
	}

	public static int solution(String numbers) {
		set = new HashSet<>();
		answer = 0;

		int n = numbers.length();
		char[] arr = numbers.toCharArray();

		// 하나씩 소수 검사
		for (int i = 0; i < n; i++) {
			int one = arr[i] - '0';
			if (!set.contains(one)) // 중복 방지
				check(one);
		}

		for (int i = 2; i <= n; i++) {
			char[] output = new char[i];
			boolean[] visited = new boolean[n];
			perm(arr, output, visited, 0, n, i);
		}

		return answer;
	}

	// 순열
	public static void perm(char[] arr, char[] output, boolean[] visited, int depth, int n, int r) {
		if (depth == r) {
			// Set 에 존재하는지 검사 후 소수 검사 함수 호출
			StringBuilder sb = new StringBuilder();
			for (char o : output) {
				sb.append(o);
			}
			int result = Integer.valueOf(sb.toString()); // 완성된 숫자 조합
			if (set.contains(result)) // 이미 소수 검사가 완료된 수라면 리턴
				return;
			check(result); // 소수 검사 호출

			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;

				output[depth] = arr[i];
				perm(arr, output, visited, depth + 1, n, r);

				visited[i] = false;
			}
		}
	}

	// 소수인지 확인
	public static void check(int num) {
		// 0 이나 1 일 때, 소수가 아님
		if (num <= 1)
			return;

		// 2 이상의 수로 나눠질 때, 소수가 아님
		for (int i = 2; i <= Math.sqrt(num); i++) { // 제곱근까지만 체크
			if (num % i == 0) {
				return;
			}
		}

		set.add(num); // set 에 추가
		answer++; // 갯수 증가
		System.out.println(num);
		return;
	}
}
