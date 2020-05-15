public class Turn extends Action {
    public enum TurnType {LEFT_TURN, RIGHT_TURN, LEFT_ABOUT, RIGHT_ABOUT, LEFT_FLANK, RIGHT_FLANK}

    TurnType turnType;

    public Turn(Location startLocation, Location endLocation, int numBeats, TurnType turnType) {
        super(startLocation, endLocation, numBeats);
        this.turnType = turnType;
    }
}
