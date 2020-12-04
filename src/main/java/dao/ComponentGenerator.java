package dao;

import models.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.UUID;

public class ComponentGenerator {
    private static File inputFile;
    private static Database database;
    private static ComponentDAO componentDAO;
    private static String name;
    private static String type;
    private static String manufacturer;
    private static int performanceRating;
    private static double price;
    private static String cpuFamily;
    private static int tpd;

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: <filename>");
            return;
        }
        database = new Database();
        try {
            inputFile = new File(args[0]);
            Scanner scanner = new Scanner(inputFile);
            componentDAO = new ComponentDAO(database.openConnection());
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] arrOfData = data.split(", ", 7);
                name = arrOfData[0];
                type = arrOfData[1];
                manufacturer = arrOfData[2];
                performanceRating = Integer.parseInt(arrOfData[3]);
                price = Double.parseDouble(arrOfData[4]);
                if(arrOfData[5].equals("null")) {
                    cpuFamily = null;
                }
                else {
                    cpuFamily = arrOfData[5];
                }
                tpd = Integer.parseInt(arrOfData[6]);
                Component component = new Component(UUID.randomUUID().toString(), name, type, manufacturer,
                        performanceRating, price, cpuFamily, tpd);
                componentDAO.insert(component);
                //System.out.println(component.toString());
            }
            database.closeConnection(true);
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
            if(database.isOpenConnection()) {
                try {
                    database.closeConnection(false);
                } catch (DataAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
