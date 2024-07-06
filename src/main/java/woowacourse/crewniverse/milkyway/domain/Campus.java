package woowacourse.crewniverse.milkyway.domain;

import java.util.Arrays;

public enum Campus {
    JAMSIL("잠실"),
    SEOLLEUNG("선릉");

    private final String name;

    Campus(final String name) {
        this.name = name;
    }

    public static Campus fromName(String name) {
        return Arrays.stream(Campus.values())
            .filter(it -> it.getName().equals(name))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("캠퍼스 명이 존재하지 않습니다 [%s]".formatted(name)));
    }

    public String getName() {
        return name;
    }
}
