package org.apache.ambari.view.swift;

import org.apache.ambari.connectors.Openstack4jConnector;
import org.apache.ambari.connectors.SwiftConnector;
import org.apache.ambari.view.ViewContext;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
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

/**            SwiftConnector swiftConnector = new Openstack4jConnector("http://10.97.152.185:5000/v3", "d1", "p1", "u1", "secrete");*/
            return swiftConnector;
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getSwiftStatistics(ModelMap model, HttpServletRequest request) {
        setViewContext();

        setSwiftCredentials();

        SwiftConnector swiftConnector = initializeSwiftConnection();

        getSwiftAccountStatistics(model, swiftConnector);
        getSwiftContainerStatistics(model, swiftConnector);
        return "swiftStatistics";
    }

    public void getSwiftAccountStatistics(ModelMap model, SwiftConnector swiftConnector) {

        SwiftAccount swiftAccount = swiftConnector.getAccount();
        model.addAttribute("bytesUsed", swiftAccount.getBytesUsed());
        model.addAttribute("containerCount", swiftAccount.getContainerCount());
        model.addAttribute("objectCount", swiftAccount.getObjectCount());
        model.addAttribute("temporaryUrlKey", swiftAccount.getTemporaryUrlKey());

        Map<String, String> metadata = swiftAccount.getMetadata();

        String stringifyMetadata = "";
        for(Map.Entry<String, String> entry: metadata.entrySet()) {
            stringifyMetadata = stringifyMetadata + entry.getKey() + " : " + entry.getValue() + " &#13;&#10;";
        }
        model.addAttribute("metadata", stringifyMetadata);
        model.addAttribute("countOfMetadata", metadata.entrySet().size());

        List<String> containerNames = swiftConnector.getContainerNames();
        String stringifyContainerNames = "";
        for(String name: containerNames) {
            stringifyContainerNames = stringifyContainerNames + name + " &#13;&#10;";
        }
        model.addAttribute("containerNames", stringifyContainerNames);
        model.addAttribute("countOfContainerNames", containerNames.size());
    }

    public void getSwiftContainerStatistics(ModelMap model, SwiftConnector swiftConnector) {
        if(swiftCredentials.get("Container") == null) {
            model.addAttribute("containerVisible", "display: block");
            return;
        }

        SwiftContainer swiftContainer = swiftConnector.getContainer(swiftCredentials.get("Container"));
        if(swiftContainer != null) {
            model.addAttribute("containerVisible", "display: block");
            model.addAttribute("container", swiftContainer.getName());
            model.addAttribute("containerObjectCount", swiftContainer.getObjectCount());
            model.addAttribute("containerTotalSize", swiftContainer.getTotalSize());

            Map<String, String> metadata = swiftContainer.getMetadata();
            String stringifyMetadata = "";
            for (Map.Entry<String, String> entry : metadata.entrySet()) {
                stringifyMetadata = stringifyMetadata + entry.getKey() + " : " + entry.getValue() + " &#13;&#10;";
            }
            model.addAttribute("containerMetadata", stringifyMetadata);
            model.addAttribute("containerCountOfMetadata", metadata.entrySet().size());
        }
        else
            model.addAttribute("containerVisible", "display: block");
    }

}