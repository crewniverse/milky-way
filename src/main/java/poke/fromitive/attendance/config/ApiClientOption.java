package poke.fromitive.attendance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api-client")
public class ApiClientOption {
    private final String tokenDirectoryPath;
    private final String credentialsFilePath;
    private final int readTimeoutMs;
    private final int connectTimeoutMs;

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

    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    public int getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public int getConnectTimeoutMs() {
        return connectTimeoutMs;
    }
}
