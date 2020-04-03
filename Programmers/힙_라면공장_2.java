import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution_42629 {
	public static void main(String[] args) {
		System.out.println(solution(4, new int[] { 4, 10, 15 }, new int[] { 20, 5, 10 }, 30));
	}
	
	public static class flour implements Comparable<flour>{
		int day;
		int sup;
		
		public flour(int day, int sup) {
			this.day = day;
			this.sup = sup;
		}

		@Override
		public int compareTo(flour o) {
			if (this.sup == o.sup)
				return this.day - o.day;
			return o.sup - this.sup; // 공급량 내림차순
		}		
	}

	public static int solution(int stock, int[] dates, int[] supplies, int k) {
		List<flour> flourList = new LinkedList<>();
		for (int i = 0; i < dates.length; i++) {
			flourList.add(new flour(dates[i], supplies[i]));
		}
		Collections.sort(flourList);
		
		// 밀가루 양 = 날짜
		int curFlour = stock; // 초기 재고
		int answer = 0; // 공급받은 횟수

		while(curFlour < k) { // 밀가루 재고가 버틸 수 있는 날만큼 채워졌거나 초과하면 탈출
			for (int i = 0; i < flourList.size(); i++) {
				if(flourList.get(i).day <= curFlour) { // 버틸 수 있는 밀가루 양보다 공급날이 먼저거나 같으면 밀가루 공급받기
					curFlour += flourList.get(i).sup;
					flourList.remove(i);
					answer++;
					break;
				}
			}
		}
		
		return answer;
	}
}
