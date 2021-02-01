
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

    public String name, password;
    public Date DoB;
    public float weight, height;
    public Gender gender;
    public File picture;

    public ArrayList<Record> records = new ArrayList<>();

    public Goal currentWeeklyGoal;
    private ArrayList<Goal> pastWeeklyGoals = new ArrayList<>();
    public HashMap<Date, Goal> dailyGoals = new HashMap<>();
    public int points;

    public UserData(String name, Date DoB, String password, float weight, float height, Gender gender, File picture){
        this.name = name;
        this.DoB = DoB;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.picture = picture;
        this.points = 0;
    }


    // For Jackson
    public UserData(){
    }

    /***
     * Adds a record to the array list. Checks if the records added completes a goal and if so awards points.
     * @param fluidVolume Volume of the record.
     * @param date Date the record was created.
     * @param type Type of liquid of the record.
     */
    public void addRecord (float fluidVolume, Date date, String type) {

        LiquidType fluidType = LiquidType.WATER;
        try {
            fluidType = LiquidType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e){
        }

        records.add(new Record(fluidVolume, date, fluidType));

        // check if the record completes the current daily goal
        if (getCurrentDailyGoal() != null) {
            if (calculateProgress(getCurrentDailyGoal(), 1) >= 100 && !getCurrentDailyGoal().isCompleted()) {
                points += getCurrentDailyGoal().getPoints();
                getCurrentDailyGoal().setCompleted(true);
            }
        }

        // check if the record completes the current weekly goal
        if (currentWeeklyGoal != null) {
            if (calculateProgress(currentWeeklyGoal, 7) >= 100 && !currentWeeklyGoal.isCompleted()) {
                points += currentWeeklyGoal.getPoints();
                currentWeeklyGoal.setCompleted(true);
                pastWeeklyGoals.add(currentWeeklyGoal);
                currentWeeklyGoal = null;
            }
        }

        // write the updates to data.txt file to save
        WriteFile();
    }

    /**
     * Add a goal. If it is a daily goal, add it to the stack of previous daily goals. If it is weekly goal simply replace the current weekly goal.
     * @param volume Volume of the goal.
     * @param type Type of the goal, daily or weekly.
     */
    public void addGoal (Float volume, GoalType type) {
        if (type.equals(GoalType.DAILY)) {
            dailyGoals.put(stripToMidnight(new Date()), new Goal(volume, GoalType.DAILY,10));
        } else {
            currentWeeklyGoal = new Goal(volume, GoalType.WEEKLY,30);
        }
        WriteFile();
    }

    /**
     * Finds and returns the daily goal for todays date, if one exists
     * @return Current (todays) daily goal
     */
    public Goal getCurrentDailyGoal () {
        return dailyGoals.get(stripToMidnight(new Date()));
    }

    /**
     * Gets a records from the record list that matches the values.
     * @param date Date the record was added.
     * @param type Type of liquid that the record has.
     * @param vol Volume of the record.
     * @return The record in the array list.
     */
    public Record getRecord (Date date, LiquidType type, Float vol) {
        for (Record r : records) {
            if (r.getDate().equals(date) && r.getType().equals(type) && r.getVolume() == vol) return r;
        }
        return null;
    }

    /**
     * Deletes the passed record from the list.
     * @param rec The record to be deleted.
     */
    public void deleteRecord (Record rec) {
        records.remove(rec);
    }

    /**
     * Converts the UserData objecct in its current state to JSON and then writes this to data.txt
     */
    public void WriteFile(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File dir = new File("data");
        dir.mkdir();
        File file = new File("data/data.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mapper.writeValue(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the specified number of points to the user.
     * @param amount The number of points to add.
     * @return The number of points the user has in total after adding.
     */
    public int addPoints(int amount){
        this.points += amount;
        return this.points;
    }

    /**
     * Sets the users points to a specified amount.
     * @param amount The value to change the users points to.
     * @return The number of points a user now has.
     */
    public int setPoints(int amount){
        this.points = amount;
        return this.points;
    }

    /**
     * Returns the number of points a user has.
     * @return The number of points a user has.
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Takes a JSON representation of the UserData from data.txt and creates a new UserData object from it.
     * @param filepath The filepath of the data file to make the UserData object from.
     * @return The UserData object created.
     */
    public static UserData FromFile(String filepath){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filepath), UserData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new UserData();
        }

    }


    public static void main(String[] args) throws Exception {
        UserData UD = new UserData("Alex", new Date(), "hunter2", 1.5f, 1.2f, Gender.MALE, new File(""));
        UD.WriteFile();
        UserData UD2 = UserData.FromFile("data/data.txt");
        System.out.println(UD2.name);
    }

    /**
     * Checks if there is a daily goal for the date provided.
     * @param date The date to check if there is a daily goal for.
     * @return Whether the goal exists in the hash map.
     */
    public boolean doesDailyGoalExist (Date date) {
        return dailyGoals.containsKey(stripToMidnight(date));
    }


    /**
     * Takes a date and strips hours, minutes and seconds from it. Essentially making it only represent the day.
     * @param date The date to strip.
     * @return The stripped date.
     */
    public Date stripToMidnight (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }


    /**
     * Counts the number of total goals made (daily goals and weekly goal)
     * @return The total number of goals ever created.
     */
    public int getTotalGoals () {
        if (currentWeeklyGoal != null) {
            return pastWeeklyGoals.size() + dailyGoals.size() + 1;
        }
        return pastWeeklyGoals.size() + dailyGoals.size();

    }

    /**
     * Counts the number of daily goals met.
     * @return The number of daily goals met.
     */
    public int getDailyGoalsMet () {
        int met = 0;
        for (Goal g : dailyGoals.values()) {
            if (g.isCompleted()) {
                met++;
            }
        }
        return met;
    }


    /**
     * Counts the number of weekly goals met.
     * @return The number of weekly goals met.
     */
    public int getWeeklyGoalsMet () {
        int met = 0;
        for (Goal g : pastWeeklyGoals) {
            if (g.isCompleted()) {
                met++;
            }
        }
        return met;
    }

    /**
     * Calculates the percentage of the total goals completed by dividing met goals by total goals.
     * @return Returns the percentage of goals that have been completed.
     */
    public float percentageOfGoalsCompleted () {
        int totalGoals = getTotalGoals();
        int metGoals = getDailyGoalsMet() + getWeeklyGoalsMet();
        return (float)metGoals/(float)totalGoals * 100;
    }

    /**
     * Iterates through all the records in the array list and finds which drink type is the most popular.
     * @return The most popular drink type.
     */
    public String getFavouriteDrink () {
        String favouriteDrink = "Water";
        ArrayList<LiquidType> types = new ArrayList<>();
        records.forEach(n -> types.add(n.getType()));
        int currMax = Collections.frequency(types, LiquidType.WATER);

        if (currMax < Collections.frequency(types, LiquidType.MILK)) {
            currMax = Collections.frequency(types, LiquidType.MILK);
            favouriteDrink = "Milk";
        }
        if (currMax < Collections.frequency(types, LiquidType.SOUP)) {
            currMax = Collections.frequency(types, LiquidType.SOUP);
            favouriteDrink = "Soup";
        }
        if (currMax < Collections.frequency(types, LiquidType.SODA)) {
            favouriteDrink = "Soda";
        }
        return favouriteDrink;
    }

    /**
     * Counts up the volume for each day in the records and finds the daily average.
     * @return Average daily volume of water intake.
     */
    public int getAverageDailyVolume() {
        HashMap<String, Float> dailyVolumes = new HashMap<>();
        int days = 0;
        int totalVolume = 0;
        for (Record rec : records) {
            String processedDate;
            processedDate = new SimpleDateFormat("MMM dd yyyy").format(rec.getDate());
            if (dailyVolumes.containsKey(processedDate)) {
                dailyVolumes.put(processedDate, dailyVolumes.get(processedDate) + rec.getVolume());
            } else {
                dailyVolumes.put(processedDate, rec.getVolume());
                days++;
            }
            totalVolume += rec.getVolume();
        }
        
        if (days == 0) {
            return 0;
        }
        return totalVolume/days;
    }

    /**
     * Counts up the volume for each day in the records and places the day : volume values in a hashmap.
     * @return A hashmap with each day as the key and the volume for the day as the value.
     */
    public HashMap<Date, Float> getDailyVolume() {
        HashMap<Date, Float> dailyVolumes = new HashMap<>();
        int days = 0;
        int totalVolume = 0;
        for (Record rec : records) {
            Date date = rec.getDate();
            date = stripToMidnight(date);
            if (dailyVolumes.containsKey(date)) {
                dailyVolumes.put(date, dailyVolumes.get(date) + rec.getVolume());
            } else {
                dailyVolumes.put(date, rec.getVolume());
                days++;
            }
            totalVolume += rec.getVolume();
        }

        return dailyVolumes;
    }

    /**
     * Counts up the total volume for a specified day.
     * @param date The day to count the volume on.
     * @return The volume of the day.
     */
    public float getVolumeOn(Date date) {
        float totalVolume = 0f;

        Date day = stripToMidnight(date);

        for (Record rec : records) {
            if (stripToMidnight(rec.getDate()).equals(day)) {
                totalVolume += rec.getVolume();
            }
        }
        return totalVolume;
    }


    /**
     * Counts the total volume over the specified number of days preceding the current day.
     * @param days The number of days to count over.
     * @return The volume over the days.
     */
    public float getVolumeOverDays (int days) {
        Date currentDate = new Date();
        float totalVolume = 0f;

        Date earlierDate = stripToMidnight(currentDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(earlierDate);
        cal.add(Calendar.DATE, -(days-1));
        earlierDate = cal.getTime();

        for (Record rec : records) {
            if (rec.getDate().after(earlierDate)) {
                totalVolume += rec.getVolume();
            }
        }
        return totalVolume;
    }

    /**
     * Calculates the percentage progress of the given goal.
     * @param goal The goal to check progress of.
     * @param days The number of days preceding the current day that will count towards the goal.
     * @return The progress of the goal.
     */
    public int calculateProgress (Goal goal, int days) {
        float totalVolume = getVolumeOverDays(days);

        return (int)((totalVolume / goal.getGoal()) * 100);
    }
}
