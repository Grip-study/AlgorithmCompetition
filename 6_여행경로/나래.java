import java.util.*;
import java.util.stream.Collectors;
class Solution {
    List<Ticket> tickets = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        
        this.tickets = Arrays.stream(tickets) //
            .map(ticket -> new Ticket(ticket[0], ticket[1])) // 
            .sorted((Ticket t1, Ticket t2) -> t1.destination.compareTo(t2.destination)) // 목적지를 알파벳순으로 정렬
            .collect(Collectors.toList());
        
        dfs("ICN", answer, 0);
        
        return answer;
    }

    private boolean dfs(String airport, String[] answer, int idx) {
        answer[idx] = airport;
        
        // 한 번 다 차면 바로 끝이야.. 왜냐면 이미 목적지를 알파벳순으로 정렬했으니깐 .. 처음으로 다 찬게 알파벳순으로 제일 빠른거니깐 다른거 할 필요 없음
        if (idx == tickets.size()) { 
            return true; 
        }
        
        for (Ticket ticket : tickets) {
            if (ticket.used == false && airport.equals(ticket.departure)) {
                ticket.used = true;
                
                // result가 true 면 역시 다른 경로를 찾을 필요 없이 바로 return true 때려버리기
                boolean result = dfs(ticket.destination, answer, idx + 1);
                if (result) {
                    return true;
                }
                
                ticket.used = false;
            }
        }
        
        // for문을 다 돌아도 갈 곳이 없으면 다른 경로를 찾아야 하니깐 return false
        return false;
    }

    static class Ticket {
        String departure;
        String destination;
        boolean used;
        
        Ticket(String departure, String destination) {
            this.departure = departure;
            this.destination = destination;
        }
    }
}

