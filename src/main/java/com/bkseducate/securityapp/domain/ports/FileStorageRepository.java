package com.bkseducate.securityapp.domain.ports;

public interface FileStorageRepository {
    String save(byte[] contenido, String name);
}
