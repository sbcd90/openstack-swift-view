package org.apache.ambari.connectors;


import org.openstack4j.model.storage.object.SwiftAccount;

public interface SwiftConnector {
    SwiftAccount getAccount();
}