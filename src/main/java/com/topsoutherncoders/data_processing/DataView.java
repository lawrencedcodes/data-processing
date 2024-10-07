package com.topsoutherncoders.data_processing;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class DataView extends VerticalLayout {
    public DataView(DataService dataService) {
        add(new H1("Weather Data"));
        String temp = dataService.getTemperature();
        add(new Paragraph("Current Temp: " + temp));

    }
}
