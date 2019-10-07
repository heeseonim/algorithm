import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_4672_수진이의팰린드롬문자열 {
	public static char[] arr;
	public static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			arr = str.toCharArray();
			Arrays.sort(arr);
			bit(arr);
			System.out.println("#" + tc + " " + cnt);
		}
	}

	// 연속된 부분문자열 구하기
	public static void bit(char[] array) {
		cnt = 0; // 팰린드롬 개수
		int select = 1;
		while (select <= array.length) {
			for (int i = 0; i < array.length; i++) {
				String str = "";
				str += array[i];
				if (i + select > array.length) {
					break;
				}
				for (int j = i + 1; j < i + select; j++) {
					str += array[j];
				}
				if (checkP(str)) {
					cnt++;
				}
			}
			select++;
		}
	}

	// 팰린드롬인지 확인하기
	public static boolean checkP(String str) {
		if (str.length() == 1) {
			return true;
		}

		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}
}
