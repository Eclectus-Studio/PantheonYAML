package com.eclectusstudio.pantheonYAML.pojo;

import java.util.ArrayList;
import java.util.List;

public class Projects {
    private static final List<Projects> projects = new ArrayList<>();

    private final String namespace;
    private final String relativePath;

    public Projects(String namespace, String relativePath) {
        this.namespace = namespace;
        this.relativePath = relativePath;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getRelativePath() {
        return relativePath;
    }

    // List Methods
    public static List<Projects> getProjects() {
        return projects;
    }

    public static void addProject(Projects project) {
        projects.add(project);
    }
}
