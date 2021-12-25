package com.company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.ui.RefineryUtilities;


public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        List<Country> countryList = getCountryDetails("Показатель счастья по странам 2015.csv");
        addDataToDb(countryList);
        Task1();
        Task2();
        Task3();
    }

    public static void Task1() throws SQLException {
        ChartBuilder chart = new ChartBuilder("Histogram", "Economy");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public static void Task2() {
        try {
            String url = "jdbc:sqlite:C:/Users/anvar/Desktop/SQLite/mydatabase.db";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            var highestEconomyInEaAndLatin = statement.executeQuery("SELECT max(Economy) FROM Countries WHERE (Region = 'Eastern Asia' OR Region = 'Latin America and Caribbean')");
            var numEaAndLatin = highestEconomyInEaAndLatin.getFloat(1);
            var queryForLatinAndEa  = statement.executeQuery("SELECT Country FROM Countries WHERE Economy ='" + numEaAndLatin + "'");
            System.out.println("Страна с самым высоким показателем экономики среди Eastern Asia и Latin America and Caribbean: " + queryForLatinAndEa.getString(1));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Task3() {
        try {
            String url = "jdbc:sqlite:C:/Users/anvar/Desktop/SQLite/mydatabase.db";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            var medianSE = statement.executeQuery("SELECT median(StandardError) FROM Countries WHERE (Region = 'Western Europe' OR  Region = 'North America')");
            var numMedianSE = medianSE.getFloat(1);
            var countryWithMedianSE = statement.executeQuery("SELECT Country FROM Countries WHERE StandardError ='" + numMedianSE + "'");
            System.out.println("Страна с самым средним показателем: " + countryWithMedianSE.getString(1));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataToDb(List<Country> countryList) {

        try {
            String url = "jdbc:sqlite:C:/Users/anvar/Desktop/SQLite/mydatabase.db";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Countries");
            for (Country country:countryList) {
                statement.executeUpdate("INSERT INTO Countries (Country, Region, HappinessRank, HappinessScore, StandardError, Economy, Family, Health, Freedom, Trust, Generosity, DystopiaResidual) " +
                        "VALUES ('" + country.Country + "', '" + country.Region + "', '" + country.HappinessRank + "', '" + country.HappinessScore + "', '" + country.StandardError + "', '" + country.Economy + "', '" + country.Family + "', '" + country.Health + "', '" + country.Freedom + "', '" + country.Trust + "', '" + country.Generosity + "', '" + country.DystopiaResidual + "')");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Country> getCountryDetails(String file) throws IOException {
        List<Country> countryList = new ArrayList<>();
        Path pathToFile = Paths.get(file);
        BufferedReader reader = Files.newBufferedReader(pathToFile);
        try {
            String row = reader.readLine();
            while (row != null) {
                String[] attributes = row.split(",");
                Country country = getOneCountry(attributes);
                countryList.add(country);
                row = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return countryList;
    }

    private static Country getOneCountry(String[] attributes) {
        var name = attributes[0];
        var region = attributes[1];
        var happinessRank = Integer.parseInt(attributes[2]);
        var happinessScore = Float.parseFloat(attributes[3]);
        var standardError = Float.parseFloat(attributes[4]);
        var economy = Float.parseFloat(attributes[5]);
        var family = Float.parseFloat(attributes[6]);
        var health = Float.parseFloat(attributes[7]);
        var freedom = Float.parseFloat(attributes[8]);
        var trust = Float.parseFloat(attributes[9]);
        var generosity = Float.parseFloat(attributes[10]);
        var dystopiaResidual = Float.parseFloat(attributes[11]);
        return new Country(name, region, happinessRank, happinessScore, standardError, economy, family, health, freedom, trust, generosity, dystopiaResidual);
    }
}
