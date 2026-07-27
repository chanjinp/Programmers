package BruteForce.Lv2;

public class PrimeNumberInK {
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;

        System.out.println(solution(n,k));
    }

    public static int solution(int n, int k) {
        int answer = 0;

        // 1. n을 k진수 문자열로 변환 후 "0"을 기준으로 분할
        String toStr = Integer.toString(n, k);
        String[] number = toStr.split("0");

        for (String s : number) {
            // 빈 문자열 스킵 (0이 연속으로 나온 경우)
            if (s.isEmpty()) {
                continue;
            }

            // 2. 잘라낸 문자열을 그대로 10진수 long으로 파싱
            long num = Long.parseLong(s);

            // 3. 소수인지 확인 후 카운트
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    // 소수 판별 메서드
    private static boolean isPrime(long num) {
        // 1 이하의 숫자는 소수가 아님
        if (num < 2) {
            return false;
        }

        // 2부터 sqrt(num)까지 나누어떨어지는지 검사
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false; // 약수가 존재하면 소수가 아님
            }
        }

        return true; // 끝까지 나누어떨어지지 않으면 소수
    }
}
/*
주어진 K 진수로 변환 후에 0으로 잘라내어 소수인지 아닌지 판단하는 로직을 가진 문제.
소수의 판별 기준과 문제에서 101은 소수가 아니듯 0이라는 Key를 가지고 Split 하는 걸 떠올리는 문제.

해당 문제의 경우 Split한 결과를 10진수로 그대로 읽고 해당 숫자가 소수인지 판별하고 카운팅만 해주면 된다.
 */
