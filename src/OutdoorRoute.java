public class OutdoorRoute extends OutdoorClimb {

    private int numberOfPitches;

    OutdoorRoute(String name, Face faceType, Grade gradeOfficial, Grade gradeFeel, boolean sent, int attempts,
                 String rockType, String location, int numberOfPitches){
        super(name, faceType, gradeOfficial, gradeFeel, sent, attempts, rockType, location);
        this.numberOfPitches = numberOfPitches;
    }

    public int getNumberOfPitches(){
        return numberOfPitches;
    }

}
