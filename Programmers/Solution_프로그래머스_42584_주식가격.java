import java.util.Arrays;

public class Solution_42584 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 1, 2, 3, 2, 3 })));
	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		int index = 0;
		while (index < prices.length) {
			int cur = prices[index];
			for (int i = index + 1; i < prices.length; i++) {
				if (prices[i] < cur) {
					answer[index] = i - index;
					break;
				} else {
					answer[index]++;
				}
			}
			index++;
		}

		return answer;
	}
}
