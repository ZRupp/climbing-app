public enum Grade {
    V0("V0"),
    V1("V1"),
    V2("V2"),
    V3("V3"),
    V4("V4"),
    V5("V5"),
    V6("V6"),
    V7("V7"),
    V8("V8"),
    V9("V9"),
    V10("V10"),
    V11("V11"),
    V12("V12"),
    V13("V13"),
    V14("V14"),
    V15("V15"),
    V16("V16"),
    V17("V17"),
    YDS6("5.6"),
    YDS7("5.7"),
    YDS8("5.8"),
    YDS9("5.9"),
    YDS10A("5.10A"),
    YDS10B("5.10B"),
    YDS10C("5.10C"),
    YDS10D("5.10D"),
    YDS11A("5.11A"),
    YDS11B("5.11B"),
    YDS11C("5.11C"),
    YDS11D("5.11D"),
    YDS12A("5.12A"),
    YDS12B("5.12B"),
    YDS12C("5.12C"),
    YDS12D("5.12D"),
    YDS13A("5.13A"),
    YDS13B("5.13B"),
    YDS13C("5.13C"),
    YDS13D("5.13D"),
    YDS14A("5.14A"),
    YDS14B("5.14B"),
    YDS14C("5.14C"),
    YDS14D("5.14D"),
    YDS15A("5.15A"),
    YDS15B("5.15B");

    private String representation;
    private int numericEquivalent; // For calculations

    Grade(String representation){
        this.representation = representation;
        if (representation.charAt(0) == 'V'){
            numericEquivalent = Integer.parseInt(representation.substring(1));
        } else if (representation.length() < 4){
            numericEquivalent = Integer.parseInt(representation.substring(2));
        } else {
            numericEquivalent = Integer.parseInt(representation.substring(2, 4));
            if (numericEquivalent >= 9){
                int intermediateGrades = 4;
                int addend;
                int multiplier = numericEquivalent % 10;

                addend = intermediateGrades * multiplier;

                switch(representation.charAt(4)){
                    case 'A':
                        addend += 1;
                        break;
                    case 'B':
                        addend += 2;
                        break;
                    case 'C':
                        addend += 3;
                        break;
                    case 'D':
                        addend += 4;
                        break;
                }

                numericEquivalent += addend;
            }

        }
    }

    public String getRepresentation(){
        return representation;
    }

    public int getNumericEquivalent(){
        return numericEquivalent;
    }
}
