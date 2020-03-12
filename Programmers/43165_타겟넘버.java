class Solution_프로그래머스_43165_타겟넘버 {
    private int count;
    
    public int solution(int[] numbers, int target) {
        count = 0;
		
		// 초기값부터 두가지 경우를 주고 탐색
		dfs(numbers, numbers[0], 1, target);
		dfs(numbers, numbers[0] * -1, 1, target);

		return count;
    }
    
    private void dfs(int[] numbers, int sum, int index, int target) {
		if (index == numbers.length) { // 끝까지 도달했을 때
			if (sum == target) { // 합이 타겟과 같다면
				count++; // 카운트 증가
			}
			return;
		}
		dfs(numbers, sum+numbers[index], index+1, target);
		dfs(numbers, sum-numbers[index], index+1, target);
		
	}
}
