package Sort.Lv2;

import java.util.Arrays;

public class makeMinNumber {
    public static void main(String[] args) {
        int[] A = {1, 4, 2};
        int[] B = {5, 4, 4};
    }

    public static int solution(int []A, int []B)
    {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int size = A.length;

        int startA = 0;
        int endA = size - 1;

        int startB = 0;
        int endB = size - 1;

        for(int i=0; i<size; i++) { //반복의 횟수는 A와 B 배열의 크기는 동일하기에
            if(A[startA] < B[startB]) { //A의 최솟값이 더 작을 경우
                int numA = A[startA];
                int numB = B[endB];

                answer += numA * numB; //A의 최솟값이 더 작거나 같기에, A의 최솟값과 B의 최대를 더한다.
                startA++;
                endB--;

            } else if(A[startA] == B[startB]){ //최솟값이 같을 경우에는 최댓값이 더 큰 것을 기준으로 뽑는다.
                if(A[endA] >= B[endB]) {
                    int numA = A[endA];
                    int numB = B[startB];

                    answer += numA * numB;
                    endA--;
                    startB++;
                } else {
                    int numA = A[startA];
                    int numB = B[endB];

                    answer+= numA * numB;
                    startA++;
                    endB--;
                }
            } else { //B의 최솟값이 더 클 경우
                int numA = A[endA];
                int numB = B[startB];

                answer += numA * numB;
                endA--;
                startB++;
            }
        }

        return answer;
    }
}

/*
문제에서 배열 A와 B가 주어졌을 때, 각 문자 하나씩 값을 가져와서 곱한 것의 누적합의 최소를 구하라.

곱한 것들의 누적합이 최소가 되기 위해서는 제일 작은 값 * 제일 큰 값을 곱했을 때 나오는 결과가 제일 작다.
그러므로 배열 A와 배열 B 중 어떤 값이 최소인지를 구분해야지만, 다른 배열에서 최대의 값을 곱해서 곱의 최소를 구할 수 있다.

다만 여기서 최소값이 같을 때, 생각해볼 필요가 있을 거 같다. 처음에는 대수롭지 않게, 작더라도 A에서 뽑지 뭐~ 라고 생각했지만,
같을 경우에는 최댓값이 어떤 배열에 있는지를 골라서 최댓값을 고르는 방식으로 변경이 필요하다.

나의 풀이는 포인터의 개념으로 인덱스를 각 배열 최대, 최소로 관리하여 최소를 뽑았을 때, 최대를 뽑았을 때를 기점으로 진행했다.
변수 선언이 많아지고, 코드가 길어지므로 더럽지만, 그래도 매우 직관적이라고 생각이 든다. 물론 다른 풀이들을 보면 직접 커스텀 정렬을 통해서 값들을 관리하거나 하는 방식으로도 많이 진행헀던데,
개념적으로는 알고 있어야하고 시간이 많거나 실무에서는 더 좋은 효율과 목적을 위해 직접 구현해서 진행하는 방법도 좋을 거 같다.
 */
