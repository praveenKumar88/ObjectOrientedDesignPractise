import org.junit.Test;
import java.util.ArrayList;
import junit.framework.TestCase;

public class LineProcessorTest {

    @Test
    public void testCalculationOfUnknown() {
        LineProcessor lineProcessor = new LineProcessor();
        String line = "pish pish Iron is 3910 Credits";
        ArrayList<String> formattedLine = lineProcessor.getFormattedLine(line);
        double unknownValue = lineProcessor.calculateUnknownValue(formattedLine);
        TestCase.assertEquals(unknownValue, 195.5);
    }

    @Test
    public void testAnswer() {
        LineProcessor lineProcessor = new LineProcessor();
        String question = "how many Credits is glob prok Silver?";
        int answer = lineProcessor.getAnswer(question);
        TestCase.assertEquals(answer, 68);
    }


}
