package com.own.test.datedemo;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author : xy
 * @date : 2021/7/30 8:42
 */
public class DateDemo {
    public static void main(String[] args) {

    }

    /**
     * 1.获取当前日期
     */
    @Test
    public void demo1() {
        //获取当前年月日
        LocalDate today = LocalDate.now();
        System.out.println("当前的日期年月日:" + today);
        //获取当前年月日时分秒
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前年月日时分秒:" + localDateTime);
        //获取当前年月
        YearMonth yearMonth = YearMonth.now();
        System.out.println("当前的日期年月:" + yearMonth);
        //获取当前月日
        MonthDay monthDay = MonthDay.now();
        System.out.println("当前的日期月日:" + monthDay);
    }

    /**
     * 2.指定年月日获取LocalDate日期或指定年月日时分秒获取
     */
    @Test
    public void demo2() {
        //指定的年月日获取日期
        LocalDate date = LocalDate.of(2021, 1, 8);
        System.out.println("指定日期:" + date);
        //指定的年月日时分秒获取日期
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 12, 1, 1);
        System.out.println("指定时分秒日期:" + localDateTime);
    }

    /**
     * 3.获取年，月，日
     */
    @Test
    public void demo3() {
        //获取LocalDate的年月日
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.println("年:" + year);
        System.out.println("月:" + month);
        System.out.println("日:" + day);
    }

    /**
     * 4.对比日期的月日
     */
    @Test
    public void demo4() {
        //对比日期的月日
        LocalDate dateNow = LocalDate.now();
        LocalDate date = LocalDate.of(2020, 1, 6);
        MonthDay monthDay = MonthDay.of(date.getMonth(), date.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(dateNow);

        if (currentMonthDay.equals(monthDay)) {
            System.out.println("到了出发日期");
        } else {
            System.out.println("出发日期还没有到");
        }
    }

    /**
     * 5.日期运算以加法为例，减法同加法
     */
    @Test
    public void demo5() {
        //日期运算以加法为例，减法同加法
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前时间:" + localDateTime);

        LocalDateTime plusDay = localDateTime.plusDays(1);
        System.out.println("当前日期一天后的日期:" + plusDay);

        LocalDateTime plusMonth = localDateTime.plusMonths(1);
        System.out.println("当前日期一个月后的日期:" + plusMonth);

        LocalDateTime plusYear = localDateTime.plusYears(1);
        System.out.println("当前日期一年后的日期:" + plusYear);

        //Period操作天以上的时间单位
        LocalDateTime plusY = localDateTime.plus(Period.ofYears(1));
        System.out.println("Period操作当前日期一年后的日期:" + plusY);

        //Duration操作天以下的时间单位
        LocalDateTime plusH = localDateTime.plus(Duration.ofHours(1));
        System.out.println("Duration操作当前日期一个小时后的日期:" + plusH);

        //ChronoUnit指定运算数和时间单位
        LocalDateTime plusW = localDateTime.plus(1, ChronoUnit.WEEKS);
        System.out.println("ChronoUnit操作当前日期一周后的日期:" + plusW);
    }

    /**
     * 6.Clock时钟类用于获取当时的时间戳，或当前时区下的日期时间信息
     * System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换
     */
    @Test
    public void demo6() {
        //Clock时钟类用于获取当时的时间戳，或当前时区下的日期时间信息
        //System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换。
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());

        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());
    }

    /**
     * 7.LocalDate类有两类方法isBefore()和isAfter()用于比较日期。
     * 调用isBefore()方法时，如果给定日期小于当前日期则返回true。
     */
    @Test
    public void demo7() {
        //LocalDate类有两类方法isBefore()和isAfter()用于比较日期。调用isBefore()方法时，如果给定日期小于当前日期则返回true。
        LocalDate today = LocalDate.now();
        LocalDate beDay = LocalDate.of(2020, 1, 6);
        LocalDate afDay = LocalDate.now().plusDays(1);
        if (afDay.isAfter(today)) {
            System.out.println("之后的日期:" + afDay);
        }
        if (beDay.isBefore(today)) {
            System.out.println("之前的日期:" + beDay);
        }
    }

    /**
     * 8.ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。这在Java 8
     * 以前都是 GregorianCalendar类来做的
     */
    @Test
    public void demo8() {
        //ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。这在Java 8以前都是 GregorianCalendar类来做的
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime newYorkDay = ZonedDateTime.of(localDateTime, america);
        System.out.println("转化为纽约时区:" + newYorkDay);
    }

    /**
     * 9.时间戳信息里同时包含了日期和时间，这和java.util.Date很像。
     * 实际上Instant类确实等同于 Java 8之前的Date类，你可以使用Date类和Instant类各自的转换方法互相转换，
     * 例如：Date.from(Instant)将Instant转换成java.util.Date，Date.toInstant()则是将Date类转换成Instant类。
     */
    @Test
    public void demo9() {
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());
    }

    /**
     * 10.日期字符串互转
     */
    @Test
    public void demo10() {
        //日期字符串互转
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:" + str);

        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //字符串转日期
        LocalDateTime date2 = LocalDateTime.parse(str, format2);
        System.out.println("字符串转日期:" + date2);
    }

    /**
     * 11.计算两个日期之间的年数，月数，周数，天数
     */
    @Test
    public void demo11() {
        //计算两个日期之间的年数，月数，周数，天数
        LocalDate localDate = LocalDate.now();
        LocalDate date = LocalDate.of(2019, 1, 1);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime dateTime = LocalDateTime.of(2019, 1, 1, 1, 1, 1);

        //ChronoUnit计算(推荐使用)
        long diffChrono = ChronoUnit.DAYS.between(date, localDate);
        System.out.println("ChronoUnit计算相差天数" + diffChrono);

        //Period处理getDays方法只对比天，忽略年月不是根据年月转化
        Period diff = Period.between(date, localDate);
        System.out.println("Period相差天:" + diff.getDays());
        System.out.println("根据日期转化的相差月:" + diff.toTotalMonths());

        //Duration处理getDays方法只对比天，忽略年月不是根据年月转化，to方法是根据日期转化后计算的
        Duration between = Duration.between(dateTime, localDateTime);
        System.out.println("Duration相差天:" + between.toDays());
    }

    /**
     * 12.判断是否闰年
     */
    @Test
    public void demo12() {
        //是否闰年
        LocalDate today = LocalDate.of(2021, 1, 1);
        LocalDate leapYear = LocalDate.of(2000, 1, 1);
        if (today.isLeapYear()) {
            System.out.println("2021年是闰年");
        } else {
            System.out.println("2021年不是闰年");
        }
        if (leapYear.isLeapYear()) {
            System.out.println("2000年是闰年");
        } else {
            System.out.println("2000年不是闰年");
        }
    }

    /**
     * 13.修改指定的时间单位
     */
    @Test
    public void demo13() {
        //修改是定时间单位的值
        LocalDate localDate1 = LocalDate.now().withDayOfMonth(10);
        System.out.println("当月10号的日期:" + localDate1);

        //修改为指定条件的日期
        LocalDate localDate2 = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当月最后一天的日期" + localDate2);

        LocalDate localDate3 = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当月第一天的日期" + localDate3);

        //指定要修改的时间单位和修改后的值
        LocalDate localDate4 = LocalDate.now().with(ChronoField.MONTH_OF_YEAR, 12);
        System.out.println("获取当前日期12月份的日期:" + localDate4);
    }
}
