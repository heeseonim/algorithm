import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_프로그래머스_42889_실패율 {
	public static void main(String[] args) {
		int N = 4;
		int[] stages = { 4, 4, 4, 4, 4 };
		System.out.println(Arrays.toString(solution(N, stages)));
	}

	public static int[] solution(int N, int[] stages) {
		List<Stage> list = new ArrayList<>(); // 따로 생성한 클래스 형식의 리스트 선언
		
		for (int i = 1; i <= N; i++) {
			double sum = 0.0;
			double fail = 0.0;
			for (int j = 0; j < stages.length; j++) {
				if (stages[j] >= i) {
					sum += 1; // 스테이지에 머물고 있거나 클리어 한 사람의 수
				}
				if (stages[j] == i) {
					fail += 1; // 스테이지에 머물고 있는 사람의 수
				}
			}
			
			if(sum == 0.0) { // 나눠지는 수가 0일 때 처리 필수 !
				list.add(new Stage(i, 0));
				continue;
			}
			
			list.add(new Stage(i, fail/sum)); // 스테이지번호와 실패율 전달
		}
		
		int[] answer = new int[N];
		Collections.sort(list); // 실패율에 따라 정렬
		
		for (int i = 0; i < N; i++) {
			answer[i] = list.get(i).stage; // 스테이지 번호 저장
		}	
		
		return answer;
	}
	
	public static class Stage implements Comparable<Stage> { // 스테이지 클래스
		int stage; // 스테이지 번호
		double value; // 스테이지 실패율
		
		public Stage(int stage, double value) {
			this.stage = stage;
			this.value = value;
		}

		@Override
		public int compareTo(Stage o) {
			if (this.value > o.value) { // 실패율 내림차순 정렬
				return -1;
			} else if (this.value == o.value) { // 실패율이 같을 경우
				return this.stage - o.stage; // 스테이지 번호 오름차순 정렬
			} else {
				return 1;
			}
		}
		
	}
}
