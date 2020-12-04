package service;

import dao.ComponentMDAO;
import dao.DataAccessException;
import models.Component;
import models.request.ComponentRequest;
import models.request.GetSingleComponentRequest;
import models.result.ComponentResult;
import models.result.GetSingleComponentResult;

import java.util.List;

public class ComponentServices {


    public GetSingleComponentResult getComponent(GetSingleComponentRequest request) {
        Component component;
        GetSingleComponentResult result;
        try {
            ComponentMDAO componentMDAO = new ComponentMDAO();
            component = componentMDAO.findOne(request.getComponentId());

            result = new GetSingleComponentResult(true, component);

        } catch (DataAccessException e) {
            result = new GetSingleComponentResult(false, e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public ComponentResult queryComponents(ComponentRequest request) {

        ComponentResult result;
        try {
            ComponentMDAO componentMDAO = new ComponentMDAO();
            List<Component> components = componentMDAO.findMany(
                    request.getComponentType(), request.getMaxPrice(), request.getPerformanceRating(),
                    request.getCpuFamily(), request.getTdp());


            result = new ComponentResult(true, components);
        } catch (DataAccessException e) {
            result = new ComponentResult(false, e.getMessage());
        }

        return result;
    }
}
