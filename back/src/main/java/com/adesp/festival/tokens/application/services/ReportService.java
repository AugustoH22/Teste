package com.adesp.festival.tokens.application.services;

import com.adesp.festival.dishes.domain.repositories.DishRepository;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;
import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    private final QRCodeService qrCodeService;

    public ReportService(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    private Cell reportQrCodeCellFactory(String votingToken, float cellHeight){
        try{
            Cell cell = new Cell();

            cell.add(new Paragraph("Restaurante Araguaia").setFont(PdfFontFactory.createFont(FontFactory.TIMES_BOLD)).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setMargin(0));
            cell.add(new Image(ImageDataFactory.create(qrCodeService.generateQRCodeImage(String.format("http://18.230.134.152/voting/vote?votingToken=%s", votingToken), 100, 100))).setHorizontalAlignment(HorizontalAlignment.CENTER).setMargins(0,0,0,0).setPadding(0));
            cell.add(new Paragraph(votingToken).setFont(PdfFontFactory.createFont(FontFactory.TIMES_BOLD)).setFontSize(12).setTextAlignment(TextAlignment.CENTER).setMargin(0).setPadding(0));
            cell.setPadding(4);
            cell.setHeight(155);
            cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
            return cell;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private Cell reportDescriptionCellFactory(){
        try{
            Cell cell = new Cell();
            cell.add(new Paragraph("Festival Cultural").setFont(PdfFontFactory.createFont(FontFactory.TIMES_BOLD)));
            cell.add(new Paragraph("Festival Cultural"));
            cell.add(new Paragraph("Festival Cultural"));

            return cell;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private float calculateCellWidth(Rectangle pageDimensions, int columns){
        return pageDimensions.getWidth()/columns;
    }
    private float calculateCellHeight(Rectangle pageDimensions, int rows){
        return pageDimensions.getHeight()/rows;
    }


    public ByteArrayOutputStream printVotingTokensBatch(List<VotingToken> tokens){
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfDocument votingTokensBatch = new PdfDocument(new PdfWriter(outputStream));
            Document document = new Document(votingTokensBatch);
            document.setMargins(12, 12, 12, 12);
            document.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Rectangle pageDimensions = document.getPageEffectiveArea(PageSize.A4);
            float cellWidth = this.calculateCellWidth(pageDimensions, 4);
            //document.add(new Paragraph("Tokens - Lote 1"));
            Table table = new Table(new float[]{cellWidth, cellWidth, cellWidth, cellWidth}).setFixedLayout().setExtendBottomRow(true);
            for(int index = 0; index < tokens.size(); index++){


                if(index == 0 || index % 16 == 0){
                    table.setKeepWithNext(true);
                    table.setHorizontalAlignment(HorizontalAlignment.CENTER);
                    table.setVerticalAlignment(VerticalAlignment.MIDDLE);
                }
                table.addCell(this.reportQrCodeCellFactory(tokens.get(index).getVotingToken(), this.calculateCellHeight(pageDimensions, 4)));
            }
            document.add(table);
            document.close();
            return outputStream;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
