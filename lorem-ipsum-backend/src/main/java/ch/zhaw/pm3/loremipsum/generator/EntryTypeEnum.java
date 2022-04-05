package ch.zhaw.pm3.loremipsum.generator;

public enum EntryTypeEnum {
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    IBANR("ibanNr"),
    TELE_NR("teleNr");

    private final String displayName;


    EntryTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
