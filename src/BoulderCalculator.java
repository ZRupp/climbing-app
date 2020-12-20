public class BoulderCalculator implements ClimbLog.Calculator {
    public Grade calculateAvgGrade(ClimbLog log){
        int count = 0;
        int sum = 0;
        for (Climb climb : log.getClimbList()){
            if (climb instanceof GymBoulder || climb instanceof OutdoorBoulder){
                count++;
                sum += climb.getGradeOfficial().getNumericEquivalent();
            }
        }

        return Grade.valueOf("V".concat(Integer.toString((count > 0 ? sum/count : -1))));
    }
}
