package com.bbva.ticker.client;

import java.util.UUID;

public class UUIDIdentifierFactory implements IdentifierFactory<String> {
    public String newIdentifier() {
        return UUID.randomUUID().toString();
    }
}
