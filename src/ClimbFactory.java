public class ClimbFactory {


    public static Climb newClimb(ClimbType type, String name, Climb.Face faceType, Grade gradeOfficial, Grade gradeFeel,
                                 boolean sent, int attempts, String colorOrRock, String wallNameOrLocation,
                                 int numberOfPitches){
        if (type.equals(ClimbType.OUTDOOR_ROUTE)){
            return new OutdoorRoute(name, faceType, gradeOfficial, gradeFeel, sent, attempts, colorOrRock,
                    wallNameOrLocation, numberOfPitches);
        } else {
            return newClimb(type, name, faceType, gradeOfficial, gradeFeel, sent, attempts, colorOrRock,
                    wallNameOrLocation);
        }
    }

    public static Climb newClimb(ClimbType type, String name, Climb.Face faceType, Grade gradeOfficial, Grade gradeFeel,
                                 boolean sent, int attempts, String colorOrRock, String wallNameOrLocation){
        Climb c;

        if (type.equals(ClimbType.GYM_BOULDER)){
            c = new GymBoulder(name, faceType, gradeOfficial, gradeFeel, sent, attempts, colorOrRock, wallNameOrLocation);
        } else if (type.equals(ClimbType.GYM_ROUTE)){
            c = new GymRoute(name, faceType, gradeOfficial, gradeFeel, sent, attempts, colorOrRock, wallNameOrLocation);
        } else if (type.equals(ClimbType.OUTDOOR_BOULDER)){
            c = new OutdoorBoulder(name, faceType, gradeOfficial, gradeFeel, sent, attempts, colorOrRock, wallNameOrLocation);
        } else if (type.equals(ClimbType.OUTDOOR_ROUTE)){
            throw new IllegalArgumentException("Cannot create an outdoor route without the number of pitches.");
        } else {
            throw new IllegalArgumentException("Not a valid climb type.");
        }
        return c;
    }

}
