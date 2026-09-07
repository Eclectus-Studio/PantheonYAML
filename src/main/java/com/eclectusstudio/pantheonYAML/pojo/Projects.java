package com.eclectusstudio.pantheonYAML.pojo;

public class Projects {
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
}
