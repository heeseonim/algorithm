import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_4366_정식이의은행업무 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			char[] two = br.readLine().toCharArray();
			char[] three = br.readLine().toCharArray();
			long answer = 0L;

			loop: for (int i = 0; i < two.length; i++) {
				if (two[i] == '1') {
					two[i] = '0';
				} else {
					two[i] = '1';
				}
				for (int j = 0; j < three.length; j++) {
					if (three[j] == '0') {
						three[j] = '1';
						// 같은지 검사
						if (calcTwo(two) == calcThree(three)) {
							answer = calcTwo(two);
							break loop;
						}
						three[j] = '2';
						if (calcTwo(two) == calcThree(three)) {
							answer = calcTwo(two);
							break loop;
						}
						three[j] = '0'; // 다시 되돌려주기
					} else if (three[j] == '1') {
						three[j] = '0';
						if (calcTwo(two) == calcThree(three)) {
							answer = calcTwo(two);
							break loop;
						}
						three[j] = '2';
						if (calcTwo(two) == calcThree(three)) {
							answer = calcTwo(two);
							break loop;
						}
						three[j] = '1';
					} else if (three[j] == '2') {
						three[j] = '0';
						if (calcTwo(two) == calcThree(three)) {
							answer = calcTwo(two);
							break loop;
						}
						three[j] = '1';
						if (calcTwo(two) == calcThree(three)) {
							answer = calcTwo(two);
							break loop;
						}
						three[j] = '2';
					}
				} // end of three
				if (two[i] == '1') {
					two[i] = '0';
				} else {
					two[i] = '1';
				}
			}

			System.out.println("#" + tc + " " + answer);

		} // end of tc
	} // end of main

	public static long calcTwo(char[] arr) {
		long result = 0;
		for (int i = 0; i < arr.length; i++) {
			result += (arr[i] - '0') * Math.pow(2, arr.length - 1 - i);
		}
		return result;
	}

	public static long calcThree(char[] arr) {
		long result = 0;
		for (int i = 0; i < arr.length; i++) {
			result += (arr[i] - '0') * Math.pow(3, arr.length - 1 - i);
		}
		return result;
	}

} // end of class
