package com.xmu.haruki.information;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FitbitSleepInformation {
    private static final Random random=new Random();
    private Integer id;
    private LocalDate date;
    private Integer score;
    private LocalTime hoursSleep;
    private Integer remSleep;
    private Integer deepSleep;
    private Integer heartRate;
    private LocalTime startAt;
    private LocalTime endAt;

    public void init(LocalDate start){
        this.date=start;
        simulateInfo();
    }

    public void init(){
        //测试数据从2022-5-10日开始模拟，每2s模拟一天睡眠
//        this.date=LocalDate.of(2022,5,10);
        //默认从今天开始模拟
        this.date=LocalDate.now();
        simulateInfo();

    }

    public void nextDay(){
        this.date=date.plusDays(1);
        simulateInfo();
    }

    public void simulateInfo(){
        //模拟睡眠分数
        this.score=random.nextInt(40)+60;
        this.startAt=RandomLocalTime.createTime(LocalTime.of(21,0),LocalTime.of(23,59));
        this.endAt=RandomLocalTime.createTime(LocalTime.of(6,0),LocalTime.of(9,0));
        this.hoursSleep=LocalTime.ofSecondOfDay(LocalTime.of(23,59).toSecondOfDay()-startAt.toSecondOfDay()+endAt.toSecondOfDay());
        this.remSleep =random.nextInt(15)+15;
        this.deepSleep=random.nextInt(10)+6;
        this.heartRate=random.nextInt(35)+45;
    }

    static class RandomLocalTime{
        public static LocalTime createTime(LocalTime start,LocalTime end){
            int startSeconds=start.toSecondOfDay();
            int endSeconds=end.toSecondOfDay();
            int randomSeconds= ThreadLocalRandom.current().nextInt(startSeconds,endSeconds);
            return LocalTime.ofSecondOfDay(randomSeconds);
        }
    }
}
