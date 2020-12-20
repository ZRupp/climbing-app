abstract class Climb {
    enum Face {OVERHANG, SLAB, VERT};

    // enum Feature {CRACK, CRIMP, EDGE, FLAKE, HORN, JUG, PINCH, SLOPER, POCKET, UNDERCLING};

    private String name;
    private Face faceType;
    private Grade gradeOfficial;
    private Grade gradeFeel;
    private boolean sent;
    private int attempts = 0;




    public Climb(String name, Face faceType, Grade gradeOfficial, Grade gradeFeel, boolean sent, int attempts){
        this.name = name;
        this.faceType = faceType;
        this.gradeOfficial = gradeOfficial;
        this.gradeFeel = gradeFeel;
        this.sent = sent;
        this.attempts = attempts;
    }

    public boolean isFlashed(){
        return getAttempts() == 1 && getSent();
    }

    public void logAttempts(int newAttempts){
        if (newAttempts <= 0){
            System.out.println("Invalid number of attempts to log.");
        } else {
            attempts += newAttempts;
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Face getFaceType(){
        return faceType;
    }

    public void setFaceType(Face faceType){
        this.faceType = faceType;
    }

    public boolean getSent(){
        return sent;
    }

    public void setSent(boolean sent){
        this.sent = sent;
    }

    public Grade getGradeOfficial(){
        return gradeOfficial;
    }

    public void setGradeOfficial(Grade gradeOfficial){
        this.gradeOfficial = gradeOfficial;
    }

    public Grade getGradeFeel(){
        return gradeFeel;
    }

    public void setGradeFeel(Grade gradeFeel){
        this.gradeFeel = gradeFeel;
    }

    public int getAttempts(){
        return attempts;
    }

    @Override
    public String toString(){
        {
            String s = "";
            s += String.format("Name: %s\tOfficial Grade: %s\t\tYour Grade: %s\n", getName(),
                    getGradeOfficial().getRepresentation(), getGradeFeel().getRepresentation());
            s += String.format("Style: %s\t\t\tFlashed: %s\t\t\tAttempts: %s\n", getFaceType(), isFlashed(), getAttempts());
            return s;
        }
    }

    @Override
    public abstract boolean equals(Object otherClimb);
}
