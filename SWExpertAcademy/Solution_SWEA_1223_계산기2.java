import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution_SWEA_1223_계산기2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
		int len = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String[] srr = str.split("");
		char[] result = new char[srr.length]; // 후위표기식이 저장될 배열
		char[] stackOp = new char[srr.length]; // 연산자 저장 스택
		int[] resultNum = new int[result.length]; //후위표기식의 숫자들이 저장될 배열, 결과값이 저장될 배열
		int optop = -1, retop = -1, ntop = -1;

		// 중위표기식 -> 후위표기식
		for (int i = 0; i < srr.length; i++) {
			char c = srr[i].charAt(0);
			switch (c) {
			case '(':
				stackOp[++optop] = c;
				break;
			case '+':
				while (optop >= 0 && stackOp[optop] != '(') {
					result[++retop] = stackOp[optop--];
				}
				stackOp[++optop] = c;
				break;
			case '*':
				while(optop>=0 && (stackOp[optop] == '*' || stackOp[optop] == '/')) {
					result[++retop] = stackOp[optop--];
				}
				stackOp[++optop] = c;
				break;
			case ')' :
				while (optop>=0 && stackOp[optop] != '(') {
					result[++retop] = stackOp[optop--];
				}
				if(stackOp[optop] == '(') {
					optop--;
				}
				break;
			default:
				result[++retop] = c;
				break;
			}
		}
		while(optop > -1) {
			result[++retop] = stackOp[optop--];
		}
		
		for (int i = 0; i <= retop; i++) {
			char c = result[i];
			int num1, num2;
			switch (c) {
			case '+':
				num2 = resultNum[ntop--];
				num1 = resultNum[ntop--];
				resultNum[++ntop] = num1 + num2;
				break;
			case '*':
				num2 = resultNum[ntop--];
				num1 = resultNum[ntop--];
				resultNum[++ntop] = num1 * num2;
				break;
			default:
				resultNum[++ntop] = c - '0';
				break;
			}
		}
		
		System.out.println("#" + tc + " " +resultNum[ntop]);
		}
	}

}
