import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_프로그래머스_42888_오픈채팅방 {
	public static void main(String[] args) {
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		System.out.println(Arrays.toString(solution(record)));
	}

	public static String[] solution(String[] record) {
		List<String[]> commandList = new ArrayList<>();
		Map<String, String> userInfo = new HashMap<>();
		
		for (int i = 0; i < record.length; i++) {
			String[] com = record[i].split(" ");
			if (com[0].equals("Enter")) {
				userInfo.put(com[1], com[2]);
				commandList.add(com);
			} else if (com[0].equals("Leave")) {
				commandList.add(com);
			} else {
				userInfo.put(com[1], com[2]);
			}
		}
		
		String[] answer = new String[commandList.size()];
		int index=0;
		
		for(String[] c : commandList) {
			if (c[0].equals("Enter")) {
				answer[index++] = userInfo.get(c[1]) + "님이 들어왔습니다.";
			} else {
				answer[index++] = userInfo.get(c[1]) + "님이 나갔습니다.";
			}
		}
		
		return answer;
	}
}
