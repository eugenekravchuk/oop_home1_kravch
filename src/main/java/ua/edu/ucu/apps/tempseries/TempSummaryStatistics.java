package ua.edu.ucu.apps.tempseries;

public class TempSummaryStatistics {
  public double avgTemp;
  public double devTemp;
  public double minTemp;
  public double maxTemp;

  public TempSummaryStatistics(double avg, double dev, double min, double max) {
    this.avgTemp = avg;
    this.devTemp = dev;
    this.minTemp = min;
    this.maxTemp = max;
  }
}
