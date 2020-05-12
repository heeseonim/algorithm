package _PRACTICE;

import java.util.*;

public class 스택_64061_크레인인형뽑기게임 {
	public int solution(int[][] board, int[] moves) {
		int answer = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < moves.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][moves[i] - 1] != 0) { // 아래로 내려가다 인형 발견
					if (stack.isEmpty()) { // 바구니 비어있는 경우 - 인형 넣기
						stack.push(board[j][moves[i] - 1]);
					} else { // 바구니 비어있지 않은 경우 - 인형 비교
						if (stack.peek() == board[j][moves[i] - 1]) { // 동일
							stack.pop();
							answer += 2;
						} else { // 동일하지 않음
							stack.push(board[j][moves[i] - 1]);
						}
					}

					board[j][moves[i] - 1] = 0; // 빼낸 자리 0 으로 표시
					break;
				}
			}
		}
		return answer;
	}
}
