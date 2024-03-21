import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomWords {

    private String text[];
    private int LineNumber;
    private int WordsInLine;

    public RandomWords() {
        this.text = getRandomLines();
        this.LineNumber = 0;
        this.WordsInLine = 5;
    }

    private String getTextFromFile(String textFileName) throws IOException {
        String pathstr = ".." + File.separator + "resources" + File.separator +
                         "texts" + File.separator + textFileName;
        Path path = Paths.get(pathstr);
        return Files.readString(path);
    }

    private String[] splitText(String text) { return text.split(" "); }

    private String[] randomizeLines(String[] lines) {
        List<String> listLines = Arrays.asList(lines);
        Collections.shuffle(listLines);
        return listLines.toArray(lines);
    }

    private String[] getRandomLines() {
        try {
            return randomizeLines(
                splitText(getTextFromFile("gametext_default.txt")));
        } catch (IOException err) {
            return new String[] {"Error fetching the string"};
        }
    }

    public String getNextLine() {
        String[] nextLine = Arrays.copyOfRange(text, LineNumber * WordsInLine,
                                               (LineNumber + 1) * WordsInLine);
        LineNumber++ ;
        return String.join(" ", nextLine).concat(" ");
    }

    public void ResetText() {
        this.text = getRandomLines();
        this.LineNumber = 0;
    }
}
