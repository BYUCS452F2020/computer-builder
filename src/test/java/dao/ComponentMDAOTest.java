package dao;

import models.Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ComponentMDAOTest {

    @Test
    @DisplayName("Test component generator/finding many")
    public void testGenerationAndFindingMany() {
        //Make sure to run component generator first before testing
        ComponentMDAO componentMDAO = new ComponentMDAO();

        Assertions.assertDoesNotThrow(() -> {
            List<Component> componentList = new ArrayList<>();
            componentList = componentMDAO.findMany("Motherboard", 0, 0, null, 0);
            Assertions.assertNotEquals(new ArrayList<>().toString(), componentList.toString());
            System.out.println(componentList.size());
            for(Component curr : componentList) {
                System.out.println(curr.toString());
            }
        });


    }

    @Test
    @DisplayName("Test finding many with conditions")
    public void testFindManyConditions() {

        ComponentMDAO componentMDAO = new ComponentMDAO();

        Assertions.assertDoesNotThrow(() -> {
            List<Component> componentList = new ArrayList<>();
            componentList = componentMDAO.findMany("CPU", 200.00, 5, "Intel", 0);
            Assertions.assertNotEquals(new ArrayList<>().toString(), componentList.toString());
            System.out.println(componentList.size());
            for(Component curr : componentList) {
                System.out.println(curr.toString());
            }
        });

    }



    @Test
    public void deleteAllTest() {
        ComponentMDAO componentMDAO = new ComponentMDAO();
        componentMDAO.deleteAll();
    }
}
