package trivia;

public enum Category {

    RED,
    BLUE,
    GREEN,
    GREY;

    public static Category getCategory(int index) {
        var categories = Category.values();
        return categories[index % categories.length];
    }
}
