package Hash.Level2;

import java.util.*;

public class ParkingAmount {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};

        String[] records = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"
        };

        System.out.println(Arrays.toString(solution(fees, records)));
    }

    public static int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0];
        int basicAmount = fees[1];
        int addTime = fees[2];
        int addAmount = fees[3];

        // 1. HashMap 대신 TreeMap을 사용해서 차량 번호 자동 오름차순 정렬
        Map<String, ParkingCar> map = new TreeMap<>();

        for (String record : records) {
            String[] rec = record.split(" ");
            String time = rec[0];
            String carNum = rec[1];
            String type = rec[2];

            if (!map.containsKey(carNum)) {
                map.put(carNum, new ParkingCar(carNum));
            }

            // 2. IN 일 때는 입차 시간 기록, OUT 일 때는 누적 시간(sumTime)에 더해줌
            ParkingCar car = map.get(carNum);
            if ("IN".equals(type)) {
                car.in(time);
            } else {
                car.out(time);
            }
        }

        int[] answer = new int[map.size()];
        int idx = 0;
        for (Map.Entry<String, ParkingCar> entry : map.entrySet()) {
            // 3. 아직 출차되지 않은 차량(isParking == true)은 23:59(1439)로 강제 출차 처리 후 계산
            ParkingCar car = entry.getValue();
            if (car.isParking) {
                car.out("23:59");
            }
            answer[idx++] = car.getParkingAmount(basicTime, basicAmount, addTime, addAmount);
        }

        return answer;
    }

    public static class ParkingCar {
        private final String carNum;
        private int inTime;
        private int sumTime = 0; // 누적 주차 시간
        private boolean isParking = false; // 현재 주차 중인지 여부 상태값


        public ParkingCar(String carNum) {
            this.carNum = carNum;
        }

        public void in(String time) {
            this.inTime = changeTimeCast(time);
            this.isParking = true;
        }

        public void out(String time) {
            int outTime = changeTimeCast(time);
            this.sumTime += (outTime - this.inTime); // 나갈 때마다 누적 시간에 합산
            this.isParking = false; // 출차 완료 상태 변경
        }

        public int changeTimeCast(String time) {
            String[] t = time.split(":");
            return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }

        public int getParkingAmount(int baseTime, int baseAmount, int addTime, int addAmount) {
            if (this.sumTime <= baseTime) {
                return baseAmount;
            } else {
                int t = this.sumTime - baseTime;
                // 올림 계산 처리 (나누어 떨어지지 않으면 1분이라도 올림)
                int count = (int) Math.ceil((double) t / addTime);
                return baseAmount + (count * addAmount);
            }
        }

        public String getCarNum() {
            return carNum;
        }
    }
}

/*
 Return 요구 사항: 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return 하도록 solution 함수를 완성해주세요.

 흔한 주차장 요금 계산 방식, 다만 같은 차량이 입차 - 출차 - 입차 - 출차 하더라도 하나의 차량 번호에 대해서 금액 합산을 계산하여야한다.
 그렇기 때문에, sumTime을 계산하는 방식으로 변경
 그리고 예외 케이스로 만약 출차를 하지 않았다면, 무조건 나간 시간은 23:59으로 계산하도록 진행한다.

[피드백]
해당 문제의 경우 문제를 접근하는데에 있어서 크게 문제될 내용은 없었지만, 문제 좀 잘 읽어야할 거 같다..
문제의 예외 처리에 대해서 조건과 예시로 나열되어있고, Return의 요구사항이 있기 때문에 좀 더 문제에 대해 집중해서 파악하는 게 필요

솔직히, 알고리즘 정도야 바로 생각나서 코드를 구현하면 되기에, 아는 알고리즘이냐 활용할 수 있냐 문제이지만,

문제의 의도 파악과 요구사항 파악을 잘못하면 알고리즘을 파악했다 하더라도, 문제 해결이 안될 수 있음
*/
