import java.util.HashMap;

public class 해시_완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };
		System.out.println(solution(participant, completion));
	}

	public static String solution(String[] participant, String[] completion) {
		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < participant.length; i++) { // 참여한 선수의 수만큼 맵에 저장
			hm.put(participant[i], hm.getOrDefault(participant[i], 0) + 1);
		}
		for (int i = 0; i < completion.length; i++) { // 완주한 선수의 수만큼 빼줌
			hm.put(completion[i], hm.get(completion[i]) - 1);
		}
		
		String answer = "";
		
		for (int i = 0; i < participant.length; i++) { // 참여한 선수를 돌면서
			if(hm.get(participant[i]) > 0) { // 0보다 큰 값이 있다면
				answer = participant[i]; // 그 선수는 완주하지 못한 선수
			}
		}
		
		return answer;
	}
}
