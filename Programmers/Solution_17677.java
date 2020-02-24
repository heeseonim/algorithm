import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_17677 {
	public static void main(String[] args) {
		System.out.println(solution("E=M*C^2", "e=m*c^2"));
	}

	public static int solution(String str1, String str2) {
		String s1 = str1.toUpperCase();
		String s2 = str2.toUpperCase();

		List<String> arr1 = new ArrayList<String>();
		List<String> arr2 = new ArrayList<String>();

		for (int i = 0; i < s1.length() - 1; i++) {
			String s = s1.substring(i, i + 2);
			char a = s.charAt(0);
			char b = s.charAt(1);
			if (a < 65 || a > 90 || b < 65 || b > 90) {
				continue;
			}
			arr1.add(s);
		}

		for (int i = 0; i < s2.length() - 1; i++) {
			String s = s2.substring(i, i + 2);
			char a = s.charAt(0);
			char b = s.charAt(1);
			if (a < 65 || a > 90 || b < 65 || b > 90) {
				continue;
			}
			arr2.add(s);
		}

		Collections.sort(arr1);
		Collections.sort(arr2);

		double intersection = 0;
		double union = arr1.size() + arr2.size();

		for (int i = 0; i < arr2.size(); i++) {
			if (arr1.indexOf(arr2.get(i)) >= 0) {
				arr1.remove(arr1.indexOf(arr2.get(i))); // 확인한 것은 지워주기
				intersection++; // 교집합의 수 증가
				union--; // 전체에서 교집합 제외
			}
		}

		if (intersection == 0 && union == 0)
			return 65536;

		return (int) Math.floor((intersection / union) * 65536);
	}
}
