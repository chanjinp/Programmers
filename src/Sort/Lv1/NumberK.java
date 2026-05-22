package Sort.Lv1;

import java.util.Arrays;

public class NumberK {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] command = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        for (int a : solution(array, command)) {
            System.out.print(a + " ");
        }
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int index = 0;
        for (int[] cmdArr : commands) {
            //원소의 길이는 3으로 고정
            int cmdA = Math.max(cmdArr[0] - 1, 0);
            int cmdB = Math.max(cmdArr[1] - 1, 0);
            int K = Math.max(cmdArr[2] - 1 , 0);

            int[] arr = new int [cmdB - cmdA + 1];
            int idx = 0;
            while(cmdA <= cmdB) {
                arr[idx++] = array[cmdA++];
            }

            Arrays.sort(arr);

            answer[index++] = arr[K];
        }
        return answer;
    }
}

/*
문제 풀이
ex) command[0][0] 부터 command [0][1]까지 array를 잘랐을 때 정렬 후 command [0][2]의 번째 수를 구했을 때 return 되는 것이 결과

오늘은 정렬 문제로 주말 공부를 위한 쉬어가는 하루..
if 문을 반복하기보다는 Math의 max 라이브러리를 사용하여 코드 간소화

Arrays.sort 라이브러리를 썼지만, 내부 동작을 살펴보면  DualPivotQuicksort.sort(a, 0, 0, a.length);로 듀얼 피벗을 퀵소트를 사용한다.
보통 퀵소트의 경우 하나의 기준(피벗)을 세우고 기준점보다 큰, 작은 그룹들을 나누면서 정렬을 해가는 과정이고 전체적으로 트리 형태를 가지기 때문에 O(NlogN) 시간 복잡도를 가진다.
다만 이미 정렬 되어있는 경우 최대 N^2의 시간 복잡도를 가지기도 하고, 피벗을 어떤 적절한 값으로 두는가에 따라 또 나뉜다.

듀얼 피벗은 기준을 2개로 잡는다는 뜻이기도 하며, 기준점을 더 많이 가지기에 더 세밀한 기준점을 가지고 정렬에 사용된다.
이는 싱글 피벗일 경우보다는 최악의 경우가 좀 적어진다.

왜 듀얼 피벗일까? 다른 정렬 기법들도 많은데??
Merge Sort로 새로운 배열을 하나 가지지도 않아도 되는 장점 (피벗을 기준으로 나누기 때문)으로 메모리 효율성을 챙긴다.
데이터의 지역성이 장점이기도 하다. >> 이 부분은 CPU를 잘 활용한다고도 하지만, 개인적인 생각으로 지역성이라고 했을 때 피벗이라는 기준점을 비교하여 비슷한 애들끼리 모이기 때문에,
데이터 지역성이 장점이라고도 하는 걸로 보이며, CPU가 연산을 진행할 때, 1 다음 2가 나와야하니까 같은 그룹에서 찾듯이 좀 더 효율적이라고 하는 것 같다. (지역성이라는 단어를 보고 개인적인 생각 정리)


실무에서는 단순 Sorting을 위해서 라이브러리를 사용하는 경우가 허다하다. 하지만 어떤 동작인지 어떤 방식인지 알아야. DB에서 데이터를 order by를 통해 꺼냈을 때, 정렬이 된 채로 왔지만, 다른 기준으로 서비스 로직에서 추가 Sorting할 때
혹은 DB에서 꺼낸 값의 일부를 가지고 sorting을 진행할 때 등의 케이스에 따라 어떻게 할 것인가는 개발자의 역량이기 때문에, 라이브러리 하나에 내부 동작을 하나씩 뜯어보는 습관이 중요한 거 같다.
*/