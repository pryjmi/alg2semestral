package utils;

import java.io.IOException;
import java.util.List;

public interface PortfolioInterface {

    /**
     * Returns portfolios name
     * @param index
     * @return portfolio name
     */
    public String getName(int index);

    /**
     * Returns list with data from portfolio
     * @param index
     * @return list with data from portfolios
     */
    public List<double[]> getInfoTable(int index);

    /**
     * Saves data from portfolio to file
     * @param fileName
     * @param index
     * @throws IOException
     */
    public void saveInfo(String fileName, int index) throws IOException;

    /**
     * Checks if portfolio exists, creates new portfolio instance
     * @param name
     * @param deposit
     * @param months
     * @return true if portfolio doesn't exist
     */
    public boolean buy(int name, double deposit, int months);

    /**
     * Checks if portfolio exists
     * @param index
     * @return true if portfolio exists
     */
    public boolean sell(int index); //true = probehlo, false = portfolio neexistuje

    /**
     * Checks if portfolio exists, loads form file
     * @param fileName
     * @return true if portfolio doesn't exist
     * @throws IOException
     */
    public boolean loadInfo(String fileName) throws IOException;

    /**
     * Checks if portfolio exists
     * @return true if portfolio exists
     */
    public boolean[] checkPortfolios();

    /**
     * Returns chronologically sorted portfolio history
     * @param index
     * @return list with portfolio history
     */
    public String timeLineChrono (int index);

    /**
     * Returns portfolio history sorted from highest to lowest profit/loss
     * @param index
     * @return list with portfolio history
     */
    public String timeLineProfit (int index);

    /**
     * Returns portfolio report
     * @param index
     * @return information about bought or loaded portfolio
     */
    public String getReport (int index);

    /**
     * Returns sold portfolio report
     * @param index
     * @return information about sold portfolio
     */
    public String getReportSold (int index);
}
