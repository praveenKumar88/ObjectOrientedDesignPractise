import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

public class LineProcessor {

    private final HashMap<String, String> inputStringToValueMap;
    private final ArrayList<String> output;
    private HashMap<String, String> inputStringToSymbolMap;
    private static final Logger log = Logger.getLogger(String.valueOf(TestInputParser.class));

    public LineProcessor() {
        this.inputStringToSymbolMap = new HashMap<String, String>();
        this.inputStringToValueMap = new HashMap<String, String>();
        this.output = new ArrayList<String>();
    }

    protected ArrayList<String> getOutput() {
        return output;
    }

    protected void processAssignedLine(String line) {
        String[] keys = line.split("\\s+");
        try {
            inputStringToSymbolMap.put(keys[0], keys[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.info("invalid line type");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handle input like "glob glob Silver is 34 Credits"
     */
    protected void processCreditsLine(String line) {
        try {
            String formatted = line.replaceAll("(is\\s+)|([c|C]redits\\s*)", "").trim();
            String[] keys = formatted.split("\\s+");
            String unknownKey = keys[keys.length - 2];
            float value = Float.parseFloat(keys[keys.length - 1]);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keys.length - 2; i++) {
                sb.append(inputStringToSymbolMap.get(keys[i]));
            }
            int arabicValue = RomanToArabicConverter.convert(sb.toString());
            float credit = value / arabicValue;
            inputStringToValueMap.put(unknownKey, credit + "");
        } catch (Exception e) {
            log.info("invalid line type");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handle input queries like "how much is pish tegj glob glob ?"
     */
    protected void processHowMuchLine(String line) {
        try {
            String formattedLine = getFormattedLine(line);
            String keys[] = formattedLine.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String key : keys) {
                String romanValue = inputStringToSymbolMap.get(key);
                sb.append(romanValue);
            }
            output.add(formattedLine + " is " + RomanToArabicConverter.convert(sb.toString()));
        } catch (Exception e) {
            log.info("incorrect line type");
            System.out.println(e.getMessage());

        }
    }

    private String getFormattedLine(String s) {
        String formattedLine = s.split("\\sis\\s")[1].trim();
        formattedLine = formattedLine.replace("?", "").trim();
        return formattedLine;
    }

//    protected void processHowManyLine(String line) {
//        try {
//            String formattedLine = getFormattedLine(line);
//            String[] keys = formattedLine.split("\\s");
//            StringBuilder sb = new StringBuilder();
//            Stack<Integer> cvalues = new Stack<Integer>();
//            for (String key : keys) {
//                if (inputStringToSymbolMap.get(key) != null && inputStringToValueMap.get(key) != null) {
//                    sb.append(inputStringToSymbolMap.get(key));
//                    cvalues.push(Integer.valueOf(inputStringToValueMap.get(key)));
//                }
//            }
//            Integer res = 1;
//            for (Integer cvalue : cvalues) res *= cvalue;
//            Integer finalres = RomanToArabicConverter.convert(sb.toString()) * res;
//            this.output.add(formattedLine + " is " + finalres + " Credits");
//        } catch (Exception e) {
//            log.info("invalid line type");
//            System.out.println(e.getMessage());
//        }
//    }

    protected void processHowManyLine(String line) {

        try
        {
            //Remove the unwanted words like "is" and "?"
            String formatted = line.split("(\\sis\\s)")[1];

            formatted = formatted.replace("?","").trim();

            // search for all numerals for their values to compute the result
            String[] keys = formatted.split("\\s");

            boolean found = false;
            String roman = "";
            String outputResult = null;
            Stack<Float> cvalues = new Stack<Float>();

            for(String key : keys)
            {
                found = false;

                String romanValue = inputStringToSymbolMap.get(key);
                if(romanValue!=null)
                {
                    roman += romanValue;
                    found = true;
                }

                String computedValue = inputStringToValueMap.get(key);
                if(!found && computedValue!=null)
                {
                    cvalues.push(Float.parseFloat(computedValue));
                    found = true;
                }


            }

            if(found)
            {
                float res=1;
                for(int i =0;i<cvalues.size();i++)
                    res *= cvalues.get(i);

                int finalres= (int) res;
                if(roman.length()>0)
                    finalres = (int)(Integer.parseInt(String.valueOf(RomanToArabicConverter.convert(roman)))*res);
                outputResult = formatted +" is "+ finalres +" Credits";
            }

            this.output.add(outputResult);

        }
        catch(Exception e)
        {
            System.out.println("error");

        }

    }



    protected void processErrorLine() {
        this.output.add("I have no idea what you are talking about");
    }
}
