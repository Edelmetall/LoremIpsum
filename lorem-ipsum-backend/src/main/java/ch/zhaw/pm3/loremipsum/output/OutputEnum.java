package ch.zhaw.pm3.loremipsum.output;

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

    public String getExtension() {
        return this.extension;
    }
}
