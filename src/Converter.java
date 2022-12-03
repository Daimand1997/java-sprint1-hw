public class Converter {
    static final double ownStepInSM = 0.75;
    static final double ownCaloriesInStep = 50;

    static public double countDistance(int steps)
    {
        double distance = 0;
        if(steps >= 0) {
            distance = steps * ownStepInSM;
        }
        return distance;
    }

    static public double burnedCalories(int steps)
    {
        double calories = 0;
        if(steps >= 0) {
            calories = (steps * ownCaloriesInStep) / 1000;
        }
        return calories;
    }
}
