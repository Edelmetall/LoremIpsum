package ch.zhaw.pm3.loremipsum.generator;

import java.util.HashMap;
import java.util.Map;

public enum EntryTypeEnum {
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    IBANR("IBAN"),
    TELE_NR("Phone number");

    private static final Map<String, EntryTypeEnum> displayNameToEnumMap = new HashMap<>();

    private final String displayName;

    static {
        for (EntryTypeEnum e : EntryTypeEnum.values()) {
            displayNameToEnumMap.put(e.getDisplayName(), e);
        }
    }

    EntryTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static EntryTypeEnum getEnumFromDisplayName(String displayName) {
        return displayNameToEnumMap.get(displayName);
    }
}