import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_42840 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 1, 3, 2, 4, 2 })));
	}

	public static int[] solution(int[] answers) {
		int[][] supoja = new int[][] { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 },
				{ 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };

		int[] score = new int[3];

		for (int i = 0; i < answers.length; i++) {			
			if (answers[i] == supoja[0][i%5])
				score[0]++;
			if (answers[i] == supoja[1][i%8])
				score[1]++;
			if (answers[i] == supoja[2][i%10])
				score[2]++;
		}

		int max = score[0];
		for (int i = 0; i < score.length; i++) {
			if (max < score[i])
				max = score[i];
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < score.length; i++) {
			if (max == score[i])
				list.add(i + 1);
		}

		int[] answer = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}
}
