package org.apache.ambari.connectors;

import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.model.storage.object.SwiftContainer;

import java.util.List;

public interface SwiftConnector {
    SwiftAccount getAccount();

    SwiftContainer getContainer(String containerName);

    List<String> getContainerNames();
}