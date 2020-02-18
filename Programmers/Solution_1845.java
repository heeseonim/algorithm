import java.util.HashSet;
import java.util.Set;

public class Solution_1845 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 3, 3, 2, 2, 4}));
	}

	public static int solution(int[] nums) {
		int length = nums.length;
		int max = length/2;
		
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}
		
		int size = set.size();
		if (size > max) // 최대 종류보다 큰 경우 (더 많은 종류가 있음)
			return max; // 최대 종류를 반환
		else
			return size; // 현재의 최대 종류 반환
	}
}