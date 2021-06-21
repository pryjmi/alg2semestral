package ui;

import app.Dashboard;
import utils.TextMessages;
import utils.Utils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class UI {

    public static Dashboard db = new Dashboard();
    public static boolean end = false;
    public static Scanner sc = new Scanner(System.in);

    /**
     * Main - starts program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TextMessages.MainMenu());
        while (!end) {
            run();
        }
    }

    /**
     * Works as main menu
     */
    public static void run() {
        try {
        String[] parts = sc.nextLine().split(" ");
        switch (parts[0]) {
            case "help":
            case "h":
                System.out.println(TextMessages.helpMessage());
                break;
            case "check":
                checkPf(parts);
                break;
            case "timeline":
                checkTimeLine();
                break;
            case "save":
                savePf();
                break;
            case "buy":
                buyAsset();
                break;
            case "sell":
                sellAsset();
                break;
            case "load":
                loadPortfolio();
                break;
            case "home":
                System.out.println(TextMessages.MainMenu());
                break;
            case "exit":
                end = true;
                break;
            default:
                break;
                }
        } catch (Exception e) {
            System.out.println("Chyba");
        }
    }

    /**
     * Saves portfolio if portfolio exists
     */
    public static void savePf() {
        int index = 0;
        System.out.println(TextMessages.buyAsset());
        switch (sc.next()) {
            case "stocks":
                index = 0;
                break;
            case "crypto":
                index = 1;
                break;
        }
        System.out.println(TextMessages.loadPf());
        try {
            db.saveInfo(sc.next(), index);
            System.out.println(TextMessages.savePf());
        } catch (IOException e) {
            System.out.println("Soubor se nepodařilo vytvořit.\n" + e.getMessage());
        }
    }

    /**
     * Creates portfolio if portfolio doesn't exist
     */
    public static void buyAsset() {
        double deposit;
        int period;
        System.out.println(TextMessages.buyAsset());
        switch (sc.next()) {
            case "stocks":
                System.out.println(TextMessages.DepositValue());
                try {
                    deposit = sc.nextDouble();
                    System.out.println(TextMessages.Period());
                    period = sc.nextInt();
                    if (db.buy(0, deposit, period)) {
                        bought(0);
                    } else {
                        System.out.println(TextMessages.pfStocksExists());
                    }
                } catch (InputMismatchException e) {
                    System.out.println("");
                    System.out.println("Neplatný příkaz!");
                    System.out.println("");
                    System.out.println(TextMessages.helpMessage());
                }
                break;
            case "crypto":
                try {
                System.out.println(TextMessages.DepositValue());
                deposit = sc.nextDouble();
                System.out.println(TextMessages.Period());
                period = sc.nextInt();
                if (db.buy(1, deposit, period)) {
                    bought(1);
                } else {
                    System.out.println(TextMessages.pfCryptoExists());
                }
                } catch (InputMismatchException e) {
                    System.out.println("");
                    System.out.println("Neplatný příkaz!");
                    System.out.println("");
                    System.out.println(TextMessages.helpMessage());
                }
                break;
            default:
                break;
        }
    }

    /**
     * Removes portfolio if portfolio exists
     */
    public static void sellAsset() {
        System.out.println(TextMessages.buyAsset());
        switch (sc.next()) {
            case "stocks":
                if (db.sell(0)) {
                    sold(0);
                }
                break;
            case "crypto":
                if (db.sell(1)) {
                    sold(1);
                }
                break;
            default : break;
        }
    }

    /**
     * Loads portfolio if portfolio doesn't exist
     */
    public static void loadPortfolio() {
        System.out.println(TextMessages.loadPf());
        String name = sc.next();
        try {
            boolean occupied = db.loadInfo(name);
            if (occupied) {
                System.out.println(TextMessages.pfExists());
            } else {
                db.loadInfo(name);
            }
        } catch (IOException e) {
            System.out.println("Soubor nebyl nalezen/nepodařilo se načíst.\n" + e.getMessage());
        }
    }

    /**
     * Shows information about portfolio after buying
     * @param index
     */
    public static void bought(int index) {
        int months = 0;
        List<double[]> history = db.getInfoTable(index);
        for (double[] arr : history
        ) {
            months++;
            System.out.print(String.format("Uplynulé měsíce: %d Aktuální hodnota: %f ", months, arr[2]));
            if (arr[5] >= 0) {
                Utils.percentPos(arr[5]);
            } else {
                Utils.percentNeg(arr[5]);
            }
            Utils.Wait();
        }
        System.out.println(" ");
        System.out.println(db.getReport(index));
    }

    /**
     * Shows information about portfolio after selling
     * @param index
     */
    public static void sold(int index) {
        System.out.println(db.getReportSold(index));
    }

    /**
     * Shows information about portfolio if portfolio exists
     * @param parts
     */
    public static void checkPf(String[] parts) {
        if (parts.length >= 2) {
            switch (parts[1]) {
                case "stocks":
                    if (db.checkPortfolios()[0] == true) {
                        System.out.println(db.getReport(0));
                    } else {
                        System.out.println(TextMessages.pfNotFound());
                    }
                    break;
                case "crypto":
                    if (db.checkPortfolios()[1] == true) {
                        System.out.println(db.getReport(1));
                    } else {
                        System.out.println(TextMessages.pfNotFound());
                    }
            }
        }
    }

    /**
     * Shows history of portfolio if portfolio exists and was bought
     */
    public static void checkTimeLine() {
        if (db.checkPortfolios()[0] == false && db.checkPortfolios()[1] == false) {
            System.out.println("Žádná portfolia nebyla založena.");
        } else {
            System.out.println(TextMessages.buyAsset());
            int index = 0;
            switch (sc.next()) {
                case "stocks":
                    index = 0;
                    break;
                case "crypto":
                    index = 1;
                    break;
            }
            System.out.println(TextMessages.checkTimeLine());
            switch (sc.next()) {
                case "chrono":
                    System.out.println(db.timeLineChrono(index));
                    break;
                case "profit":
                    System.out.println(db.timeLineProfit(index));
                    break;
            }
        }
    }
}
