package classes.object;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Row;
import classes.model.POProduct;
import classes.model.Product;
import classes.model.PurchaseOrder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PDFCreator {
    private static String FILE_PATH = "/var/wprod/temp/file_pdf_temp_";
    private static String FONT_PATH = "/var/font/tahoma.ttf";

    public String createPurchaseOrderPDF(PurchaseOrder purchaseOrder) throws IOException {
        PDPage myPage = new PDPage(PDRectangle.A4);
        PDDocument mainDocument = new PDDocument();
        PDFont font = PDType0Font.load(mainDocument, new File(FONT_PATH));
        PDPageContentStream contentStream = new PDPageContentStream(mainDocument, myPage);

        ////////////////////////////////////////////////////////
        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 800);
        contentStream.drawString("Siam Markro Public Company");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 15);
        contentStream.moveTextPositionByAmount(450, 800);
        contentStream.drawString("ใบสั่งสินค้า");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 790);
        contentStream.drawString("บมจ. สยามแม๊คโคร (สำนักงานใหญ่)");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 780);
        contentStream.drawString("1468 ถนนพัฒนาการ แขวงพัฒนาการ เขตสวนหลวง กรุงเทพมหานคร 10250");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 770);
        contentStream.drawString("โทร 0-2067-8999 (อัตโนมัติ)");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 760);
        contentStream.drawString("เลขประจำตัวผู้เสียภาษีอากร 0 10 7 537 00052 1");
        contentStream.endText();
        ////////////////////////////////////////////////////////

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 735);
        contentStream.drawString("ชื่อผู้ผลิต");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(250, 735);
        contentStream.drawString("โปรดส่งสินค้าไปที่");
        contentStream.endText();

        //////////////// SUPPLIER /////////////////////////////
        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 725);
        contentStream.drawString(purchaseOrder.getSupplier_name());
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 715);
        contentStream.drawString("ADD_1");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 705);
        contentStream.drawString("ADD_2");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 695);
        contentStream.drawString("ADD_3");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 685);
        contentStream.drawString("CONTACT");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 675);
        contentStream.drawString("SUP_NAME");
        contentStream.endText();

        //////////////// BRANCH /////////////////////////////

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(250, 725);
        contentStream.drawString(purchaseOrder.getCustomer_branch_name());
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(250, 715);
        contentStream.drawString("ADD_1");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(250, 705);
        contentStream.drawString("ADD_2");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(250, 695);
        contentStream.drawString("ADD_3");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(250, 685);
        contentStream.drawString("CONTACT");
        contentStream.endText();

        //////////////// DETAIL /////////////////////////////

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(230, 665);
        contentStream.drawString("วันที่สั่งซื้อ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(290, 665);
        contentStream.drawString("รหัสผู้ผลิต");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(350, 665);
        contentStream.drawString("เลขที่ใบสั่งซื้อ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(420, 665);
        contentStream.drawString("แผ่นที่");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(230, 655);
        String orderDate = purchaseOrder.getOrder_date().substring(0, Math.min(purchaseOrder.getOrder_date().length(), 11));
        contentStream.drawString(orderDate);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(290, 655);
        contentStream.drawString(String.valueOf(purchaseOrder.getSupplier_id()));
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(350, 655);
        contentStream.drawString(purchaseOrder.getPo_number());
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(420, 655);
        contentStream.drawString("1");
        contentStream.endText();




        //////////////TABLE///////////////////////
        generateTable(myPage, mainDocument, font, purchaseOrder.getPo_product());
        //////////////TABLE///////////////////////

        //////////////// FOOTER /////////////////////////////

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 50);
        contentStream.drawString("วันที่ส่งของ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(100, 50);
        String deliveryDate = purchaseOrder.getExpect_delivery_date().substring(0, Math.min(purchaseOrder.getExpect_delivery_date().length(), 11));
        contentStream.drawString(deliveryDate);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(50, 35);
        contentStream.drawString("การชำระเงิน");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 9);
        contentStream.moveTextPositionByAmount(100, 35);
        contentStream.drawString("XX");
        contentStream.endText();

        contentStream.close();
        mainDocument.addPage(myPage);
        String savedFileName = FILE_PATH+System.currentTimeMillis()+".pdf";
        mainDocument.save(savedFileName);
        mainDocument.close();

        return savedFileName;
    }

    public void generateTable(PDPage myPage, PDDocument mainDocument, PDFont font, ArrayList<Product> products) throws IOException {
        //////////////TABLE///////////////////////
        float margin = 50;
        float yStartNewPage = myPage.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        float yPosition = 645;

        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, mainDocument, myPage, true, drawContent);

        //////////////// HEADER ////////////////////////
        Row<PDPage> headerRow = table.createRow(15f);
        headerRow.createCell(10, "ผู้ซื้อ").setFont(font);
        headerRow.createCell(45, "รายการ").setFont(font);
        headerRow.createCell(12, "รหัสแม๊คโคร").setFont(font);
        headerRow.createCell(12, "จำนวนหน่วย").setFont(font);
        headerRow.createCell(12, "ชนิดบรรจุ").setFont(font);
        headerRow.createCell(12, "จำนวนสั่งซื้อ").setFont(font);

        table.addHeaderRow(headerRow);

        for (Product product : products) {
            Row<PDPage> row = table.createRow(12);
            row.createCell(10, "FF1").setFont(font);
            row.createCell(45, product.getProduct_name()).setFont(font);
            row.createCell(12, "-").setFont(font);
            row.createCell(12, ((POProduct) product).getOrder_amount().toString()).setFont(font);
            row.createCell(12, product.getProduct_type()).setFont(font);

            NumberFormat nf= NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            nf.setRoundingMode(RoundingMode.HALF_UP);

            Double totalValue = ((POProduct) product).getOrder_amount()*product.getPrice();
            row.createCell(12, nf.format(totalValue)).setFont(font);
        }

        table.draw();
        //////////////TABLE///////////////////////
    }
}
