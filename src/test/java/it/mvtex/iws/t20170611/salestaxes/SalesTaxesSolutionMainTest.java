package it.mvtex.iws.t20170611.salestaxes;

import java.io.PrintWriter;
import java.io.StringWriter;

import it.mvtex.iws.t20170611.salestaxes.processing.ReceiptProcessor;
import it.mvtex.iws.t20170611.salestaxes.processing.ReceiptProcessor.Builder;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SalesTaxesSolutionMainTest
        extends TestCase {

    public SalesTaxesSolutionMainTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(SalesTaxesSolutionMainTest.class);
    }

    public void testReceiptProcessorBuilder() {
        Builder builder = new ReceiptProcessor.Builder();

        assertNotNull(builder);

        assertEquals(builder, builder.addInvoice("1 book at 16.99"));

        assertEquals(builder, builder.addInvoices("1 book at 17.99", "1 book at 18.99"));
    }

    public void testEmptyReceipt() {
        ReceiptProcessor emptyProcessorSample = new ReceiptProcessor.Builder().build();

        assertNotNull(emptyProcessorSample);

        String calculatedResult = emptyProcessorSample.printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("Sales Taxes: 0.00");
        pw.println("Total: 0.00");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    public void testBasicTaxOnBook() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoice("1 book at 16.99")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 book: 16.99");
        pw.println("Sales Taxes: 0.00");
        pw.println("Total: 16.99");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    public void testBasicTaxOnFood() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoice("1 chocolate bar at 1.50")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 chocolate bar: 1.50");
        pw.println("Sales Taxes: 0.00");
        pw.println("Total: 1.50");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    public void testBasicTaxOnMedical() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoice("1 headache pills at 8.75")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 headache pills: 8.75");
        pw.println("Sales Taxes: 0.00");
        pw.println("Total: 8.75");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    public void testBasicTaxOnOtherGoodType() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoice("1 car at 24999.00")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 car: 27498.90");
        pw.println("Sales Taxes: 2499.90");
        pw.println("Total: 27498.90");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    public void testImportDutyTaxWithoutBasicTax() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoice("1 imported book at 20.80")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 imported book: 20.80");
        pw.println("Sales Taxes: 1.05");
        pw.println("Total: 21.85");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    public void testImportDutyTaxWithBasicTax() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoice("1 imported car at 24999.00")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 imported car: 24999.00");
        pw.println("Sales Taxes: 3749.85");
        pw.println("Total: 28748.85");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    // test output #1 as the assignment example
    public void testOutput1Assignment() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoices("1 book at 12.49",
                             "1 music CD at 14.99",
                             "1 chocolate bar at 0.85")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 book : 12.49");
        pw.println("1 music CD: 16.49");
        pw.println("1 chocolate bar: 0.85");
        pw.println("Sales Taxes: 1.50");
        pw.println("Total: 29.83");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    // test output #2 as the assignment example
    public void testOutput2Assignment() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoices("1 imported box of chocolates at 10.00",
                             "1 imported bottle of perfume at 47.50")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 imported box of chocolates: 10.50");
        pw.println("1 imported bottle of perfume: 54.65");
        pw.println("Sales Taxes: 7.65");
        pw.println("Total: 65.15");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

    // test output #3 as the assignment example
    public void testOutput3Assignment() {
        String calculatedResult = new ReceiptProcessor.Builder()
                .addInvoices("1 imported bottle of perfume at 27.99",
                             "1 bottle of perfume at 18.99",
                             "1 packet of headache pills at 9.75",
                             "1 box of imported chocolates at 11.25")
                .build()
                .printReceiptDetails();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("1 imported bottle of perfume: 32.19");
        pw.println("1 bottle of perfume: 20.89");
        pw.println("1 packet of headache pills: 9.75");
        pw.println("1 imported box of chocolates: 11.85");
        pw.println("Sales Taxes: 6.70");
        pw.println("Total: 74.68");
        pw.close();
        String expectedResult = sw.toString();

        assertEquals(calculatedResult, expectedResult);
    }

}