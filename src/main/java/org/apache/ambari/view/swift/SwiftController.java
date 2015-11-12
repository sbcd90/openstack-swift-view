package org.apache.ambari.view.swift;

import org.apache.ambari.connectors.Openstack4jConnector;
import org.apache.ambari.connectors.SwiftConnector;
import org.apache.ambari.view.ViewContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class SwiftController {
    @Autowired
    ServletContext context;

    private ViewContext viewContext;
    private Map<String, String> swiftCredentials;

    private void setViewContext() {
        viewContext = (ViewContext) context.getAttribute(ViewContext.CONTEXT_ATTRIBUTE);
    }

    private void setSwiftCredentials() {
        swiftCredentials = new HashMap<String, String>();

        if(viewContext != null)
            swiftCredentials.putAll(viewContext.getProperties());
    }

    private SwiftConnector initializeSwiftConnection() {
        if(swiftCredentials != null) {
            SwiftConnector swiftConnector = new Openstack4jConnector(swiftCredentials.get("AuthURL"), swiftCredentials.get("Domain"),
                                    swiftCredentials.get("Project"), swiftCredentials.get("User"), swiftCredentials.get("Password"));
            return swiftConnector;
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getSwiftStatistics(ModelMap model, HttpServletRequest request) {
        setViewContext();

        setSwiftCredentials();

        SwiftConnector swiftConnector = initializeSwiftConnection();

        model.addAttribute("bytesUsed", swiftConnector.getAccount().getBytesUsed());

        return "swiftStatistics";
    }

}