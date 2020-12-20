abstract class GymClimb extends Climb {
    private String color;
    private String wallName;
    GymClimb(String name, Face faceType, Grade gradeOfficial, Grade gradeFeel, boolean sent, int attempts, String color,
             String wallName) {
        super(name, faceType, gradeOfficial, gradeFeel, sent, attempts);
        this.color = color;
        this.wallName = wallName;
    }


    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getWallName(){
        return wallName;
    }

    public void setWallName(String wallName){
        this.wallName = wallName;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if (!(o instanceof GymClimb)){
            return false;
        }

        GymClimb otherBoulder = (GymClimb) o;

        return getName().equals(otherBoulder.getName())
                && getGradeOfficial().getRepresentation().equals(otherBoulder.getGradeOfficial().getRepresentation())
                && color.equals(otherBoulder.color) && wallName.equals(otherBoulder.wallName);
    }

}
