package BruteForce.Lv1;

public class MockExam {
    public static void main(String[] args) {
        int[] answersA = {1,2,3,4,5};
        int[] answersB = {1,3,2,4,2};

        for(int a : solution(answersA)) {
            System.out.print(a + " ");
        }
    }
    public static int[] solution(int[] answers) {
        int[] caseA = {1, 2, 3, 4, 5};
        int[] caseB = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] caseC = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] scores = new int[3]; // 점수를 담을 배열


        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == caseA[i % caseA.length]) scores[0]++;
            if (answers[i] == caseB[i % caseB.length]) scores[1]++;
            if (answers[i] == caseC[i % caseC.length]) scores[2]++;
        }

        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));

        int count = 0; //결과 배열 사이즈를 세팅해주기 위해
        for (int score : scores) {
            if (score == max) count++;
        }

        // 4. 결과 배열 만들기
        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            if (scores[i] == max) {
                result[index++] = i + 1;
            }
        }

        return result;
    }
}
/*
모의 고사에서 수학 문제를 찍으려고 한다.

1번 찍는 방식: 1 2 3 4 5 반복
2번 찍는 방식: 2 1 2 3 2 4 2 5  반복
3번 찍는 방식: 3 3 1 1 2 2 4 4 5 5 반복

답지를 볼 때 갖아 많이 맞춘 사람은?

결국 정답과 대조를 해보려고 한다면, 다 답을 대조해봐야한다.

완전 탐색의 경우 구현력을 보는 내용이 주로 많이 나오는 거 같다. 물론 중간에 효율을 중요시하는 점과 핵심 아이디어가 필수로 필요하다.

해당 문제에서는 나머지 연산 (%)에 대해 얼마나 잘 활용할 수 있는가를 따지는 거 같다. % 연산을 통해 index의 반복 주기를 나타내며, 각 수포자들의 특징에 맞게 답을 하나씩 비교해서
return 값을 구성하는게 핵심이다.
*/
