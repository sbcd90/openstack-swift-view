package org.apache.ambari.connectors;

import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.api.client.IOSClientBuilder.V3;
import org.openstack4j.api.client.IOSClientBuilder.V2;

import java.util.ArrayList;
import java.util.List;

public class Openstack4jConnector implements SwiftConnector {
    private V3 clientBuilderV3;
    private V2 clientBuilderV2;

    public Openstack4jConnector(String authUrl, String domain, String project, String tenantName, String username, String password) {
        Identifier userDomainName = Identifier.byName(domain);
        Identifier projectName = Identifier.byName(project);
        Identifier projectDomainName = Identifier.byName(domain);

        if(tenantName == null || tenantName.equals(""))
            clientBuilderV3 = OSFactory.builderV3().endpoint(authUrl).credentials(username, password, userDomainName).scopeToProject(projectName,
                    projectDomainName);
        else
            clientBuilderV2 = OSFactory.builder().endpoint(authUrl).credentials(username, password).tenantName(tenantName);
    }

    private ObjectStorageService getObjectStorageService() {
        if(clientBuilderV3 != null)
            return clientBuilderV3.authenticate().objectStorage();
        else
            return clientBuilderV2.authenticate().objectStorage();
    }

    public SwiftAccount getAccount() {
        ObjectStorageService objectStorageService = getObjectStorageService();

        return objectStorageService.account().get();
    }

    public SwiftContainer getContainer(String containerName) {
        ObjectStorageService objectStorageService = getObjectStorageService();

        List<? extends SwiftContainer> containers = objectStorageService.containers().list();
        for(SwiftContainer container: containers) {
            if (container.getName().equals(containerName))
                return container;
        }
        return null;
    }

    public List<String> getContainerNames() {
        ObjectStorageService objectStorageService = getObjectStorageService();

        List<String> containerNames = new ArrayList<String>();
        List<? extends SwiftContainer> containers = objectStorageService.containers().list();

        for(SwiftContainer container: containers) {
            containerNames.add(container.getName());
        }

        return containerNames;
    }
}