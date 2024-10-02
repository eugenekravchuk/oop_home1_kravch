package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {
private TemperatureSeriesAnalysis seriesEmpty;
    private TemperatureSeriesAnalysis seriesNormal;
    private static final double DELTA = 0.00001;

    @Before
    public void setUp() {
        seriesEmpty = new TemperatureSeriesAnalysis();
        seriesNormal = new TemperatureSeriesAnalysis(new double[] {3.0, -5.0, 1.0, 5.0});
    }

    @Test
    public void testConstructorEmpty() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        assertEquals(0, series.findTempsLessThen(0).length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNull() {
        new TemperatureSeriesAnalysis(null);
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructorWithInvalidTemperature() {
        new TemperatureSeriesAnalysis(new double[] {-275.0});
    }

    @Test
    public void testAverage() {
        assertEquals(1.0, seriesNormal.average(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmptySeries() {
        seriesEmpty.average();
    }

    @Test
    public void testDeviation() {
        assertEquals(3.7416573867739413, seriesNormal.deviation(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationEmptySeries() {
        seriesEmpty.deviation();
    }

    @Test
    public void testMin() {
        assertEquals(-5.0, seriesNormal.min(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmptySeries() {
        seriesEmpty.min();
    }

    @Test
    public void testMax() {
        assertEquals(5.0, seriesNormal.max(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmptySeries() {
        seriesEmpty.max();
    }

    @Test
    public void testFindTempClosestToZero() {
        assertEquals(1.0, seriesNormal.findTempClosestToZero(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroEmptySeries() {
        seriesEmpty.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToValue() {
        assertEquals(5.0, seriesNormal.findTempClosestToValue(6.0), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueEmptySeries() {
        seriesEmpty.findTempClosestToValue(0.0);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] expected = {-5.0, 1.0};
        double[] actual = seriesNormal.findTempsLessThen(2.0);
        Arrays.sort(actual);
        assertArrayEquals(expected, actual, DELTA);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] expected = {3.0, 5.0};
        double[] actual = seriesNormal.findTempsGreaterThen(2.0);
        Arrays.sort(actual);
        assertArrayEquals(expected, actual, DELTA);
    }

    @Test
    public void testFindTempsInRange() {
        double[] expected = {1.0, 3.0};
        double[] actual = seriesNormal.findTempsInRange(0.0, 4.0);
        Arrays.sort(actual);
        assertArrayEquals(expected, actual, DELTA);
    }

    @Test
    public void testAddTemps() {
        TemperatureSeriesAnalysis series = new TemperatureSeriesAnalysis();
        assertEquals(2, series.addTemps(1.0, 2.0));
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsInvalid() {
        seriesNormal.addTemps(-274.0);
    }

    @Test
    public void testSortTemps() {
        double[] expected = {-5.0, 1.0, 3.0, 5.0};
        assertArrayEquals(expected, seriesNormal.sortTemps(), DELTA);
    }

    @Test
    public void testSummaryStatistics() {
        TempSummaryStatistics stats = seriesNormal.summaryStatistics();
        assertEquals(1.0, stats.getAvgTemp(), DELTA);
        assertEquals(3.7416573867739413, stats.getDevTemp(), DELTA);
        assertEquals(-5.0, stats.getMinTemp(), DELTA);
        assertEquals(5.0, stats.getMaxTemp(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsEmptySeries() {
        seriesEmpty.summaryStatistics();
    }

    @Test
    public void testReset() {
        seriesNormal.reset();
        assertEquals(0, seriesNormal.findTempsLessThen(0).length);
    }
}
