import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_42587 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 2, 1, 3, 2 }, 2));
	}

	public static int solution(int[] priorities, int location) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < priorities.length; i++) {
			list.add(priorities[i]);
		}

		Collections.sort(list); // 정렬
		Collections.reverse(list); // 정렬된 결과를 뒤집음
		int max = list.get(0);

		int answer = 0;
		int index = 0;
		boolean flag = false;

		while (true) {
			if (max == priorities[index]) {
				answer++; // 인쇄 순서
				priorities[index] = -1;
				flag = true; // max 값 변경되어야 함
			}

			// 입력받은 위치가 -1 이면 종료 후 리턴
			if (priorities[location] == -1)
				return answer;

			// 인덱스 이동
			if (index == priorities.length - 1)
				index = 0;
			else
				index++;

			// max 값 변경
			if (flag) {
				list.remove(0);
				max = list.get(0);
				flag = false;
			}
		}
	}
}
