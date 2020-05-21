import java.util.*;
class Solution {
    String[] user_id;
    String[] banned_id;
    Set<String> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        
        dfs(new boolean[user_id.length], 0);
        
        return set.size();
    }
    
    private void dfs(boolean[] visited, int idx) {
        if (idx == banned_id.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < user_id.length; i ++) {
                if (visited[i]) {
                    sb.append(i);
                }
            }
            set.add(sb.toString());
            return;
        }
        
        for (int i = 0; i < user_id.length; i++) {
            if(visited[i] == false && match(banned_id[idx], user_id[i])) {
                visited[i] = true; 
                dfs(visited, idx + 1); 
                visited[i] = false;
            }
        }
    }
    
    private boolean match(String pattern, String target) {
        pattern = pattern.replace("*", "[\\w\\d]");
        return target.matches(pattern);
    }
}
