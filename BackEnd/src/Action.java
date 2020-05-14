
public abstract class Action {
    public Action(Location startLocation, Location endLocation, int numBeats) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.numBeats = numBeats;
    }
    Location startLocation;
    Location endLocation;
    int numBeats;
}
