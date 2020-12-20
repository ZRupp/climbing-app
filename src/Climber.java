import java.time.LocalDate;
import java.util.Comparator;

public class Climber implements Comparable<Climber>{
    private static int numberOfClimbers = 0;
    private String climberID, firstName, lastName;
    private int age;
    private double weightInPounds, bodyFatPercentage;
    private LocalDate startDate;
    private ClimbLog boulderLog, routeLog;

    public final static Comparator<Climber> LAST_FIRST_NAME_COMPARATOR = new ClimberLastNameFirstNameComparator();
    public final static Comparator<Climber> START_DATE_COMPARATOR = new ClimberStartDateComparator();
    public final static Comparator<Climber> CLIMB_COUNT_COMPARATOR = new ClimberLoggedClimbCountComparator();

    private Climber(ClimberBuilder builder){
        Climber.numberOfClimbers++;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.weightInPounds = builder.weightInPounds;
        this.bodyFatPercentage = builder.bodyFatPercentage;
        startDate = LocalDate.now();
        boulderLog = new BoulderLog();

        routeLog = new RouteLog();
        climberID = String.format("%s%s%s%s%s", firstName.charAt(0), lastName.charAt(0), startDate.getMonthValue(),
                startDate.getDayOfMonth(), startDate.getYear());

    }

    public static class ClimberBuilder {
        private String firstName, lastName;
        private int age;
        private double weightInPounds = 0.0, bodyFatPercentage = 0.0;

        public ClimberBuilder(String firstName, String lastName, int age){
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public ClimberBuilder weightInPounds(double var){
            this.weightInPounds = var;
            return this;
        }

        public ClimberBuilder bodyFatPercentage(double var){
            this.bodyFatPercentage = var;
            return this;
        }

        public Climber build(){
            return new Climber(this);
        }
    }

    private static class ClimberLastNameFirstNameComparator implements Comparator<Climber> {

        @Override
        public int compare(Climber c1, Climber c2){
            if (c1.lastName.compareTo(c2.lastName) == 0){
                return c1.firstName.compareTo(c2.firstName);
            } else {
                return c1.lastName.compareTo(c2.lastName);
            }
        }
    }

    private static class ClimberStartDateComparator implements Comparator<Climber>{
        @Override
        public int compare(Climber c1, Climber c2){
            return c1.startDate.compareTo(c2.startDate);
        }
    }

    private static class ClimberLoggedClimbCountComparator implements Comparator<Climber>{
        @Override
        public int compare(Climber c1, Climber c2){
            int c1ClimbCount = c1.getNumberOfLoggedClimbs();
            int c2ClimbCount = c2.getNumberOfLoggedClimbs();
            return Integer.compare(c1ClimbCount, c2ClimbCount);
        }
    }

    public static int getNumOfClimbers(){ // M2 HOMEWORK STATIC
        return numberOfClimbers;
    }

    // Logs a climb for the climber.
    public void logClimb(Climb climbLogged){
        if (climbLogged instanceof GymBoulder || climbLogged instanceof OutdoorBoulder){
            boulderLog.logClimb(climbLogged);
        } else {
            routeLog.logClimb(climbLogged);
        }
    }

    public void printClimbs(){
        System.out.println("Boulders logged: ");

        if (boulderLog.getClimbList().size() > 0){
            boulderLog.printClimbs();
        } else {
            System.out.println("None");
        }

        System.out.println();
        System.out.println("Routes logged: ");

        if (routeLog.getClimbList().size() > 0) {
            routeLog.printClimbs();
        } else {
            System.out.println("None");
        }
    }

    public void printAvgGrades(){
       System.out.print("Average Boulder Grade: ");
        try {
           System.out.println(boulderLog.getAvgGrade().getRepresentation());
       } catch (IllegalArgumentException e){
           System.out.println("None");
       }

        System.out.print("Average Route Grade: ");
        try {
            System.out.println(routeLog.getAvgGrade().getRepresentation());
        } catch (IllegalArgumentException e){
            System.out.println("None");
        }
    }

    public String getAvgBoulderGrade(){
        try {
            return boulderLog.getAvgGrade().getRepresentation();
        } catch (IllegalArgumentException e){
            return "None";
        }
    }

    public String getAvgRouteGrade(){
        try {
            return routeLog.getAvgGrade().getRepresentation();
        } catch (IllegalArgumentException e){
            return "None";
        }
    }

    public String getClimberID(){
        return climberID;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        if (age < 6) {
            System.out.println("Climber is too young to register.");
        } else {
            this.age = age;
        }
    }

    public double getWeightInPounds(){
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds){
        if (weightInPounds < 0){
            System.out.println("Invalid weight.");
        } else {
            this.weightInPounds = weightInPounds;
        }
    }

    public double getBodyFatPercentage(){
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(double bodyFatPercentage) {
        if (bodyFatPercentage < 0 || bodyFatPercentage > 80) {
            System.out.println("Invalid body fat percentage.");
        } else {
            this.bodyFatPercentage = bodyFatPercentage;
        }
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public int getNumberOfLoggedClimbs(){
        return  boulderLog.getClimbList().size() + routeLog.getClimbList().size();
    }

    public ClimbLog getBoulderLog(){
        return boulderLog;
    }

    public ClimbLog getRouteLog(){
        return routeLog;
    }

    @Override
    public String toString(){
        String s = "";
        s += String.format("%s %s (%s)\n", firstName, lastName, climberID);
        s += String.format("Age: %s\nWeight: %s\n", age, (weightInPounds > 0 ? weightInPounds : "None given"));
        s += String.format("Enrollment Date: %s\n", startDate);
        s += String.format("Number of climbs logged: %s\n", getNumberOfLoggedClimbs());

        return s;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if (!(o instanceof Climber)){
            return false;
        }

        Climber otherClimber = (Climber) o;

        return climberID.equals(otherClimber.climberID);
    }

    @Override
    public int compareTo(Climber otherClimber){
        if (firstName.equals(otherClimber.firstName) && lastName.equals(otherClimber.lastName)){
            return climberID.compareTo(otherClimber.climberID);
        } else if (firstName.compareTo(otherClimber.firstName) == 0){
            return lastName.compareTo(otherClimber.lastName);
        } else {
            return firstName.compareTo(otherClimber.firstName);
        }
    }
}
