package woowacourse.crewniverse.milkyway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google-credentials")
public class GoogleCredentialsOption {
    private final int receiverPort;
    private final String tokenDirectoryPath;
    private final String credentialsFilePath;

    public GoogleCredentialsOption(final int receiverPort,
                                   final String tokenDirectoryPath,
                                   final String credentialsFilePath) {
        this.receiverPort = receiverPort;
        this.tokenDirectoryPath = tokenDirectoryPath;
        this.credentialsFilePath = credentialsFilePath;
    }

    public int getReceiverPort() {
        return receiverPort;
    }

    public String getTokenDirectoryPath() {
        return tokenDirectoryPath;
    }

    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }
}
