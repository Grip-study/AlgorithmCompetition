import java.math.*;
import java.util.*;
class Solution {
    int[] tree;
    int size;
    List<Integer> answer = new ArrayList<>();
  public int[] solution(int n) {
      size = (int) Math.pow(2, n);
      tree = new int[size];
      
      tree[1] = 0;
      for (int idx = 2; idx < size - 1; idx = idx + 2) {
          tree[idx] = 0;
          tree[idx + 1] = 1;
      }
      
      inorder(1);
      
      return answer.stream().mapToInt(i->i).toArray();
  }

  private void inorder(int idx) {
      if(idx * 2 < size) {
          inorder(idx * 2);
      }
      
      answer.add(tree[idx]);
      
      if(idx * 2 + 1 < size) {
          inorder(idx * 2 + 1);
      }
  };
}
