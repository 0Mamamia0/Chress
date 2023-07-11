package util;

public class CalendarController {


    private static final byte[] DAY_OFFSET_OF_MONTH = {
            //2009
            4, 0, 2,
            //2010
            5, 1, 1,
            4, 6, 2,
            4, 0, 3,
            5, 1, 3
    };

    private static final byte[] DAY_OF_MONTH = {

            //2009 |2010
            31, 28, 31,
            30, 31, 30,
            31, 31, 30,
            31, 30, 31,

    };





    private int year;
    private int month;
    private int day;



    public CalendarController() {
        init();
    }


    private void init() {
        this.year = 2009;
        this.month = 10;
        this.day = 1;
    }


    public void previousMonth() {
        if(year == 2009 && month == 10) return;
        if(year == 2010 && month == 1) {
            year = 2009;
            month = 12;
            return;
        }
        month --;
    }

    public void nextMonth() {
        if(year == 2010 && month == 12) return;
        if(year == 2009 && month == 12) {
            year = 2010;
            month = 1;
            return;
        }
        month ++;
    }



    public int getYear() {
        return year;
    }


    public int getMonth() {
        return month;
    }


    public int getDay() {
        return day;
    }


    public int getDayOffsetOfMonth() {
        if(year == 2009) {
            return DAY_OFFSET_OF_MONTH[month - 10];
        }
        return DAY_OFFSET_OF_MONTH[month + 2];
    }

    public int getDayOfMonth() {
        return DAY_OF_MONTH[month - 1];
    }


    // day: -7 ~ 7
    private void add(int num) {

        int nextDay = this.day + num;
        int dayOfMonth = getDayOfMonth();
        if(nextDay < 1) {
            if (year == 2009) {
                if(month > 10) {
                    month --;
                    day = getDayOfMonth() + nextDay;
                }
            } else if (year == 2010) {
                if(month > 1) {
                    month --;
                } else {
                    year --;
                    month = 12;
                }
                day = getDayOfMonth() + nextDay;
            }
        } else if (nextDay > dayOfMonth) {
            if(month < 12) {
                month ++;
                day = nextDay - dayOfMonth;
            } else {
                if(year == 2009) {
                    year ++;
                    month = 1;
                    day = nextDay - dayOfMonth;
                }
            }
        } else {
            day = nextDay;
        }


    }

    public void left() {
        add(-1);
//        if(day > 1) {
//            day --;
//        } else if(day == 1) {
//            if(year == 2009) {
//                if(month > 10) {
//                    month --;
//                    day = DAY_OF_MONTH[month - 1];
//                }
//            } else {
//                if(month > 1) {
//                    month --;
//                } else {
//                    year --;
//                    month = 12;
//                }
//                day = DAY_OF_MONTH[month - 1];
//
//            }
//
//        }
    }

    public void right() {
        add(1);
//        if(day < DAY_OF_MONTH[month - 1]) {
//            day ++;
//        } else if(day == DAY_OF_MONTH[month - 1]){
//            if (month < 12) {
//                month ++;
//                day = 1;
//            } else {
//                if(year == 2009) {
//                    year++;
//                    month = 1;
//                    day = 1;
//                }
//            }
//        }

    }


    public void up() {
        add(-7);
    }

    public void down() {
        add(7);
    }
}
