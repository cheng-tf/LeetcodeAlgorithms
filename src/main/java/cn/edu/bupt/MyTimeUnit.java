package cn.edu.bupt;

public enum MyTimeUnit {

    YEARS{
        public long getValue(){
            return yearValue;
        }
        public void setValue(long yearValue){
            this.yearValue = yearValue;
        }
    },MONTHS{
        public long getValue(){
            return monthValue;
        }
        public void setValue(long monthValue){
            this.monthValue = monthValue;
        }
    },DAYS{
        public long getValue(){
            return dayValue;
        }
        public void setValue(long dayValue){
            this.dayValue = dayValue;
        }
    },HOURS,MINUTES,SECONDS;
    long abstractValue;
    long yearValue;
    long monthValue;
    long dayValue;
    public long getValue(){
        return abstractValue;
    }
    public void setValue(long abstractValue){
        this.abstractValue = abstractValue;
    }
}
