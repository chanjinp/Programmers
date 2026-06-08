package Greedy.Lv1;

public class SportsUniform {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};

        System.out.println(solution(n, lost, reserve));
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] student = new int[n+1]; //1 ~ n 시작하기 때문

        student[0] = -1;

        //체육복 세팅
        for(int l : lost) {
            student[l]--;
        }

        //체육복 세팅
        for(int r : reserve) {
            student[r]++;
        }

        for(int i = 1; i< student.length; i++) {
            if(student[i] > 0) { //여분의 체육복이 있다면?
                if(i-1 > 0 && student[i-1] < 0 ) { //자기보다 앞에 학생이 체육복이 없다면?
                    student[i-1]++; //체육복을 받음
                } else if (i+1 < student.length && student[i+1] < 0) { //자기보다 뒤에 학생이 체육복이 없다면?
                    student[i+1]++;
                }
            }
        }

        for(int s : student) {
            if(s >= 0) {
                answer++;
            }
        }

        return answer;
    }
}

/*
여별의 체육복이 있는 학생이 이들에게 체육복을 빌려주려고 한다.
번호는 체격순!
바로 앞 혹은 바로 뒷 학생에게만 체육복을 빌려줄 수 있다.
체육복의 경우 최대한 많이 빌려서 많은 학생들이 체육 수업을 들어야한다.

전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve

체육 수업을 들을 수 있는 학생들의 최댓값을 return 하라.

만약 잃어버린 사람이 여분이 있는 사람보다 사이즈가 클 경우에는 어떻게 될까?

ex) n = 7명일 때 lost = {1, 3, 4, 6} reserve = { 2, 5, 7 }
2는 1에게 빌려주거나 3에게 빌려줄 수 있다.
5는 4에게 빌려주거나 6에게 빌려줄 수 있다.
7은 6에게만 빌려줄 수 있다.

이럴 경우에 5가 만약 6에게 빌려준다고 하면 7은 6에게만 빌려줄 수 있는 기회를 놓치게 되며, 최댓값을 잃어버리게 된다.
*/
