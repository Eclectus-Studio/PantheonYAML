package com.eclectusstudio.pantheonYAML.parser;

import com.eclectusstudio.pantheonYAML.pojo.Projects;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ProjectsParser {

    public static void parse(Path projectsFile, Path projectsDirectory) throws IOException {
        Yaml yaml = new Yaml();

        try (InputStream input = Files.newInputStream(projectsFile)) {
            Map<String, Object> root = yaml.load(input);

            // Empty YAML / only comments
            if (root == null) {
                return;
            }

            Object projectsObject = root.get("projects");

            if (!(projectsObject instanceof List<?> projects)) {
                return;
            }

            for (Object projectObject : projects) {
                if (!(projectObject instanceof Map<?, ?> project)) {
                    continue;
                }

                Object namespaceObject = project.get("namespace");
                Object relativePathObject = project.get("relative-path");

                if (!(namespaceObject instanceof String namespace)
                        || !(relativePathObject instanceof String relativePath)) {
                    continue;
                }

                Path projectPath = projectsDirectory.resolve(relativePath).normalize();

                // Prevent paths such as ../../something from escaping
                // the projects directory.
                if (!projectPath.startsWith(projectsDirectory.normalize())) {
                    throw new IOException(
                            "Project '" + namespace + "' has an invalid relative path: "
                                    + relativePath
                    );
                }

                if (!Files.exists(projectPath)) {
                    continue;
                }

                Projects.addProject(
                        new Projects(namespace, relativePath)
                );
            }
        }
    }
}
