package com.digitalspirit.project.config;

import lombok.Getter;
import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class GitlabApiConfig {
    private static final String host = "http://gitlab:8088";
    @Value("${gitlab.token}")
    private static String token; //TODO: add gitlab token

    public static GitLabApi gitlabApi() {
        return new GitLabApi(host, token);
    }
}
