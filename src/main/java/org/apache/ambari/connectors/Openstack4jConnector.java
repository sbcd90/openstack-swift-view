package org.apache.ambari.connectors;

import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.api.client.IOSClientBuilder.V3;

public class Openstack4jConnector implements SwiftConnector {
    private V3 clientBuilder;

    public Openstack4jConnector(String authUrl, String domain, String project, String username, String password) {
        Identifier userDomainName = Identifier.byName(domain);
        Identifier projectName = Identifier.byName(project);
        Identifier projectDomainName = Identifier.byName(domain);

        clientBuilder = OSFactory.builderV3().endpoint(authUrl).credentials(username, password, userDomainName).scopeToProject(projectName,
                projectDomainName);
    }

    private ObjectStorageService getObjectStorageService() {
        return clientBuilder.authenticate().objectStorage();
    }

    public SwiftAccount getAccount() {
        ObjectStorageService objectStorageService = getObjectStorageService();

        return objectStorageService.account().get();
    }
}