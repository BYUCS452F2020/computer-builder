package dao;

import models.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBComponentGenerator {
    private static File inputFile;
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
        List<Component> componentList = new ArrayList<>();
        try {
            inputFile = new File(args[0]);
            Scanner scanner = new Scanner(inputFile);
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
                componentList.add(component);
                System.out.println(component.toString());
            }
            ComponentMDAO componentMDAO = new ComponentMDAO();
            componentMDAO.insertMany(componentList);
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
        }
    }
}
