package _PRACTICE;

import java.util.*;

public class 종이접기 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(3)));
	}

	public static int[] solution(int n) {
		Vector<Integer> vector = new Vector<>();
		vector.add(0); // 초기상태
		
		if (n >= 2) {
			for (int i = 2; i <= n; i++) {
				Vector<Integer> temp = (Vector)vector.clone(); // 이전 상태 저장
				vector.add(0);
				for (int j = temp.size() - 1; j >= 0; j--) {// 이전 상태의 뒤부터 검사
					if (temp.get(j) == 0)
						vector.add(1);
					else
						vector.add(0);
				}
			}
		}

		int[] answer = new int[vector.size()];
		int index = 0;
		for (Integer v : vector) {
			answer[index++] = v;
		}
		return answer;
	}
}
