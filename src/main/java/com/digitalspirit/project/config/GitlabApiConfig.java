package com.digitalspirit.project.config;

import lombok.Getter;
import org.gitlab4j.api.GitLabApi;

@Getter
public class GitlabApiConfig {
    private static final String host = "http://gitlab:8088";
    private static final String token = ""; //TODO: add gitlab token

    public static GitLabApi gitlabApi() {
        return new GitLabApi(host, token);
    }
}
