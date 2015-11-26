import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;

public class LineProcessorTest {

    @Test
    public void testCalculationOfUnknown() {
        HashMap<String,String> knownValueMap = new HashMap<String,String>();
        knownValueMap.put("glob","V");
        knownValueMap.put("prok", "I");
        LineProcessor lineProcessor = new LineProcessor(knownValueMap);
        String line = "glob prok Iron is 60 Credits";
        ArrayList<String> formattedLine = lineProcessor.getFormattedLine(line);
        double unknownValue = lineProcessor.calculateUnknownValue(formattedLine);
        TestCase.assertEquals(unknownValue, 10.0);
    }

    @Test
    public void testAnswer() {
        HashMap<String,String> knownValueMap = new HashMap<String,String>();
        knownValueMap.put("glob","V");
        knownValueMap.put("prok", "I");
        LineProcessor lineProcessor = new LineProcessor(knownValueMap);
        String question = "how many Credits is glob prok ?";
        int answer = lineProcessor.getAnswer(question);
        TestCase.assertEquals(answer, 6);
    }


}
