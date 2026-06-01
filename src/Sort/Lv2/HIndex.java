package Sort.Lv2;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
//        int[] citations = {3, 0, 6, 1, 1, 6, 4, 5};

        System.out.println(solution(citations));
    }

    public static int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        int size = citations.length;

        for(int i = 0; i < citations.length; i++){
            int h = size - i;

            if(citations[i] >= h) {
                return h;
            }
        }

        return answer;
    }
}

/*
어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.

결론적으로는 h를 찾는 문제

h라는 기준점을 잡아서 기준보다 많이 인용된 게 h번만큼 있어야하고, 나머지가 전부 h라는 것보다 작다면, 이때의 h를 구하라.

H-index라는 걸 제대로 이해했어야 한다. h의 경우 기준점인 h인 것도 맞지만 정답에서 바라는 건 얼만큼 인용되었는가?를 보는 것이기 때문에 length(논문 개수)에 대해서 return 되도록한다.

최초로 정렬한 후 앞에서부터 자기보다 크거나 같은 논문의 개수를 h로 설정한 후 자신의 값(인용된 수가)이 H와 크거나 같을 경우 return하고 아닐 경우에는 0으로 리턴한다.

만약에 시간 제한이 좀 빠듯하거나 할 경우, 역순으로 정렬 후 혹은 정렬 후 역순으로 인덱싱을 통해서 size를 통해 찾아가는 것도 빠른 방법 중 하나일 것으로 생각이 든다.

이유는 최댓값이라는 목적에서 나온다. 역순으로 할 경우 O(N)만큼 돌 수 있지만, 그런 케이스는 매우 드물게 적용되며, 전체 순회를 최소화할 수 있다는 장점이 있다.
이런 최댓값을 구하는 경우에 대해서 혹은 어떤 특정 기준점을 구하는 경우 Sorting 작업을 활용해야한다는 관점 하나만 포인트로 가지고 있다면, 나중에 DB에서 꺼낸 Report 데이터, Summary 데이터를
사용자에게 보여주는 방식에 따라 효율성 있게 적용할 수 있는 방향성을 잡아줄 수 있다는 생각이 들었다.
*/