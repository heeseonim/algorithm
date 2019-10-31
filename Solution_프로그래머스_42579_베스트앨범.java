import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_프로그래머스_42579_베스트앨범 {
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		System.out.println(Arrays.toString(solution(genres, plays)));
	}

	public static int[] solution(String[] genres, int[] plays) {
		Map<String, Integer> indexMap = new HashMap<>(); // 장르별 인덱스를 하나씩만 저장
		List<Genre> genreList = new ArrayList<>(); // 노래목록을 각자 가질 장르리스트 생성

		for (int i = 0; i < genres.length; i++) {
			Genre genre;
			Integer index = indexMap.get(genres[i]);

			if (index == null) { // 맵에 해당 장르가 존재하지 않을 때
				genre = new Genre();
				genreList.add(genre);

				indexMap.put(genres[i], genreList.size() - 1);
			} else {
				genre = genreList.get(index);
			}

			Song song = new Song(); // 현재 노래의 index와 재생 수 저장
			song.id = i;
			song.play = plays[i];

			genre.songs.add(song); // 저장된 노래를 장르의 목록에 저장
			genre.sum += plays[i]; // 해당 장르의 재생 수 누적
		}

		Collections.sort(genreList); // 저장된 장르들을 누적 플레이수의 내림차순으로 정렬

		int count = 0; // answer 배열 사이즈 변수

		for (Genre genre : genreList) { // 장르 리스트를 돌면서
			Collections.sort(genre.songs); // 해당 장르에 속한 노래들 재생 수에 따라 내림차순 정렬
			if (genre.songs.size() > 1) { // 해당 장르에 속한 노래가 여러개라면
				count += 2; // 두개의 공간만 할당
			} else { // 속한 노래가 하나라면
				count += 1; // 하나의 공간만 할당
			}
		}

		int[] answer = new int[count];
		int answerIdx = 0;

		for (Genre genre : genreList) { // 장르 리스트를 돌면서
			for (int i = 0; i < genre.songs.size() && i < 2; i++) { // 속한 노래의 개수만큼, 더 많다면 2개까지만 반복
				answer[answerIdx] = genre.songs.get(i).id; // 해당 장르에 속한 i번째 노래의 index 저장
				answerIdx++;
			}
		}

		return answer;
	}

	public static class Genre implements Comparable<Genre> { // 장르 클래스
		int sum; // 누적 플레이를 저장할 변수
		List<Song> songs; // 각각의 장르에 속한 노래 리스트

		public Genre() { // 기본 생성자
			sum = 0; // 처음에 0 할당
			songs = new ArrayList<>(); // 리스트 생성
		}

		@Override
		public int compareTo(Genre g) {
			return g.sum - this.sum; // 누적 플레이 내림차순 정렬
		}
	}

	public static class Song implements Comparable<Song> {
		int id; // 노래의 인덱스
		int play; // 노래의 재생 수

		@Override
		public int compareTo(Song o) {
			if (this.play == o.play) {
				return this.id - o.id; // 인덱스 오름차순 정렬
			}
			return o.play - this.play; // 재생 수 내림차순 정렬
		}
	}
}
