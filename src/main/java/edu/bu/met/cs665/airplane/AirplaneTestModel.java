package edu.bu.met.cs665.airplane;

public class AirplaneTestModel implements Airplane {
    private final double fuelConsumption;

    public AirplaneTestModel(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getRange() {
        return 0;
    }

    @Override
    public double getFuelCapacity() {
        return 0;
    }

    @Override
    public double getFuelBurnRate() {
        return 0;
    }

    @Override
    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    @Override
    public double getSpeed() {
        return 0;
    }
}
