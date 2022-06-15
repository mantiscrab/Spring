package pl.mantiscrab.textstatistics;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TextStatisticsService {

    public TextStatisticsDto getStatistics(String text, String[] statistics) {
        List<String> statisticsList = (List<String>) Arrays.asList(statistics);
        Integer textLength = null;
        Integer wordsNumber = null;
        Boolean isPalindrome = null;
        String mostFrequentWord = null;
        Integer mostFrequentWordFrequency = null;
        if (statisticsList.contains("text-length"))
            textLength = text.length();
        if (statisticsList.contains("words-number"))
            wordsNumber = text.split(" ").length;
        if (statisticsList.contains("palindrome")) {
            String textWithoutWhiteSpaces = text.replaceAll("\\s", "");
            String reversedTextWithoutWhiteSpaces = new StringBuilder(textWithoutWhiteSpaces).reverse().toString();
            isPalindrome = textWithoutWhiteSpaces.equalsIgnoreCase(reversedTextWithoutWhiteSpaces);
        }
        if (statisticsList.contains("most-frequent-word")) {
            mostFrequentWordFrequency = 0;
            List<String> words = Arrays.asList(text.split(" "));
            words.sort(String::compareToIgnoreCase);
            for (int i = 0; i < words.size(); i++) {
                int wordFrequency = words.lastIndexOf(words.get(i)) - i + 1;
                if (wordFrequency > mostFrequentWordFrequency) {
                    mostFrequentWord = words.get(i);
                    mostFrequentWordFrequency = wordFrequency;
                }
                i = words.lastIndexOf(words.get(i));
            }
        }
        return new TextStatisticsDto(textLength, wordsNumber, isPalindrome, mostFrequentWord, mostFrequentWordFrequency);
    }
}
