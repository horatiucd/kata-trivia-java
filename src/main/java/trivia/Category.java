package trivia;

public enum Category {

    POP,
    SCIENCE,
    SPORTS,
    ROCK;

    public static Category atIndex(int index) {
        var categories = Category.values();
        return categories[index % categories.length];
    }
}
