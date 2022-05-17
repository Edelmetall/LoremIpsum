package ch.zhaw.pm3.loremipsum.output;

/**
 * contains all Output Formats
 */
public enum OutputEnum {
    XML("xml"),
    JSON("json"),
    JAVA("java"),
    CSV("csv"),
    SQL("sql"),
    PHP("php"),
    CSHARP("cs");

    private final String extension;

    OutputEnum(String extension) {
        this.extension = extension;
    }

    /**
     * extension for this format
     *
     * @return file extension
     */
    public String getExtension() {
        return this.extension;
    }
}
