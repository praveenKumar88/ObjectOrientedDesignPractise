import java.util.ArrayList;

    public class IntergalacticTranslator {
        public static void main(String[] args) {
            System.out.println("Welcome! Please enter test input data to begin:");
            TestInputParser testInputParser = new TestInputParser();
            ArrayList<String> output = testInputParser.readInputParagraph();
            System.out.println("The output is ...");
            for (String singleOutput : output) {
                System.out.println(singleOutput);
            }
        }
    }
