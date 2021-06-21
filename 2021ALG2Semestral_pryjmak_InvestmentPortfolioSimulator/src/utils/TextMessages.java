package utils;

public class TextMessages {

    /**
     * Prints help menu
     * @return help message
     */
    public static String helpMessage(){
        return "Pro seznam příkazů napište 'help' nebo 'h'\n"+
                "Příkaz 'save' uloží portfolio do souboru\n"+
                "Příkaz 'check stocks' slouží ke kontrole akcií\n"+
                "Příkaz 'check crypto' slouží ke kontrole kryptoměn\n"+
                "Příkaz 'timeline' slouží k seřazení historie investice\n"+
                "Příkaz 'buy' slouží k nákupu aktiv v portfoliu\n"+
                "Příkaz 'sell' slouží k prodeji aktiv v portfoliu\n"+
                "Příkaz 'load' slouží k načtení prtofolia ze souboru\n"+
                "Příkaz 'home' vás vrátí na domovskou obrazovku\n"+
                "Příkaz 'exit' ukončí program\n";
    }

    /**
     * Prints main menu
     * @return main menu message
     */
    public static String MainMenu(){
        return "Pro seznam příkazů napište 'help' nebo 'h'";
    }

    /**
     * Prints asset choice menu
     * @return asset choice message
     */
    public static String buyAsset(){
        return "Vyberte aktivum\n"+
        "Akcie 'stocks'\n"+
        "Kryptoměny 'crypto'";
    }

    /**
     * Asks for deposit input
     * @return input deposit message
     */
    public static String DepositValue(){
        return "Zadejte hodnotu vkladu.";
    }

    /**
     * Asks for time period input
     * @return input time period message
     */
    public static String Period(){
        return "Zadejte dobu (pouze měsíce), po kterou bude vklad uschován.";
    }

    /**
     * Informs about save
     * @return saved portfolio message
     */
    public static String savePf(){
        return "Portfolio bylo uloženo\n";
    }

    /**
     * Prints error message - portfolio is not found
     * @return portfolio not found message
     */
    public static String pfNotFound(){
        return "Portfolio nebylo založeno/načteno.";
    }

    /**
     * Prints message - stocks portfolio exists
     * @return stocks portfolio exists message
     */
    public static String pfStocksExists(){
        return "Akciové portfolio je již založeno. Nákup není možný.";
    }

    /**
     * Prints message - cryptocurrency portfolio exists
     * @return crypto portfolio exists message
     */
    public static String pfCryptoExists(){
        return "Kryptoměnové portfolio je již založeno. Nákup není možný.";
    }

    /**
     * Asks for file name to input
     * @return file name load message
     */
    public static String loadPf(){
        return "Zadejte název souboru i s koncovkou, pokud není binární (.txt, .csv, .pdf)";
    }

    /**
     * Prints message - portfolio exists
     * @return portfolio exists message
     */
    public static String pfExists(){
        return "Portfolio již existuje.";
    }

    /**
     * Prints types of comparison menu
     * @return timeline choice message
     */
    public static String checkTimeLine() {
        return "Vyberte typ výpisu: \n" +
                "Chronologický 'chrono' \n" +
                "Dle zisku 'profit'";

    }
}
