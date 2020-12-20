import java.util.*;

public class BoulderLog extends ClimbLog{

    private Calculator calculator;

    public BoulderLog(){
        super(new ArrayList<>());
        calculator = new BoulderCalculator(); // M3 USING STRATEGY
    }

    public Grade getAvgGrade(){
        return calculator.calculateAvgGrade(this); // M3 USING STRATEGY
    }

    @Override
    public void logClimb(Climb climbLogged) {
        if (climbLogged instanceof GymBoulder || climbLogged instanceof OutdoorBoulder){
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
