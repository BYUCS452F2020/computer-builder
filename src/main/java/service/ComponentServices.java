package service;

import dao.ComponentDAO;
import dao.DataAccessException;
import dao.Database;
import models.Component;
import models.request.ComponentRequest;
import models.request.GetSingleComponentRequest;
import models.result.ComponentResult;
import models.result.GetSingleComponentResult;

import java.util.List;

public class ComponentServices {


    public GetSingleComponentResult getComponent(GetSingleComponentRequest request) throws DataAccessException{
        Database database = new Database();
        Component component;
        GetSingleComponentResult result;
        try {
            ComponentDAO componentDAO = new ComponentDAO(database.openConnection());
            component = componentDAO.queryNoPreconditions(request.getComponentId());
            result = new GetSingleComponentResult(true, component);

            database.closeConnection(true);
        } catch (DataAccessException e) {
            result = new GetSingleComponentResult(false, e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public ComponentResult queryComponents(ComponentRequest request) throws DataAccessException {
        Database database = new Database();
        ComponentResult result;
        try {
            ComponentDAO componentDAO = new ComponentDAO(database.openConnection());
            List<Component> components = componentDAO.queryComponentsWithConditions(
                    request.getComponentType(), request.getMaxPrice(), request.getPerformanceRating(),
                    request.getCpuFamily(), request.getTdp());
            result = new ComponentResult(true, components);
            database.closeConnection(true);
        } catch (DataAccessException e) {
            database.closeConnection(false);
            result = new ComponentResult(false, e.getMessage());
        }

        return result;
    }
}
