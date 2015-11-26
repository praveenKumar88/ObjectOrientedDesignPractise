import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

public class LineProcessor {

    private final HashMap<String, String> knownValueMap;
    private final ArrayList<String> output;
    private HashMap<String, Double> unknownValueMap;
    private static final Logger log = Logger.getLogger(String.valueOf(TestInputParser.class));

    public LineProcessor() {
        this.unknownValueMap = new HashMap<String, Double>();
        this.knownValueMap = new HashMap<String, String>();
        this.output = new ArrayList<String>();
    }

    protected ArrayList<String> getOutput() {
        return output;
    }

    /**
     *
     * Process known values into map so that these can be used to calculate unknowns
     */
    protected void processKnownValues(String line) {
        ArrayList<String> keys = getFormattedLine(line);
        try {
            knownValueMap.put(keys.get(0), keys.get(1));
        } catch (Exception e) {
            log.info("invalid line type");
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * calculate unknown values from the known equivalents
     */
    protected void processUnknownValues(String line) {
        ArrayList<String> formattedLine = getFormattedLine(line);
        String unknownkey = getUnknownKey(formattedLine);
        double unknownValue = calculateUnknownValue(formattedLine);
        unknownValueMap.put(unknownkey, unknownValue);
    }

    private String getUnknownKey(ArrayList<String> formattedLine) {
        return formattedLine.get(formattedLine.size() - 2);
    }

    protected double calculateUnknownValue(ArrayList<String> formattedLine) {
        double knownValueSum = 0;
        double sumOfKnownAndUnknown = Integer.parseInt(formattedLine.get(formattedLine.size() - 1));
        StringBuilder sb = new StringBuilder();
        for(String key: formattedLine) {
            if(knownValueMap.containsKey(key)) {
                sb.append(knownValueMap.get(key));
            }
            try {
                knownValueSum = RomanToArabicConverter.convert(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (sumOfKnownAndUnknown/knownValueSum);
    }

    /**
     * Handle all questions like "how much is pish tegj glob glob ? or how many Credits is glob prok Iron ?"
     */
    protected void processQuestion(String line) {
        int result = getAnswer(line);
        output.add(line + " is " + result);
    }

    protected int getAnswer(String line) {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        try {
            String unknownKey = null;
            for (String key : getFormattedLine(line)) {
                if (knownValueMap.containsKey(key)) {
                    sb.append(knownValueMap.get(key));
                } else {
                    unknownKey = key;
                }
            }
            if (unknownKey == null) {
                result = RomanToArabicConverter.convert(sb.toString());
            } else {
                result = (int) (RomanToArabicConverter.convert(sb.toString()) * unknownValueMap.get(unknownKey));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return result;
    }

    /**
     *
     *  Format the line input to delete unnecessary strings
     */
    protected ArrayList<String> getFormattedLine(String line) {
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
            wordList.remove("is");
            wordList.remove("much");
            wordList.remove("many");
            wordList.remove("?");
            wordList.remove("Credits");
            wordList.remove("how");
        return wordList;
    }

    /**
     * Handle error scenario
     */
    protected void processErrorLine() {
        this.output.add("I have no idea what you are talking about");
    }
}
