
import java.util.*;
import java.lang.*;

/* Name of the class has to be "Main" only if the class is public. */
class DuplicatesAlgorithm
{
    public static void main (String[] args) throws java.lang.Exception
    {
        String s = "abcbadeba";
        Character c = getFirstRepeatingCharacter(s);
        Character d = getNonRepeatingCharacter(s);
        String f = eliminateDuplicates(s);
        System.out.println(c+ " " + d + " " + f);
    }

    private static Character getFirstRepeatingCharacter(String s) {
        HashMap<Character,Integer> hashmap = new HashMap<Character, Integer>();
        char[] charArray = s.toCharArray();
        for(Character c:charArray) {
            if(!hashmap.containsKey(c)) {
                hashmap.put(c,1);
            } else {
                hashmap.put(c,hashmap.get(c)+1);
            }
        }
        System.out.println(hashmap);
        Set keyset = hashmap.keySet();
        System.out.println("keyset"+keyset);
        for(Character c: charArray) {
            if(hashmap.get(c)>1){
                return c;
            }
        }

        return null;

    }

    private static Character getNonRepeatingCharacter(String s) {
        HashMap<Character,Integer> hashmap = new HashMap<Character, Integer>();
        char[] charArray = s.toCharArray();
        for(Character c:charArray) {
            if(!hashmap.containsKey(c)) {
                hashmap.put(c,1);
            } else {
                hashmap.put(c,hashmap.get(c)+1);
            }
        }
        for(Character c: charArray) {
            if(hashmap.get(c)==1){
                return c;
            }
        }
        return null;
    }

    private static String eliminateDuplicates(String s) {
        HashMap<Character,Integer> hashmap = new HashMap<Character, Integer>();
        char[] charArray = s.toCharArray();
        for(Character c:charArray) {
            if(!hashmap.containsKey(c)) {
                hashmap.put(c,1);
            } else {
                hashmap.put(c,hashmap.get(c)+1);
            }
        }
        List<String> newArray = new ArrayList<String>();
        for(Character c: charArray) {
            if(hashmap.get(c)==1){
                newArray.add(c.toString());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String st : newArray) {
            sb.append(st);
        }
        return sb.toString();

    }
}