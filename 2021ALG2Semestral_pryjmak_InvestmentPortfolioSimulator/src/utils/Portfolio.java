package utils;

import java.util.List;

public abstract class Portfolio {

    /**
     * Getter
     * @return actValue
     */
    public abstract double getActValue ();

    /**
     * Getter
     * @return actQuantity
     */
    public abstract double getActQuantity ();

    /**
     * Getter
     * @return actSum
     */
    public abstract double getActSum ();

    /**
     * Getter
     * @return startValue
     */
    public abstract double getStartValue ();

    /**
     * Getter
     * @return startQuantity
     */
    public abstract double getStartQuantity ();

    /**
     * Getter
     * @return startDeposit
     */
    public abstract double getStartDeposit ();

    /**
     * Getter
     * @return timeLineProfit
     */
    public abstract double getTimelineProfit();

    /**
     * Getter
     * @return timeLineProfitPercent
     */
    public abstract double getTimelineProfitPercent();

    /**
     *Getter
     * @return month
     */
    public abstract int getMonth ();

    /**
     * Getter
     * @return name
     */
    public abstract String getName();

    /**
     * Getter
     * @return infoTable
     */
    public abstract List<double[]> getInfoTable();

    /**
     * Returns chronologically sorted portfolio history
     * @return list with portfolio history
     */
    public abstract String timeLineChrono ();

    /**
     * Returns portfolio history sorted from highest to lowest profit/loss
     * @return list with portfolio history
     */
    public abstract String timeLineProfit ();

    /**
     * Returns portfolio report
     * @return list with portfolio information
     */
    public abstract String getReport ();

    /**
     * Generates random percent in interval
     * @return random number
     */
    protected abstract double getRandomPercent();

    /**
     * Generates random asset value in interval
     * @return random number
     */
    protected abstract double generateStartValue();

    /**
     * Calculates asset quantity
     * @return asset quantity number
     */
    protected abstract double generateStartQuantity();

    /**
     * Assigns startQuantity to actQuantity
     * @return asset quantity number
     */
    protected abstract double generateActQuantity();

    /**
     * Calculates profit percentage every month
     * @return profit percentage
     */
    protected abstract double generateTimelineProfitPercent();

    /**
     * Calculates profit percentage overall
     * @return profit percentage
     */
    protected abstract double generateProfitPercent();

    /**
     * Creates list with data from created portfolio
     * @return list with portfolio information
     */
    protected abstract List<double[]> loadInfoTable();

    /**
     * Creates list with data from loaded portfolio
     * @return list with portfolio information
     */
    protected abstract List<double[]> loadInfoTableFile();
}
