package pl.edu.pwr.cdmodel;

public class MultRange {
    public int low;
    public int upper;

    public static boolean isIncludedR1InR2(MultRange r1, MultRange r2){
        return (r1.low >= r2.low && r1.upper <= r2.upper);
    }

    public static MultRange translateMul(Multiplicity mul){
        MultRange range = new MultRange();
        switch(mul) {
            case ONE:
                range.low = 1;
                range.upper = 1;
                break;
            case ZERO_TO_ONE:
                range.low = 0;
                range.upper = 1;
                break;
            case MANY:
                range.low = 0;
                range.upper = 10;
                break;
            case ONE_TO_MANY:
                range.low = 1;
                range.upper = 10;
                break;
        }
        return range;
    }

    public void normalize(){
        if (low > 10) low = 10;
        if (upper > 10) upper = 10;
    }

}
