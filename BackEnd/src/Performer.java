import java.util.ArrayList;


public class Performer {

    boolean cacheFresh = false;
    ArrayList<Action> actions = new ArrayList<Action>();
    Location currentLocation;
    ArrayList<Location> locationCache = new ArrayList<Location>();

    //TODO add sanity checks to the getLocations
    public Location getLocation(int beat) {
        if (!cacheFresh) //hopefully it's fresh
            freshenCache();
        return locationCache.get(beat + 1);
    }

    public Location getLocation(double beat) {
        if (!cacheFresh) //hopefully it's fresh
            freshenCache();
        int flooredBeat = (int) Math.floor(beat);
        Location startLocation = locationCache.get(flooredBeat);
        Location endLocation = locationCache.get(flooredBeat + 1);
        double xVal = Interpolator.interpolate(startLocation.yardsFromFifty,
                endLocation.yardsFromFifty, 1, beat);
        double yVal = Interpolator.interpolate(startLocation.yardsFromCenter,
                endLocation.yardsFromCenter, 1, beat);
        return new Location(xVal, yVal);
    }

    public Performer(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Performer(ArrayList<Action> actions, Location currentLocation) {
        this.actions = actions;
        this.currentLocation = currentLocation;
    }

    public void freshenCache() {
        if (!cacheFresh) {
            for (Action action : actions) {
                if (action.startLocation.equals(action.endLocation)) { //stays in same spot through the action
                    for (int i = 0; i < action.numBeats; i++) //add the same location for each beat
                        locationCache.add(action.startLocation);
                } else //great. time for interpolation. Goodie goodie gumdrop.
                {
                    Interpolator xInterpolator = new Interpolator(action.startLocation.yardsFromCenter,
                            action.endLocation.yardsFromCenter, action.numBeats);
                    Interpolator yInterpolator = new Interpolator(action.startLocation.yardsFromFifty,
                            action.endLocation.yardsFromFifty, action.numBeats);
                    for (int i = 0; i < action.numBeats - 1; i++) //add interpolated for each except last
                        locationCache.add(new Location(xInterpolator.getNext(), yInterpolator.getNext()));
                    locationCache.add(action.endLocation);  //why interpolate for the last value, it's slower &
                    //introduces error
                }
            }
        }
        cacheFresh = true;
    }
}
