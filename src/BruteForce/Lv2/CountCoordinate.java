package BruteForce.Lv2;

public class CountCoordinate {
    public static void main(String[] args) {
        int k = 2;
        int d = 4;

        System.out.println(solution(k, d));
    }

    public static long solution(int k, int d) {
        long answer = 0;
        long longD = d;

        // x 자체를 long으로 선언하여 x * x 연산 시 int 오버플로우를 방지합니다.
        for (long x = 0; x <= longD; x += k) {

            // long * long - long * long 구조가 되어 안전하게 1조 단위 연산이 가능합니다.
            long distanceMaxY = (longD * longD) - (x * x);

            long maxY = (long) Math.sqrt(distanceMaxY);

            answer += (maxY / k) + 1;
        }
        return answer;
    }
}

/*
k의 경우 x 좌표의 배수, y좌표의 배수 역할을 한다 (ex: k가 2라면 가능한 x의 좌표는 0,2,4,6,8 ... / 가능한 y의 좌표는 0,2,4,6,8
d의 경우는 좌표의 최대 거리를 의미한다.

이때 가능한 좌표의 개수를 구해라 (점을 찍었을 때 개수를 구해라)

제한 조건을 보면 int로는 오버플로우 발생 >> 함정이기 때문에 캐치할 수 있도록
피타고라스 정리와 문제 조건에 의해 d >= Math.sqrt(x^2 + y^2) → d^2 >= x^2 + y^2

이때 x좌표를 기점으로 y의 최대 가능 좌표는 Y^2 <= d^2 - x^2 이기 때문에 y는 Math.sqrt(d^2 - x^2)가 된다.

y의 경우 실제 좌표 * k의 값이기 때문에, k만큼 나눈 값이 찍힐 수 있는 y의 좌표 개수가 되며, y가 0일 경우에도 찍어야하기 때문에 +1을 진행한다.

for문의 변수를 int로 할 경우 x+=k 로 int의 제한점이 걸려서 오버플로우 발생하며,
만약 모든 경우의 수 혹은 거쳐간 값을 저장해서 비교하는 방식을 할 경우 시간초과가 나기 때문에 수학적 공식을 활용한 Brute Force 문제이다.
 */
