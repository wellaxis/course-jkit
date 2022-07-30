package com.witalis.jkit.usage.core.invoke.section.networks.content.scenario;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Complex dictionary
 * User: Wellaxis
 * Date: 19.01.2022
 */
public final class ComplexDictionary {
    private static final List<String> messages;

    static {
        messages = List.of(
            "The best way to predict the future is to create it. – Abraham Lincoln",
            "You are never too old to set another goal or to dream a new dream. – C.S.Lewis",
            "Live as if you were to die tomorrow. Learn as if you were to live forever. – Gandhi",
            "Learning is not a spectator sport. – D. Blocher",
            "There is no substitute for hard work. – Thomas Edison",
            "Do the difficult things while they are easy and do the great things while they are small. A journey of a thousand miles begins with a single step. – Lao Tzu",
            "Today a reader, tomorrow a leader. – Margaret Fuller",
            "Learning is like rowing upstream, not to advance is to drop back. – Chinese Proverb",
            "The secret of getting ahead is getting started. – Mark Twain",
            "If you can dream it, you can do it. – Walt Disney",
            "It doesn’t matter how slowly you go as long as you do not stop. – Confucius",
            "By failing to prepare, you are preparing to fail. – Benjamin Franklin",
            "Ever tried. Ever failed. No matter. Try again. Fail again. Fail better. – Samuel Beckett",
            "Language is “the infinite use of finite means.” – Wilhelm von Humboldt",
            "Is it not enjoyable to learn and practise what you learn? – Confucius",
            "Reading is to the mind what exercise is to the body. – Joseph Addison",
            "To have another language is to possess a second soul. – Charlemagne",
            "Language is wine upon the lips. – Virginia Woolf",
            "Tell me and I forget. Teach me and I remember. Involve me and I learn. – Benjamin Franklin",
            "Perfection is the lowest standard of achievement. Doing is the highest. - Andy Jenkins"
        );
    }

    public static String takeQuote() {
        // emulate termination command
        if (sendExit()) return "exit";

        return messages.get(
            ThreadLocalRandom.current().nextInt(0, messages.size())
        );
    }

    private static boolean sendExit() {
        return ThreadLocalRandom.current().nextInt(0, 3) == 1;
    }
}
