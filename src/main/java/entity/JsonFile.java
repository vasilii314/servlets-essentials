package entity;

public class JsonFile {
    private final String name;
    private final Content content;

    public JsonFile(String name, Content content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public Content getContent() {
        return content;
    }
}
