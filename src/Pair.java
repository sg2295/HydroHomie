public class Pair {
    public String key;
    public Object data;

    @Override
    public String toString() {
        if (data instanceof String) {
            return "\"" + key + "\": \"" + data.toString() + "\"";
        } else {
            return "\"" + key + "\": " + data.toString() + "";
        }
    }

    public Pair(String key, Object data){
        this.key = key;
        this.data = data;
    }
}
