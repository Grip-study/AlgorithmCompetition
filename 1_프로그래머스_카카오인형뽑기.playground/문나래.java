import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int length = board.length;
        Stack<Integer> basket = new Stack<>();
        
        for (int move : moves) {
            int x = move - 1;
            for (int y = 0; y < length; y++) {
                int position = board[y][x];
                if (position > 0) {
                    if (basket.size() > 0 && basket.peek() == position) {
                        basket.pop();
                        answer = answer + 2;
                    } else {
                        basket.push(position);
                    }
                    board[y][x] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}
