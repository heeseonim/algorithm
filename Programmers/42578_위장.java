import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {

        HashMap<String, Integer> hm = new HashMap<>();
        for (String[] strings : clothes) {
            hm.put(strings[1], hm.getOrDefault(strings[1], 0) + 1);
        }

        int result = 1;
        for(Integer i : hm.values()) {
            result *= (i + 1);
        }

        return result - 1;
    }
}