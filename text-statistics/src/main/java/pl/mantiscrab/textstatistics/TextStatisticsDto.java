package pl.mantiscrab.textstatistics;

public class TextStatisticsDto {
    private final Integer textLength;
    private final Integer wordsNumber;
    private final Boolean isPalindrome;
    private final String mostFrequentWord;
    private final Integer mostFrequentWordFrequency;

    public TextStatisticsDto(Integer textLength, Integer wordsNumber, Boolean isPalindrome, String mostFrequentWord, Integer mostFrequentWordFrequency) {
        this.textLength = textLength;
        this.wordsNumber = wordsNumber;
        this.isPalindrome = isPalindrome;
        this.mostFrequentWord = mostFrequentWord;
        this.mostFrequentWordFrequency = mostFrequentWordFrequency;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public Integer getWordsNumber() {
        return wordsNumber;
    }

    public Boolean getIsPalindrome() {
        return isPalindrome;
    }

    public String getMostFrequentWord() {
        return mostFrequentWord;
    }

    public Integer getMostFrequentWordFrequency() {
        return mostFrequentWordFrequency;
    }
}
