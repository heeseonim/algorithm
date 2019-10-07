import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1240_SW문제해결응용1일차_단순2진암호코드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			String pass = "";
			//암호 0~9 저장
			String[] number = {"0001101", "0011001", "0010011", "0111101", "0100011",
					"0110001", "0101111", "0111011", "0110111", "0001011"};

			for (int i = 0; i < N; i++) { // N 개의 줄 입력받기
				String str = br.readLine();
				for (int j = M - 1; j >= 0; j--) { // 뒤에서부터 검색
					if (str.charAt(j) == '1') { // 1을 발견 시
						pass = str.substring(j - 55, j + 1); // 56개 문자만 추출
						break;
					}
				}
			}

			// 암호문으로부터 구해지는 숫자는 8개
			int[] result = new int[8];
			int index = 0;
			// 추출된 암호문 해석하기
			for (int i = 0; i < pass.length(); i+=7) {
				String temp = pass.substring(i, i+7);
				for (int j = 0; j < number.length; j++) {
					if (temp.equals(number[j])) {
						result[index++] = j;
					}
				}
			}
			
			System.out.println("#" + tc + " " + cal(result));
			

		}
	} // end of main
	
	public static int cal(int[] arr) {
		// 홀수 자리의 합, 짝수 자리의 합
		int oSum = 0, eSum = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i+=2) {
			oSum += arr[i];
			eSum += arr[i+1];
		}
		
		sum = oSum * 3 + eSum;
		if (sum%10 == 0) {
			return oSum + eSum;
		} else {
			return 0;
		}
		
	}

} // end of class
