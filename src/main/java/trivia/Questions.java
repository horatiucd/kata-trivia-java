package trivia;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Questions {

    private final Map<Category, LinkedList<String>> byCategory = new EnumMap<>(Category.class);

    public Questions() {
        for (Category category : Category.values()) {
            byCategory.put(category, IntStream.range(0, 50)
                                        .mapToObj(index -> String.format("%s Question %d", category.name(), index))
                                        .collect(Collectors.toCollection(LinkedList::new)));
        }
    }

    public String popQuestion(Category category) {
        return byCategory.get(category)
                .removeFirst();
    }
}
