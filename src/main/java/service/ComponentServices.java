package service;

import dao.ComponentDAO;
import dao.ComponentMDAO;
import dao.DataAccessException;
import dao.Database;
import models.Component;
import models.request.ComponentRequest;
import models.request.GetSingleComponentRequest;
import models.result.ComponentResult;
import models.result.GetSingleComponentResult;

import java.util.List;

public class ComponentServices {


    public GetSingleComponentResult getComponent(GetSingleComponentRequest request) {
        //Database database = new Database();
        Component component;
        GetSingleComponentResult result;
        try {
            //Old SQL code
            //ComponentDAO componentDAO = new ComponentDAO(database.openConnection());
            ComponentMDAO componentMDAO = new ComponentMDAO();
            component = componentMDAO.findOne(request.getComponentId());

            result = new GetSingleComponentResult(true, component);
            //database.closeConnection(true);

        } catch (DataAccessException e) {
            //Old SQL code
            result = new GetSingleComponentResult(false, e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public ComponentResult queryComponents(ComponentRequest request) {

        //Old SQL code
        //Database database = new Database();
        ComponentResult result;
        try {
            ComponentMDAO componentMDAO = new ComponentMDAO();
            List<Component> components = componentMDAO.findMany(
                    request.getComponentType(), request.getMaxPrice(), request.getPerformanceRating(),
                    request.getCpuFamily(), request.getTdp());


            result = new ComponentResult(true, components);
            //database.closeConnection(true);
        } catch (DataAccessException e) {
            //database.closeConnection(false);
            result = new ComponentResult(false, e.getMessage());
        }

        return result;
    }
}
