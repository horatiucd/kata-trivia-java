package trivia;

public enum Category {

    POP,
    SCIENCE,
    SPORTS,
    ROCK;

    public static Category getCategory(int index) {
        var categories = Category.values();
        return categories[index % categories.length];
    }
}
