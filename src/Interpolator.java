public class Interpolator {
    double start;
    double end;
    double current;
    int numSteps;
    double currentStep;
    private double distancePerStep;

    public double getCurrent() {
        return current;
    }

    public Interpolator(double start, double end, int numSteps) {
        this.start = start;
        this.end = end;
        this.numSteps = numSteps;
        currentStep = 0;
        double distance = end - start;
        distancePerStep = distance / numSteps;
    }

    private void doStep() {
        currentStep += distancePerStep;
    }

    public double getNext() {
        doStep();
        return currentStep;
    }

    public static double interpolate(double start, double end, double distance, double partialDistance) {
        double dif = end - start;
        double slope = dif / distance;
        return slope * partialDistance;
    }
}
