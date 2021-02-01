
import java.util.Date;

public class Record implements Comparable{

    private LiquidType type;
    private Date date;
    private float fluidVolume;
    private float waterVolume;

    public Record () {

    }

    public Record (float fluidVolume, Date date, LiquidType type) {
        this.type = type;
        this.date = date;
        this.fluidVolume = fluidVolume;
        this.waterVolume = calcWaterVolume();
    }

    public float getVolume() {
        return waterVolume;
    }

    public LiquidType getType () {
        return type;
    }

    public void setVolume(float fluidVolume) {
        this.fluidVolume = fluidVolume;
        this.waterVolume = calcWaterVolume();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public float calcWaterVolume () {
        float waterVolume = 0f;
        switch (type) {
            case WATER:
                waterVolume = fluidVolume;
                break;
            case MILK:
                waterVolume = fluidVolume * 0.97f;
                break;
            case SOUP:
                waterVolume = fluidVolume * 0.8f;
                break;
            case SODA:
                waterVolume = fluidVolume * 0.99f;
                break;
            default:
                //erorr
                break;
        }

        return waterVolume;
    }

    @Override
    public int compareTo(Object o) {
        Record r = (Record)o;
        return (int) (this.date.getTime() - r.getDate().getTime());
    }
}
