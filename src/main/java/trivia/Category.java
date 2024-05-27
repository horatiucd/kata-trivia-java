package trivia;

public enum Category {

    POP,
    SCIENCE,
    SPORTS,
    ROCK;

    public static Category withIndex(int index) {
        var categories = Category.values();
        return categories[index % categories.length];
    }
}
