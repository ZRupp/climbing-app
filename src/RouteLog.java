import java.util.ArrayList;

public class RouteLog extends ClimbLog{

    private Calculator calculator;

    public RouteLog(){
        super(new ArrayList<>());
        calculator = new RouteCalculator(); // M3 USING STRATEGY
    }

    public Grade getAvgGrade(){
        return calculator.calculateAvgGrade(this); // M3 USING STRATEGY
    }

    @Override
    public void logClimb(Climb climbLogged) {
        if (climbLogged instanceof GymRoute || climbLogged instanceof OutdoorRoute){
            if (getClimbList().contains(climbLogged)){
                getClimbList().get(getClimbList().indexOf(climbLogged)).logAttempts(climbLogged.getAttempts());
            } else {
                getClimbList().add(climbLogged);
            }
        } else {
            throw new IllegalArgumentException("Invalid climb type.");
        }
    }

}
