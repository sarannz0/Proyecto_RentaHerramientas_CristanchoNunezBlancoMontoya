package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.ports.FileStorageRepository;

@Component
public class FileStorageRepositoryAdapter implements FileStorageRepository {
    private final Path root = Paths.get("uploads");

    @Override
    public String save(byte[] contenido, String name) {
        try {
            if (!Files.exists(root)) Files.createDirectories(root);
            String newName = UUID.randomUUID() + "_" + name;
            Files.write(root.resolve(newName), contenido);
            return newName;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la imagen ", e);
        }
    }
}
