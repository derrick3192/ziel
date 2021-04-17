package com.oe.ziel.dsl;

import com.oe.ziel.dsl.export.GanttPrinter;
import com.oe.ziel.dsl.export.YumlGanttPrinter;
import com.oe.ziel.dsl.model.Gantt;
import org.junit.Assert;
import org.junit.Test;

import static com.oe.ziel.dsl.model.dsl.GanttKeywords.*;

public class GanttTest {

    private static final String EXPECTED_TEXT = "[note: You can stick notes on diagrams too!{bg:skyblue}]\n[Customer]<>1-orders 0..*>[Order]\n[Order]++*-*>[LineItem]\n[Order]-1>[DeliveryMethod]\n[Order]*-*>[Product]\n[Category]<->[Product]\n[DeliveryMethod]^[National]\n[DeliveryMethod]^[International]\n";

    @Test
    public void testGanttJava() {
        GanttPrinter printer = new YumlGanttPrinter();
        Gantt gantt = buildGantt();
        Assert.assertEquals(EXPECTED_TEXT, printer.print(gantt));
    }

    private Gantt buildGantt() {
        return Gantt.create(d -> {
            d.note("You can stick notes on diagrams too!", "skyblue");

            d.aggregation("Customer", "Order", r -> {
                r.source("1");
                r.destination("0..*", "orders");
            });

            d.composition("Order", "LineItem", r -> {
                r.source("*");
                r.destination("*");
            });

            d.association("Order", "DeliveryMethod", r -> {
                r.destination("1");
            });

            d.association("Order", "Product", r -> {
                r.source("*");
                r.destination("*");
            });

            d.association("Category", "Product", r -> {
                r.bidirectional(true);
            });

            d.type("National").inherits(from).type("DeliveryMethod");
            d.type("International").inherits(from).type("DeliveryMethod");
        });
    }

}
