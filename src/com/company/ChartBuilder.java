package com.company;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ChartBuilder extends ApplicationFrame {

    public ChartBuilder(String applicationTitle, String chartTitle) throws SQLException {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Страны", "Экономика", createDataset(),
                PlotOrientation.VERTICAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1200, 700));
        setContentPane(chartPanel);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(true);

        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.blue);


    }

    private DefaultCategoryDataset createDataset() throws SQLException {
        String url = "jdbc:sqlite:C:/Users/anvar/Desktop/SQLite/mydatabase.db";
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        var countryAndEconomy = statement.executeQuery("SELECT Economy, Country FROM Countries");
        ResultSetMetaData rsmd = countryAndEconomy.getMetaData();
        int columnCount = rsmd.getColumnCount();

        ArrayList<String> countryAndEconomyList = new ArrayList<String>(columnCount);
        while (countryAndEconomy.next()) {
            int i = 1;
            while(i <= columnCount) {
                countryAndEconomyList.add(countryAndEconomy.getString(i++));
            }
        }
        statement.close();

        var dataset = new DefaultCategoryDataset();
        for (var i = 0; i < countryAndEconomyList.size(); i = i + 2) {
            dataset.addValue(Double.parseDouble(countryAndEconomyList.get(i)), "economy", countryAndEconomyList.get(i+1));
        }
        return dataset;
    }
}
