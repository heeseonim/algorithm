import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution_42627_2 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } }));
	}

	public static class job implements Comparable<job> {
		int start;
		int time;

		public job(int start, int time) {
			this.start = start;
			this.time = time;
		}

		@Override
		public int compareTo(job o) {
			if (this.time == o.time) // 소요시간을 기준으로 정렬
				return this.start - o.start;
			return this.time - o.time;
		}
	}

	public static int solution(int[][] jobs) {
		List<job> jobList = new LinkedList<>(); // 순회하면서 확인하기 위해 List 사용
		for (int i = 0; i < jobs.length; i++) {
			jobList.add(new job(jobs[i][0], jobs[i][1]));
		}
		Collections.sort(jobList); // 비교기준을 적용하여 리스트 정렬

		int curTime = 0; // 현재 시간을 나타내는 변수
		int sum = 0; // 총 작업시간의 합을 누적할 변수
		while (jobList.size() > 0) {
			for (int i = 0; i < jobList.size(); i++) {
				if (jobList.get(i).start <= curTime) { // 대기 중이거나 현재 시간에 요청한 작업이면
					curTime += jobList.get(i).time;
					sum += (curTime - jobList.get(i).start); // 대기시간 누적
					jobList.remove(i); // 해당 작업은 처리했으므로 삭제
					break;
				}
				if (i == jobList.size()-1) // 모든 작업 조회했는데 조건에 맞는 시간이 없다면
					curTime++; // 시간 증가
			}
		}

		return sum/jobs.length;
	}
}
