import java.util.Arrays;

public class Solution_42842 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(10, 2)));
	}

	public static int[] solution(int brown, int red) {
		int[] answer = new int[2];
		int sum = brown + red;
		for (int i = 3; i < sum; i++) {
			if (sum % i == 0) { // i가 sum의 약수이면
				int j = sum / i;
				if ((i - 2) * (j - 2) == red) { // 모서리를 제외한 부분 확인
					answer[0] = j;
					answer[1] = i;
					break;
				}
			}
		}

		return answer;
	}
}
