import java.util.List;

/*
  Not sure yet if I actually want to make this a class. I'm doing it this way now
  because I think I may want to eventually include more functionality relating
  specifically to the climb log.
 */

public abstract class ClimbLog {

    private List<Climb> climbList;

    protected ClimbLog(List<Climb> climbList){
        this.climbList = climbList;
    }


    public abstract Grade getAvgGrade();

    public abstract void logClimb(Climb climbLogged);

    protected List<Climb> getClimbList(){
        return climbList;
    }

    public void printClimbs(){
        for (Climb climb : climbList){
            System.out.println(climb);
        }
    }

    public static interface Calculator {

        /*  I don't think calculating the average grade is actually a healthy thing to do
            since it may encourage climbers to avoid logging lower grade climbs to boost
            the average grade climbed. I'm keeping it here for now, because I will be using
            this interface to calculate other, more meaningful statistics in the future.
         */
        Grade calculateAvgGrade(ClimbLog climbList);
    }
}
