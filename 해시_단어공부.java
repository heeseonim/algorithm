import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_1157 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Character, Integer> map = new HashMap<>();
		String str = br.readLine();
		str = str.toUpperCase();
		for (int i = 0; i < str.length(); i++) {
			map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
		}
		
		int max = Integer.MIN_VALUE;
		char answer = '?';
		
		// 최댓값 저장
		for(int m : map.values()) {
			if (max < m) {
				max = m;
			}
		}
		
		// 최댓값과 같은 수가 존재하는지 찾기
		int count = 0;
		for(int m : map.values()) {
			if (max == m) {
				if (++count > 1) {
					System.out.println(answer);
					return;
				}
			}
		}
		
		// 최댓값인 문자 저장하기
		for (int i = 0; i < str.length(); i++) {
			int m = map.get(str.charAt(i));
			if (max == m) {
				answer = str.charAt(i);
			}
		}
		
		System.out.println(answer);
	}
}
