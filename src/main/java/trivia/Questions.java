package trivia;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Questions {

    private final Map<Category, LinkedList<String>> byCategory = new EnumMap<>(Category.class);

    public Questions() {
        List<String> categ1 = List.of(
                "Who is the villain in Ant-Man?",
                "In Spider-Man: Far From Home, what necklace does Peter buy for M.J.?",
                "What system replaced Jarvis?",
                "In Avengers: Endgame, what food is Scott Lang trying to eat when it's blown out of his hand?",
                "In Spider-Man: No Way Home, which version of Spider-Man successfully saves MJ after she falls off a building?",
                "Who cut Thanos' head off?",
                "In Avengers: Endgame, which Infinity Stone does Natasha sacrifice herself for?",
                "Who is the sorcerer and ally of Doctor Strange known as the Ancient One?",
                "What is the alias of T'Challa's sister in Black Panther?",
                "Which Marvel character is known for breaking the fourth wall?",
                "How many dog tags is Bucky seen wearing throughout The Falcon and the Winter Soldier?",
                "What is Carol's nickname for Monica in Captain Marvel?",
                "What does Thor want \"another\" of when he's in the diner in Thor?",
                "Complete the lyric: \"It's been __ all along!\"",
                "How are Erik Killmonger and T'Challa related?"
        );
        byCategory.put(Category.RED, new LinkedList<>(categ1));

        List<String> categ2 = List.of(
                "What is the real name of the villain Red Skull?",
                "What is Natasha's final line before she sacrifices herself on Vormir in Avengers: Endgame?",
                "Asgardians have their own name for Earth. What is it?",
                "In Captain America: Civil War, who isn't on Iron Man's team? Vision, Black Panther, Hawkeye, or Black Widow?",
                "In Thor: Ragnarok, who cameoed as an Asgardian actor portraying Thor in Loki's play?",
                "Whose power exceeds that of the Sorcerer Supreme?",
                "In Thor: Ragnarok, who does Thor fight in the arena while stuck on Sakaar?",
                "Which of these actors makes their debut in Captain America: The Winter Soldier in 2014: Elizabeth Olsen as Wanda Maximoff, Tom Hiddleston as Loki, Danai Gurira as Okoye, or Benedict Wong as Wong?",
                "What does Stan Lee cameo as in Guardians of the Galaxy Vol. 2?",
                "In Hawkeye, what does Yelena cook for Kate when she breaks into her apartment?",
                "Who voiced Ultron in Avengers: Age of Ultron?",
                "Loki discovered he was actually which species?",
                "Where does Peggy tell Steve she wants to meet him for a dance, before he plunges into the ice in Captain America: The First Avenger?",
                "Natasha remarks to Clint that the Battle of New York is a lot like what in The Avengers?",
                "What is Valkyrie's scrapper number in Thor: Ragnarok?"
        );
        byCategory.put(Category.BLUE, new LinkedList<>(categ2));

        List<String> categ3 = List.of(
                "Who makes their MCU debut in Thor: Ragnarok (2017)",
                "In Captain America: Civil War, what dish does Wanda help Vision make?",
                "In Loki, what does the TVA stand for?",
                "Why was Scott Lang arrested?",
                "What was the name of Peter Parker's high school?",
                "In Spider-Man: No Way Home, which of these Peter Parkers is nicknamed \"Peter 3\"?",
                "What's the name of Tony Stark's daughter?",
                "Who played War Machine in the first Iron Man?",
                "Which Spider-Man in Spider-Man: No Way Home suffers from back pain?",
                "What is the first line of \"Agatha All Along\" in Wandavision?",
                "Scott hid an old Ant-Man suit underneath a trophy. What did the trophy say?",
                "Finish this line from Guardians of the Galaxy Vol. 2: \"I'm ___, y'all!\"",
                "What's Black Widow's actual name?",
                "Who is Peter Parker's best friend?"
        );
        byCategory.put(Category.GREEN, new LinkedList<>(categ3));

        List<String> categ4 = List.of(
                "Hawkeye has how many children?",
                "What is Thor's first line in the MCU?",
                "What was the name of the missile that Tony Stark was demonstrating in Afghanistan just before his convoy was attacked?",
                "After escaping from the US, where was Bruce Banner in hiding and working in a bottling plant?",
                "What was the last thing Thor said in Avengers: Endgame?",
                "What was the name of the radiation that Steve Rogers was subjected to that transformed him into a super soldier?",
                "What was the name of Jane Foster's ex-boyfriend in whose name a fake driver's license was given to Thor?",
                "As a part of her cover, in which country had \"Natalie Rushman\" worked as a model?",
                "True or false: Algrim is a member of Asgard's \"The Warriors Three.\"",
                "To steal which metal did Hawkeye go to Stuttgart, where the Avengers have their first confrontation with Loki?",
                "Which villain appears near the end of Spider-Man: No Way Home?",
                "What alien race is Ronan in Guardians of the Galaxy?",
                "What is the name of the automated Asgardian weapon used by Loki in Thor?",
                "What museum has a Captain American exhibit?",
                "What song plays at the beginning of Iron Man?"
        );
        byCategory.put(Category.GREY, new LinkedList<>(categ4));
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
