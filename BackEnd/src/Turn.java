public class Turn extends Action {
    public enum HowTurn {LEFT_TURN, RIGHT_TURN, LEFT_ABOUT, RIGHT_ABOUT, LEFT_FLANK, RIGHT_FLANK}

    HowTurn howTurn;

    public Turn(Location startLocation, Location endLocation, int numBeats, HowTurn howTurn) {
        super(startLocation, endLocation, numBeats);
        this.howTurn = howTurn;
    }
}
