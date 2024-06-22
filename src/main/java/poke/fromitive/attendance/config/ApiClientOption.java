package poke.fromitive.attendance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api-client")
public class ApiClientOption {
    private String tokenDirectoryPath;
    private String credentialsFilePath;
    private int readTimeoutMs;
    private int connectTimeoutMs;

    private ApiClientOption() {
    }

    public ApiClientOption(final String tokenDirectoryPath,
                           final String credentialsFilePath,
                           final int readTimeoutMs,
                           final int connectTimeoutMs) {
        this.tokenDirectoryPath = tokenDirectoryPath;
        this.credentialsFilePath = credentialsFilePath;
        this.readTimeoutMs = readTimeoutMs;
        this.connectTimeoutMs = connectTimeoutMs;
    }

    public String getTokenDirectoryPath() {
        return tokenDirectoryPath;
    }

    public void setTokenDirectoryPath(final String tokenDirectoryPath) {
        this.tokenDirectoryPath = tokenDirectoryPath;
    }

    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    public void setCredentialsFilePath(final String credentialsFilePath) {
        this.credentialsFilePath = credentialsFilePath;
    }

    public int getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public void setReadTimeoutMs(final int readTimeoutMs) {
        this.readTimeoutMs = readTimeoutMs;
    }

    public int getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public void setConnectTimeoutMs(final int connectTimeoutMs) {
        this.connectTimeoutMs = connectTimeoutMs;
    }
}
