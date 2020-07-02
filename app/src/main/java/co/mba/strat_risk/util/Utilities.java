package co.mba.strat_risk.util;

import java.util.ArrayList;

public class Utilities {

    public static ArrayList<Class> runningActivities = new ArrayList<>();

    /* <ACTIVITY_STATUS> */
    public static void addThisActivityToRunningActivityies (Class cls) {
        if (!runningActivities.contains(cls)) runningActivities.add(cls);
    }

    public static void removeThisActivityFromRunningActivities (Class cls) {
        runningActivities.remove(cls);
    }

    public static boolean isActivityInBackStack (Class cls) {
        return runningActivities.contains(cls);
    }

    public static int activitiesInQueue(){
        return runningActivities.size();
    }
}
