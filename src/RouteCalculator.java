public class RouteCalculator implements ClimbLog.Calculator {

    public Grade calculateAvgGrade(ClimbLog log){
        int count = 0;
        int sum = 0;
        for (Climb climb : log.getClimbList()){
            if (climb instanceof GymRoute || climb instanceof OutdoorRoute){
                count++;
                sum += climb.getGradeOfficial().getNumericEquivalent();
            }
        }

        return convertAvgRouteToGrade((count > 0? sum/count : -1));
    }

    private Grade convertAvgRouteToGrade(int avgRouteGrade){

        if (avgRouteGrade <=9){
            System.out.println(avgRouteGrade);
            return  Grade.valueOf("YDS".concat(Integer.toString(avgRouteGrade)));
        } else {

            int minNumericGrade = 10;
            int checkedGrade;
            int maxNumericGrade = 15;

            for (int i = 0; i <= maxNumericGrade - minNumericGrade; i++){
                checkedGrade = minNumericGrade + i;
                int result = avgRouteGrade - (4*i) - checkedGrade;
                if (result <= 4){
                    String modifier;

                    switch (result){
                        case 1:
                            modifier = "A";
                            break;
                        case 2:
                            modifier = "B";
                            break;
                        case 3:
                            modifier = "C";
                            break;
                        case 4:
                            modifier = "D";
                            break;
                        default:
                            modifier = "E"; // This is bad maybe. Will cause an IllegalArgumentException to be thrown.
                    }
                    return Grade.valueOf("YDS".concat(Integer.toString(checkedGrade).concat(modifier)));
                }
            }

        }
        return null;
    }

}
