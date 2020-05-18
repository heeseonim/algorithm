
public class 징검다리건너기 {
	public static void main(String[] args) {
		징검다리건너기 문 = new 징검다리건너기();
		System.out.println(문.solution(new int[] { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 }, 3));
	}

	public int solution(int[] stones, int k) {

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < stones.length; i++) {
			max = max < stones[i] ? stones[i] : max;
			min = min > stones[i] ? stones[i] : min;
		}

		int answer = Integer.MIN_VALUE;
		int mid;
		while (min <= max) {
			mid = (min + max) / 2;

			if (isPossible(mid, stones.clone(), k)) {
				answer = answer < mid ? mid : answer;
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}

		return answer;
	}

	public boolean isPossible(int mid, int[] stones, int k) {
		int cnt = 0;
		for (int i = 0; i < stones.length; i++) {
			stones[i] -= mid;

			if (stones[i] < 0) {
				cnt++;
				if (cnt >= k) { // 음수가 연속으로 K 이상이면
					return false;
				}
			} else
				cnt = 0; // cnt 초기화
		}

		return true;
	}
}
