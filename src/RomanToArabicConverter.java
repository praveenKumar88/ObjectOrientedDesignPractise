public class RomanToArabicConverter {
    enum Roman {
        I(1) , V(5), X(10), L(50), C(100), D(500), M(1000);
        private int value;
        Roman(int value) {
            this.value = value;
        }
        public int getValue() {
            return this.value;
        }
    }

    private static int getValueFromRomanChar(char romanChar) {
        int value = -1;
        switch(romanChar) {
            case 'I' : value = Roman.I.getValue();
                break;
            case 'V' : value = Roman.V.getValue();
                break;
            case 'X' : value = Roman.X.getValue();
                break;
            case 'L' : value = Roman.L.getValue();
                break;
            case 'C' : value = Roman.C.getValue();
                break;
            case 'D' : value = Roman.D.getValue();
                break;
            case 'M' : value = Roman.M.getValue();
                break;
        }
        return value;
    }

    public static Integer convert(String roman) throws Exception {
        int tmp = 0;
        int arabicValueOfString =0;
        for(int i = roman.length()-1;i>=0;i--) {
            char ch = roman.charAt(i);
            int arabicValueOfCharacter = getValueFromRomanChar(ch);
            if(arabicValueOfCharacter == -1) {
                throw new Exception("Unable to convert the Roman character: " +ch);
            }
            if (tmp > arabicValueOfCharacter) {
                arabicValueOfString = arabicValueOfString - arabicValueOfCharacter;
            } else {
                arabicValueOfString = arabicValueOfString + arabicValueOfCharacter;
            }
            tmp = arabicValueOfCharacter;
        }
        return arabicValueOfString;
    }
}
