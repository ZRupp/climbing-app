abstract class OutdoorClimb extends Climb{
    private String rockType;
    private String location;
    OutdoorClimb(String name, Face faceType, Grade gradeOfficial, Grade gradeFeel, boolean sent, int attempts,
                 String rockType, String location){
        super(name, faceType, gradeOfficial, gradeFeel, sent, attempts);
        this.rockType = rockType;
        this.location = location;
    }

    public String getRockType(){
        return rockType;
    }

    public void setRockType(String rockType){
        this.rockType = rockType;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if (!(o instanceof OutdoorClimb)){
            return false;
        }

        OutdoorClimb otherClimb = (OutdoorClimb) o;

        return getName().equals(otherClimb.getName())
                && getGradeOfficial().getRepresentation().equals(otherClimb.getGradeOfficial().getRepresentation())
                && rockType.equals(otherClimb.rockType) && location.equals(otherClimb.location);
    }
}
