package trivia;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Questions {

    private final Map<Category, LinkedList<String>> byCategory = new EnumMap<>(Category.class);

    public Questions() {
        for (Category category : Category.values()) {
            byCategory.put(category, IntStream.range(0, 50)
                                        .mapToObj(index -> String.format("%s Question %d", category.name(), index))
                                        .collect(Collectors.toCollection(LinkedList::new)));
        }
    }

    public String popFor(Category category) {
        List<String> questions = byCategory.get(category);
        if (questions == null ||
                questions.isEmpty()) {
            throw new IllegalArgumentException("No more questions available for category " + category);
        }
        return byCategory.get(category).removeFirst();
    }
}
