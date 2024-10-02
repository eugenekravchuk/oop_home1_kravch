package ua.edu.ucu.apps.tempseries;

public class TempSummaryStatistics {
  private double avgTemp;
  private double devTemp;
  private double minTemp;
  private double maxTemp;

  public TempSummaryStatistics(double avg, double dev, double min, double max) {
    this.avgTemp = avg;
    this.devTemp = dev;
    this.minTemp = min;
    this.maxTemp = max;
  }

  public double getAvgTemp(){
    return this.avgTemp;
  }
  
  public double getDevTemp(){
    return this.devTemp;
  }

  public double getMinTemp(){
    return this.minTemp;
  }

  public double getMaxTemp(){
    return this.maxTemp;
  }

  @Override
  public String toString() {
      return String.format(
      "TempSummaryStatistics { avgTemp=%.2f,"
      +"devTemp=%.2f, minTemp=%.2f, maxTemp=%.2f }",
      avgTemp, devTemp, minTemp, maxTemp);
  }
}
