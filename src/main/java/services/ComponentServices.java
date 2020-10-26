package services;

import dao.ComponentDAO;
import dao.DataAccessException;
import dao.Database;
import models.Component;
import models.request.GetComponentsRequest;
import models.request.GetSingleComponentRequest;
import models.result.GetComponentsResult;
import models.result.GetSingleComponentResult;

public class ComponentServices {


    public GetSingleComponentResult getComponent(GetSingleComponentRequest request) {
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

    public GetComponentsResult queryComponents(GetComponentsRequest request) {
        Database database = new Database();
        GetComponentsResult result;
        try {
            ComponentDAO componentDAO = new ComponentDAO(database.openConnection());
            result = new GetComponentsResult(true, componentDAO.queryComponentsWithConditions(
                    request.getComponentType(), request.getBudgetPrice(), request.getPerformanceRating(),
                    request.getCpuFamily()));
        } catch (DataAccessException e) {
            result = new GetComponentsResult(false, e.getMessage());
        }

        return result;
    }
}
