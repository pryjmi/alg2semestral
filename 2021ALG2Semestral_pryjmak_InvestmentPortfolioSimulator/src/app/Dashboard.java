package app;

import utils.FileOperations;
import utils.Portfolio;
import utils.PortfolioInterface;

import java.io.IOException;
import java.util.List;

public class Dashboard implements PortfolioInterface {

    public Portfolio stock; //0

    private Portfolio crypto; //1

    /**
     * Returns portfolios name
     * @param index
     * @return portfolio name
     */
    @Override
    public String getName(int index) {
        if (index == 0){
            return stock.getName();
        } else if (index == 1){
            return crypto.getName();
        }
        return null;
    }

    /**
     * Returns list with data from portfolio
     * @param index
     * @return list with data from portfolios
     */
    @Override
    public List<double[]> getInfoTable(int index) {
        if (index == 0) {
            return stock.getInfoTable();
        } else if (index == 1) {
            return crypto.getInfoTable();
        }
        return null;
    }

    /**
     * Saves data from portfolio to file
     * @param fileName
     * @param index
     * @throws IOException
     */
    @Override
    public void saveInfo(String fileName, int index) throws IOException {
        switch (index) {
            case 0: FileOperations.saveInfo(fileName, stock); break;
            case 1: FileOperations.saveInfo(fileName, crypto); break;
        }
    }

    /**
     * Checks if portfolio exists, creates new portfolio instance
     * @param name
     * @param deposit
     * @param months
     * @return true if portfolio doesn't exist
     */
    @Override
    public boolean buy(int name, double deposit, int months) {
        switch (name){
            case 0: //stocks
                if (stock == null){
                    stock = new Stocks(deposit, months); return true;
                } else return false;
            case 1: //crypto
                if (crypto == null){
                    crypto = new Crypto(deposit, months); return true;
                } else return false;
            default : break;
        }
        return false;
    }

    /**
     * Checks if portfolio exists
     * @param index
     * @return true if portfolio exists
     */
    @Override
    public boolean sell(int index) {
        switch (index) {
            case 0: if (stock != null){
                    return true;
                } else {
                    return false;
                }
            case 1: if (crypto != null){
                    return true;
                } else {
                    return false;
                }
            }
        return false;
    }

    /**
     * Checks if portfolio exists, loads form file
     * @param fileName
     * @return true if portfolio doesn't exist
     * @throws IOException
     */
    @Override
    public boolean loadInfo(String fileName) throws IOException {
        utils.Portfolio port = FileOperations.loadFile(fileName);
        if (port.getName().equals("AKCIE")) {
            if (stock == null) {
                stock = port;
                return true;
            } else return false;
        } else if (port.getName().equals("KRYPTOMĚNA")) {
            if (crypto == null) {
                crypto = port;
                return true;
            } else return false;
        }
        return false;
    }

    /**
     * Checks if portfolio exists
     * @return true if portfolio exists
     */
    @Override
    public boolean[] checkPortfolios() {
        boolean[] check = {false, false};
        if (stock != null) {
            check[0] = true;
        }
        if (crypto != null) {
            check[1] = true;
        }
        return check;
    }

    /**
     * Returns chronologically sorted portfolio history
     * @param index
     * @return list with portfolio history
     */
    @Override
    public String timeLineChrono(int index) {
            switch (index){
                case 0: if (stock != null) {
                    return stock.timeLineChrono();
                } else {
                    return "Portfolio nebylo založeno.";
                }
                case 1:if (crypto != null) {
                    return crypto.timeLineChrono();
                } else {
                    return "Portfolio nebylo založeno.";
                }
            }
            return null;
    }

    /**
     * Returns portfolio history sorted from highest to lowest profit/loss
     * @param index
     * @return list with portfolio history
     */
    @Override
    public String timeLineProfit(int index) {
        switch (index){
            case 0: if (stock != null) {
                return stock.timeLineProfit();
            } else {
                return "Portfolio nebylo založeno.";
            }
            case 1:if (crypto != null) {
                return crypto.timeLineProfit();
            } else {
                return "Portfolio nebylo založeno.";
            }
        }
        return null;
    }

    /**
     * Returns portfolio report
     * @param index
     * @return information about bought or loaded portfolio
     */
    @Override
    public String getReport(int index) {
        switch (index){
            case 0: return stock.getReport();
            case 1: return crypto.getReport();
        }
        return null;
    }

    /**
     * Returns sold portfolio report
     * @param index
     * @return information about sold portfolio
     */
    @Override
    public String getReportSold(int index) {
        double sum = 0;
        switch (index){
            case 0: sum = stock.getActSum();
                stock = null;
                break;
            case 1: sum = crypto.getActSum();
                crypto = null;
                break;
        }
        return String.format("Portfolio bylo prodáno, bilance: $ %f", sum);
    }


}
