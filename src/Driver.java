import java.util.ArrayList;
import java.util.Collections;

public class Driver {

    public static void main(String[] args) {
        ArrayList<Climber> climbers = new ArrayList<>();

        // M3 USING BUILDER
        Climber climber1 = new Climber.ClimberBuilder("Dude", "Guy", 29).build();
        Climber climber2 = new Climber.ClimberBuilder("Mister", "Monster", 999)
                .weightInPounds(250)
                .bodyFatPercentage(56.5)
                .build();
        Climber climber3 = new Climber.ClimberBuilder("Aloe", "Vera", 32).build();
        Climber climber4 = new Climber.ClimberBuilder("Edric", "Garcia", 28).build();


        climbers.add(climber1);
        climbers.add(climber2);
        climbers.add(climber3);
        climbers.add(climber4);
        Collections.sort(climbers);

        // M3 USING FACTORY
        Climb climb1 = ClimbFactory.newClimb(ClimbType.GYM_BOULDER, "Head Crusher", Climb.Face.SLAB,
                Grade.V2, Grade.V2, false, 3, "Blue", "Slab Wall");

        Climb climb2 = ClimbFactory.newClimb(ClimbType.OUTDOOR_BOULDER, "Mind the Crack",
                Climb.Face.OVERHANG, Grade.V10, Grade.V10, false, 12, "granite", "Bishop");

        Climb climb3 = ClimbFactory.newClimb(ClimbType.GYM_ROUTE, "FingerBreaker", Climb.Face.VERT,
                Grade.YDS10A, Grade.YDS10A, true, 1, "yellow", "Vert Wall");

        Climb climb4 = ClimbFactory.newClimb(ClimbType.OUTDOOR_ROUTE, "OingoBoingo",
                Climb.Face.OVERHANG, Grade.YDS13C, Grade.YDS13A, false, 12, "Sandstone", "Berkeley", 1);

        ArrayList<Climb> climbs = new ArrayList<>();
        climbs.add(climb1);
        climbs.add(climb2);
        climbs.add(climb3);
        climbs.add(climb4);
        System.out.println("Registered Climbers: ");
        System.out.println(climbers);

        System.out.println("Registered Climbs: ");
        System.out.println(climbs);
        climbers.get(0).logClimb(climbs.get(0));
        climbers.get(0).logClimb(climbs.get(1));
        climbers.get(1).logClimb(climbs.get(2));
        climbers.get(1).logClimb(climbs.get(3));

        System.out.println(climbers.get(0));
        climbers.get(0).printClimbs();

        System.out.println(climbers.get(1));
        climbers.get(1).printClimbs();

        System.out.println();
        System.out.printf("CALCULATING %s'S AVERAGE GRADES\n", climbers.get(0).getFirstName().toUpperCase());
        System.out.printf("Climber: %s %s\n", climbers.get(0).getFirstName(), climbers.get(0).getLastName());
        climbers.get(0).printAvgGrades();
        System.out.println();

        System.out.printf("CALCULATING %s'S AVERAGE GRADES\n", climbers.get(1).getFirstName().toUpperCase());
        System.out.printf("Climber: %s %s\n", climbers.get(1).getFirstName(), climbers.get(1).getLastName());
        climbers.get(1).printAvgGrades();
        System.out.println();

        System.out.printf("Number of climbers: %s\n", Climber.getNumOfClimbers());

        System.out.println(climbers);
        System.out.println("Sorting by number of climbs: ");

        // This sorts them in ascending order, but how do I sort in descending order?
        Collections.sort(climbers, Climber.CLIMB_COUNT_COMPARATOR); // M3 USING COMPARATOR
        System.out.println(climbers);

    }
}
