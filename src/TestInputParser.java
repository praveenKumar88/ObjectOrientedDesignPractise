import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class TestInputParser {
    private final Scanner scan;

    public TestInputParser() {
        this.scan = new Scanner(System.in);
    }

    public ArrayList<String> readInputParagraph() {
        String line;
        String intputLinePattern;
        LineProcessor lineProcessor = new LineProcessor();
        while (this.scan.hasNextLine() && (line = this.scan.nextLine()).length() > 0) {
            intputLinePattern = matchLineWithPattern(line);
            if (intputLinePattern.equals("patternAssigned")) {
                lineProcessor.processKnownValues(line);
            } else if (intputLinePattern.equals("patternCredits")) {
                lineProcessor.calculateUnknownValues(line);
            } else if (intputLinePattern.equals("patternHowMuch")) {
                lineProcessor.processQuestion(line);
            } else if (intputLinePattern.equals("patternHowMany")) {
                lineProcessor.processQuestion(line);
            } else {
                lineProcessor.processErrorLine();
            }
        }
        return lineProcessor.getOutput();
    }


    private String matchLineWithPattern(String line) {
        line = line.trim();
        HashMap<String, String> lineFilter = new HashMap<String, String>();
        lineFilter.put("^([A-Za-z]+) is ([I|V|X|L|C|D|M])$", "patternAssigned");
        lineFilter.put("^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$", "patternCredits");
        lineFilter.put("^how much is (([A-Za-z\\s])+)\\?$", "patternHowMuch");
        lineFilter.put("^how many [c|C]redits is (([A-Za-z\\s])+)\\?$", "patternHowMany");
        Set<String> keyset = lineFilter.keySet();
        for (String key : keyset) {
            if (line.matches(key)) {
                return lineFilter.get(key);
            }
        }
        return "error";
    }
}
