package Hash.Level2;

import java.util.*;
public class OpenChat {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        Arrays.stream(solution(record)).forEach(System.out::println);
    }

    public static String[] solution(String[] record) {
        Map<String, String> nameMap = new HashMap<>(); //닉네임과 uid를 매핑하는 Map
        List<MyLog> log = new ArrayList<>();
        for(String rec : record) {
            String[] logging = rec.split(" "); //공백으로 나누고
            String type = logging[0];
            String uid = logging[1];
            String nickName = "";
            if(!"Leave".equals(type)) {
                nickName = logging[2];
            }

            if("Enter".equals(type) || "Change".equals(type)) { //새로 들어왔거나 혹은 닉네임을 바꾼 경우
                nameMap.put(uid, nickName);
            }

            if(!"Change".equals(type)) {
                log.add(new MyLog(uid, type));
            }
        }
        String[] answer = new String[log.size()];
        for(int i=0; i<answer.length; i++) {
            String type = log.get(i).getType();
            String uid = log.get(i).getUid();
            switch(type) {
                case "Enter" : answer[i] = nameMap.get(uid) +"님이 들어왔습니다.";
                    break;
                case "Leave" : answer[i] = nameMap.get(uid) +"님이 나갔습니다.";
                    break;
            }
        }


        return answer;
    }

    public static class MyLog {
        private final String uid;
        private final String type;

        public MyLog(String uid, String type) {
            this.uid = uid;
            this.type = type;
        }

        public String getUid() {
            return this.uid;
        }

        public String getType() {
            return this.type;
        }
    }
}
/*
관리자창을 만들기로 한다.
- 누군가가 들어올 때
xx님이 들어왔습니다.
- 누군가가 나갈 때
xx님이 나갔습니다.

채팅방에 닉네임을 변경하는 방법
1. 채팅방 나간 후 새로운 닉네임
2. 채팅방에서 닉네임 변경
(기존 채팅방의 출력된 모든 메시지의 닉네임도 전부 변경된다.)

[풀이]
1. 식별자로는 uid가 있다
2. Enter, Leave, Change
- Enter는 들어왔을 때
- Leave는 나갔을 때
- Change는 채팅방 안에서 닉네임을 바꿀 때

기존 로그를 어떻게 관리하느냐가 관건
기존 로그에는 어떤 uid에 대한 로그인지를 확인해야하며, 그 닉네임과 타입을 추적해야한다.
*/