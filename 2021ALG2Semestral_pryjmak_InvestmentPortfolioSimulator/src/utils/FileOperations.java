package utils;

import app.Crypto;
import app.Stocks;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileOperations {

    /**
     * Menu, gives choice of file format to load portfolio from
     * @param fileName
     * @return calls method for specific file format
     * @throws IOException
     */
    public static Portfolio loadFile(String fileName) throws IOException {
        if (fileName.endsWith(".txt")) {
            return loadTxt(fileName);
        } else if (fileName.endsWith(".csv")) {
            return loadCsv(fileName);
        } else if (fileName.endsWith(".pdf")) {
            return loadPDF(fileName);
        } else {
            return loadBin(fileName);
        }
    }

    /**
     * Loads portfolio from .txt if portfolio doesn't exist
     * @param fileName
     * @return creates new portfolio
     * @throws IOException
     */
    private static Portfolio loadTxt(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            String name = line.split(" ")[0];
            br.readLine();
            line = br.readLine().trim().replaceAll(" +", " ").replaceAll(",", ".");
            String[] parts = line.split(" ");
            switch (name) {
                case "KRYPTOMĚNA":
                    Portfolio crypto = new Crypto(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                    return crypto;
                case "AKCIE":
                    Portfolio stock = new Stocks(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                    return stock;
            }
            return null;
        }
    }

    /**
     * Loads portfolio from .csv if portfolio doesn't exist
     * @param fileName
     * @return creates new portfolio
     * @throws IOException
     */
    private static Portfolio loadCsv(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            String name = line.split(";")[0];
            br.readLine();
            line = br.readLine().trim().replaceAll(" +", " ").replaceAll(",", ".");
            String[] parts = line.split(";");
            switch (name) {
                case "KRYPTOMĚNA":
                    Portfolio crypto = new Crypto(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                    return crypto;
                case "AKCIE":
                    Portfolio stock = new Stocks(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                    return stock;
            }
            return null;
        }
    }

    /**
     * Loads portfolio from .pdf if portfolio doesn't exist
     * @param fileName
     * @return creates new portfolio
     * @throws IOException
     */
    private static Portfolio loadPDF(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        PDDocument d = PDDocument.load(f);
        PDFTextStripper ts = new PDFTextStripper();
        String text = ts.getText(d);
        String[] parts = text.split("\n");
        String name = parts[0].split(" ")[0];
        String[] data = parts[2].replaceAll(",",".").split(" \\| ");
        d.close();
        switch (name) {
            case "AKCIE":
                Portfolio stock = new Stocks(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]));
                return stock;
            case "KRYPTOMĚNA":
                Portfolio crypto = new Crypto(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]));
                return crypto;
        }
        return null;
    }

    /**
     * Loads portfolio from binary file if portfolio doesn't exist
     * @param fileName
     * @return creates new portfolio
     * @throws IOException
     */
    private static Portfolio loadBin(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (DataInputStream bin = new DataInputStream(new FileInputStream(f))) {
            String name = bin.readUTF();
            bin.readInt();
            bin.readInt();
            bin.readInt();
            bin.readLong();
            switch (name) {
                case "KRYPTOMĚNA":
                    return new Crypto(bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readInt(), bin.readDouble(), bin.readDouble());
                case "AKCIE":
                    return new Stocks(bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readInt(), bin.readDouble(), bin.readDouble());
            }
            return null;
        }
    }

    /**
     * Menu, gives choice of file format to save portfolio to
     * @param fileName
     * @return calls method for specific file format
     * @throws IOException
     */
    public static void saveInfo(String fileName, Portfolio port) throws IOException {
        switch (fileName.substring(fileName.length() - 4)) {
            case ".txt":
                saveTxt(fileName, port); break;
            case ".csv":
                saveCsv(fileName, port); break;
            case ".pdf":
                savePDF(fileName, port); break;
            default:
                saveBin(fileName, port); break;
        }
    }

    /**
     * Saves portfolio to .txt if portfolio exists
     * @param fileName
     * @throws IOException
     */
    private static void saveTxt(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        f.createNewFile();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            pw.println(port.getName() + " " + LocalTime.now().toString() + " " + LocalDate.now().toString());
            pw.println(String.format("%15s %15s %15s %15s %15s %15s %7s %10s %4s", "Akt. Hodnota Aktiva", "Akt. Počet Aktiv", "Akt. Hod. Investice", "Počát. Hod. Aktiva", "Počát. Počet Aktiv", "Počát. Hod. Investice", "Měsíc", "Zisk/Ztráta", "%"));
            pw.println(String.format("%15f %15f %15f %15f %15f %15f %7d %10f %4f", port.getActValue(), port.getActQuantity(), port.getActSum(), port.getStartValue(), port.getStartQuantity(), port.getStartDeposit(), port.getMonth(), port.getTimelineProfit(), port.getTimelineProfitPercent()));
        }
    }

    /**
     * Saves portfolio to .csv if portfolio exists
     * @param fileName
     * @throws IOException
     */
    private static void saveCsv(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            pw.println(port.getName() + ";" + LocalTime.now().toString() + ";" + LocalDate.now().toString());
            pw.println("Akt. Hodnota Aktiva;Akt. Počet Aktiv;Akt. Hod. Investice;Počát. Hod. Aktiva;Počát. Počet Aktiv;Počát. Hod. Investice;Měsíc;Zisk/Ztráta;%");
            pw.println(String.format("%f;%f;%f;%f;%f;%f;%d;%f;%f", port.getActValue(), port.getActQuantity(), port.getActSum(), port.getStartValue(), port.getStartQuantity(), port.getStartDeposit(), port.getMonth(), port.getTimelineProfit(), port.getTimelineProfitPercent()));
        }
    }

    /**
     * Saves portfolio to .pdf if portfolio exists
     * @param fileName
     * @throws IOException
     */
    private static void savePDF(String fileName, Portfolio port) throws IOException {
        String head = (port.getName() + " | " + LocalTime.now().toString() + " | " + LocalDate.now().toString());
        String legend = ("Akt. Hodnota Aktiva | Akt. Počet Aktiv | Akt. Hod. Investice | Počát. Hod. Aktiva | Počát. Počet Aktiv | Počát. Hod. Investice | Měsíc(e) | Zisk/Ztráta | %");
        String data = (String.format("%f | %f | %f | %.0f | %f | %f | %d | %f | %f", port.getActValue(), port.getActQuantity(), port.getActSum(), port.getStartValue(), port.getStartQuantity(), port.getStartDeposit(), port.getMonth(), port.getTimelineProfit(), port.getTimelineProfitPercent()));
        PDDocument d = new PDDocument();
        try {
            PDPage p = new PDPage();
            d.addPage(p);
            PDPageContentStream stream = new PDPageContentStream(d, p);
            PDFont formFont = PDType0Font.load(d, new FileInputStream(System.getProperty("user.dir") + File.separator + "Data" + File.separator + "ARIALUNI.TTF"));
            stream.setFont(formFont, 8);
            stream.beginText();
            stream.setLeading(14.5f);
            stream.moveTextPositionByAmount(5, 780);
            stream.drawString(head);
            stream.newLine();
            stream.newLine();
            stream.drawString(legend);
            stream.newLine();
            stream.drawString(data);
            stream.endText();
            stream.close();
            d.save(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        } finally {
            d.close();
        }
    }

    /**
     * Saves portfolio to binary file if portfolio exists
     * @param fileName
     * @throws IOException
     */
    private static void saveBin(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(f))) {
            out.writeUTF(port.getName());
            out.writeLong(LocalTime.now().toSecondOfDay());
            out.writeInt(LocalDate.now().getDayOfMonth());
            out.writeInt(LocalDate.now().getMonthValue());
            out.writeInt(LocalDate.now().getYear());
            out.writeDouble(port.getActValue());
            out.writeDouble(port.getActQuantity());
            out.writeDouble(port.getActSum());
            out.writeDouble(port.getStartValue());
            out.writeDouble(port.getStartQuantity());
            out.writeDouble(port.getStartDeposit());
            out.writeInt(port.getMonth());
            out.writeDouble(port.getTimelineProfit());
            out.writeDouble(port.getTimelineProfitPercent());

        }
    }
}

