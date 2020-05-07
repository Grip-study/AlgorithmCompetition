import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;
        int n = food_times.length;
        
        List<Food> foods = new LinkedList<>();
        for (int i = 0; i < food_times.length; i++) {
            foods.add(new Food(food_times[i], i + 1));
        }
        
        foods.sort(new Comparator<Food>() {
            @Override
            public int compare(Food f1, Food f2) {
               return f1.time - f2.time;
            }
        });
        
        int time = 0;
        int i = 0;
        for (Food food : foods) {
            int diff = food.time - time;
            if (diff != 0) {
                long spend = diff * n;
                if (spend <= k) {
                    k = k - spend;
                    time = food.time;
                } else {
                    k = k % n;
                    foods.subList(i, food_times.length).sort(new Comparator<Food>() {
                        @Override
                        public int compare(Food f1, Food f2) {
                           return f1.index - f2.index;
                        }
                    });
                    return foods.get(i + (int) k).index;
                }
            }
            i ++;
            n --;
        }
        
        return -1;
    }
    
    class Food {
        int time;
        int index;
        
        Food(int time, int index) {
            this.time = time;
            this.index = index;
        }
    }
}
