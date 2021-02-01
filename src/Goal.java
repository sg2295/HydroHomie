
import java.util.Date;

public class Goal {
    private float goal;
    private Date date;
    private float progress;
    private boolean completed;
    private int points;
    private GoalType type;

    public Goal () {
        
    }

    public Goal(float goal, GoalType type, int points) {
        this.goal = goal;
        this.date = new Date();
        this.progress = 0;
        this.completed = false;
        this.points = points;
        this.type = type;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean state) {
        this.completed = state;
    }

    public Date getDate() {
        return date;
    }

    public float getProgress() {
        return progress;
    }

    public float getGoal() {
        return goal;
    }

    public int getPoints() {return points; }

    public GoalType getType() {
        return type;
    }
}