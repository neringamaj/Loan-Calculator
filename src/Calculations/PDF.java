package Calculations;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;
import static Constants.Constants.columns;

public class PDF {
    double[] payments;
    double[] principalAmounts;
    double[] interestAmounts;
    double[] debtsLeft;
    int x,y;
    int n;

    public PDF(int n, double[] payments, double[] principalAmounts, double[] interestAmounts,
               double[] debtsLeft, int x, int y) {
        this.n = n;
        this.payments = payments;
        this.principalAmounts = principalAmounts;
        this.interestAmounts = interestAmounts;
        this.debtsLeft = debtsLeft;
        this.x = x;
        this.y = y;

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Ataskaita.pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        PdfPTable table = new PdfPTable(5);
        addTableHeader(table);
        addRows(table);

        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    private void addTableHeader(PdfPTable table){
        Stream.of(columns)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table){
        for (int i = x; i < y; ++i){
            table.addCell(String.valueOf(i+1));
            table.addCell(String.valueOf(payments[i]));
            table.addCell(String.valueOf(principalAmounts[i]));
            table.addCell(String.valueOf(interestAmounts[i]));
            table.addCell(String.valueOf(debtsLeft[i]));
        }
    }
}
