package com.eclectusstudio.pantheonYAML;

import com.eclectusstudio.pantheonYAML.parser.ProjectElementsParser;
import com.eclectusstudio.pantheonYAML.parser.ProjectsParser;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

class PantheonYAMLBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(final BootstrapContext context) {
        Path dataFolder = context.getDataDirectory();

        Path projectsFolder = dataFolder.resolve("projects");
        Path projectsFile = dataFolder.resolve("projects.yaml");

        try {
            Files.createDirectories(projectsFolder);

            if (Files.notExists(projectsFile)) {
                createProjectFile(projectsFile);
            }

            loadProjects(projectsFile,projectsFolder);

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize PantheonYAML", e);
        }
    }

    private void loadProjects(Path projectsFile, Path projectsFolder){
        try {
            ProjectsParser.parse(projectsFile, projectsFolder);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createProjectFile(Path file) throws IOException {
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("projects.yaml")) {

            if (input == null) {
                throw new IOException("projects.yaml was not found in plugin resources");
            }

            Files.copy(input, file);
        }
    }
}
