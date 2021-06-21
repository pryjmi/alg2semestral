# 2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator
2021 ALG2 LS Semestral project

### Zadání
Zapište program z finančního prostředí simulující investice do aktiv typu akcie a kryptoměna. \
Současně mohou být založena pouze dvě investiční portfolia, jedno pro kryptoměny, druhé pro akcie. Portfolio může být založeno ručně nákupem určitého množství aktiv, nebo načtením ze souboru. Pro nakoupená aktiva je nasimulován vývoj jejich hodnot v uživatelem zadaném období s krokem jeden měsíc. \
Program umožňuje zpětné zobrazení vývoje hodnot portfolia, buď v chronologickém pořadí dle uplynulých měsíců, nebo po měsících dle nejvyššího zisku. Uživateli je umožněno uložení informací o portfoliu do souborů typu .txt, .csv, .pdf, nebo binárního souboru. \
Po prodeji portfolia je vypsána výsledná bilance (zisk) a portfolio automaticky zaniká. Následně je umožněno vytvořit/načíst nové portfolio. \
Pro komunikaci s uživatelem je použito rozhraní příkazové řádky s využitím menu.

### Návrh řešení
#### Funkční specifikace 

1. Nákup kryptoměny/akcií  
➔ **Automatické vytvoření portfolia**  
➔ **Automatická simulace vývoje hodnot pro zadané časové období**  
2. Načtení portfolia (kryptoměna/akcie) ze souboru  
2.1 **Načtení portfolia z textového souboru (.txt)**   
2.2 **Načtení portfolia ze souboru .csv**  
2.3 **Načtení portfolia z .pdf souboru**  
2.4 **Načtení portfolia z binárního souboru**   
3. **Kontrola stavu portfolií**
4. Zobrazení historie vývoje hodnot portfolia  
4.1 **Zobrazení chronologické historie vývoje hodnot**  
4.2 **Zobrazení vývoje hodnot řazených dle zisku**  
5. Uložení informací o portfoliu  
5.1 **Uložení informací o portfoliu do textového souboru**  
5.2 **Uložení informací o portfoliu do .csv souboru**  
5.3 **Uložení informací o portfoliu do .pdf souboru**  
5.4 **Uložení informací o portfoliu do binárního souboru**    
6. Prodej portfolia  
➔ **Automatické odstranění portfolia, výpis finální bilance**  

#### Struktura vstupních a výstupních souborů  
Program nabízí výstupy ve formátech .txt, .csv, .pdf, nebo v binárních souborech.  
##### Formátovaný textový soubor a PDF soubor: 
První řádek obsahuje název portfolia ("KRYPTOMĚNA"/"AKCIE"), čas a datum uložení informací o portfoliu. Na druhém řádku je formátovaná hlavička k výstupním datům na třetím řádku. 
        
    AKCIE 09:14:41.675 2021-06-20
    Akt. Hodnota Aktiva Akt. Počet Aktiv Akt. Hod. Investice Počát. Hod. Aktiva Počát. Počet Aktiv Počát. Hod. Investice   Měsíc Zisk/Ztráta    %
               4,846416        24,104160          116,818781           4,148662          24,104160            100,000000       3    -9,029262   -7,174734

##### .csv soubor:  
První řádek obsahuje název portfolia ("KRYPTOMĚNA"/"AKCIE"), čas a datum uložení informací o portfoliu. Na druhém řádku je hlavička k výstupním datům na třetím řádku.    

    KRYPTOMĚNA;10:26:59.257;2021-06-20
    Akt. Hodnota Aktiva;Akt. Počet Aktiv;Akt. Hod. Investice;Počát. Hod. Aktiva;Počát. Počet Aktiv;Počát. Hod. Investice;Měsíc;Zisk/Ztráta;%
    23,765938;25,183375;598,506547;3,970874;25,183375;100,000000;4;498,506547;498,506547

##### .bin soubor: 
Název portfolia ("KRYPTOMĚNA"/"AKCIE") (utf), čas uložení portfolia v sekundách od počátku dne (long), (datum uložení) den v měsíci (integer), (datum uložení) měsíc v roce (integer), (datum uložení) rok (integer), aktuální hodnota aktiva (double), aktuální počet aktiv (double), aktuální hodnota investice (double), počáteční hodnota aktiva (double), počáteční počet aktiv (double), počáteční hodnota investice (double), délka období v měsících (integer), zisk/ztráta (double), procentuální bilance (double)  

**Z každého výstupního souboru je uživateli umožněno portfolio znovu nahrát.**

#### Objektový návrh - diagram
![Investment_Portfolio](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Semestral.png)

### Testování
V testech 1 - 3 bylo testované načtení, výpis a prodej portfolia, data byla čerpána z pdf souboru testStocks.pdf a binárního souboru testCrypto. V testech 4 - 7 bylo testované založení a výpis portfolia. V testech 8 - 9 bylo testováno ošetření nesprávného vstupu do konzole. V testu 10 (a test10_output) bylo testováno ukládání do externího souboru. Reálné výpisy ve všech testech se shodovaly s předpokládanými výpisy.\
test1
![test1](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test1.png)
test2
![test2](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test2.png)
test3
![test3](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test3.png)
test4
![test4](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test4.png)
test5
![test5](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test5.png)
test6
![test6](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test6.png)
test7
![test7](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test7.png)
test8
![test8](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test8.png)
test9
![test9](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test9.png)
test10
![test10](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test10.png)
test10_output
![test10_output](https://github.com/pryjmi/2021ALG2Semestral_pryjmak_InvestmentPortfolioSimulator/blob/main/Testing_screens/test10_output.png)

### Popis fungování externí knihovny - Apache PDFBox®   
Externí knihovna PDFBox umožňuje vytváření nových PDF dokumentů, manipulaci s existujícími a získávání informací z pdf dokumentů.  
    
    //načítání
    PDDocument d = PDDocument.load(f);
    PDFTextStripper ts = new PDFTextStripper();
    String text = ts.getText(d);
    d.close();
    
    //zápis
    PDDocument d = new PDDocument();
    PDPage p = new PDPage();
    d.addPage(p);
    PDPageContentStream stream = new PDPageContentStream(d, p);
    PDFont font = PDType1Font.HELVETICA;
    stream.setFont(font, 14);
    stream.beginText();
    stream.moveTextPositionByAmount(10, 775);
    stream.showText("Test");
    stream.endText();
    stream.close();
    d.save(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
    d.close();
    
       
   

