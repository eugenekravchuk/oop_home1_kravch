package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double ABSOLUTE_ZERO_CELSIUS = -273;
    private double[] temperatureSeries;
    private int size;


    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (temperatureSeries == null) {
            throw new IllegalArgumentException("Temperature series cannot be null");
        }
        if (temperatureSeries.length == 0) {
            this.temperatureSeries = new double[0];
            this.size = 0;
            return;
        }

        for (double temp : temperatureSeries) {
            if (temp < ABSOLUTE_ZERO_CELSIUS) {
                throw new InputMismatchException("Temperature cannot be below -273°C");
            }
        }

        this.temperatureSeries = temperatureSeries.clone();
        this.size = temperatureSeries.length;
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }

        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
        
        double avar = average();
        double sumSquaredDiffs = 0;
        
        for (double temp : temperatureSeries) {
            double diff = temp - avar;
            sumSquaredDiffs += diff * diff;
        }
        
        return Math.sqrt(sumSquaredDiffs / temperatureSeries.length);
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        double minTemp = temperatureSeries[0];
    
        for (double temp : temperatureSeries) {
            if (temp < minTemp) {
                minTemp = temp;
            }
        }
    
        return minTemp;
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }

        double maxTemp = temperatureSeries[0];
        
        for (double temp : temperatureSeries) {
            if (temp < maxTemp) {
                maxTemp = temp;
            }
        }

        return maxTemp;

    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }

        double closest = temperatureSeries[0];

        for (double temp : temperatureSeries) {
            if (Math.abs(temp - 0) < Math.abs(closest - 0)) {
                closest = temp;
            }
        }

        return closest;

    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }

        double closest = temperatureSeries[0];

        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < Math.abs(closest - tempValue)) {
                closest = temp;
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] less = new double[this.temperatureSeries.length];
        int counter = 0;

        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                less[counter] = temp;
                counter++;
            }
        }

        return Arrays.copyOf(less, counter);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] great = new double[this.temperatureSeries.length];
        int counter = 0;

        for (double temp : temperatureSeries) {
            if (temp > tempValue) {
                great[counter] = temp;
                counter++;
            }
        }

        return Arrays.copyOf(great, counter);
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        double[] range = new double[this.temperatureSeries.length];
        int counter = 0;

        for (double temp : temperatureSeries) {
            if (temp > lowerBound && temp < upperBound) {
                range[counter] = temp;
                counter++;
            }
        }

        return Arrays.copyOf(range, counter);
    }

    public void reset() {
        this.temperatureSeries = new double[0];
    }

    public double[] sortTemps() {
        if (temperatureSeries.length == 0) {
            return new double[0];
        }

        double[] sortedTemps = temperatureSeries.clone();

        Arrays.sort(sortedTemps);

        return sortedTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException(
                "Temperature series is empty");
        }

        double averTemp = average();
        double devTemp  = deviation();
        double minTemp = min();
        double maxTemp = max(); 

        return new TempSummaryStatistics(averTemp, 
            devTemp, minTemp, maxTemp);
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < ABSOLUTE_ZERO_CELSIUS) {
                throw new InputMismatchException(
                "Temperature cannot be below -273°C");
            }
        }
        
        if (size + temps.length > temperatureSeries.length) {
            double[] newTemperatureSeries = 
            new double[temperatureSeries.length * 2];
            System.arraycopy(temperatureSeries, 
            0, newTemperatureSeries, 0, size);
            temperatureSeries = newTemperatureSeries;
        }

        for (double temp : temps) {
            temperatureSeries[size] = temp;
            size++;
        }

        return size;
    }
}
