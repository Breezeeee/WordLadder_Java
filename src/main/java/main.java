
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Stack;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args)  {
        HashSet dictionary;
        while(true) {
            System.out.println("Dictionary file name? ");
            Scanner scanner = new Scanner(System.in);
            String Dictionary_name = scanner.nextLine();
            dictionary = read_Dictionary(Dictionary_name);
            if(dictionary.size() != 0) break;
        }
        while(true) {
            String word1, word2;
            word1 = get_Word(1);
            if(word1.length() == 0) break;
            word2 = get_Word(2);
            if(word2.length() == 0) break;
            if(word1.length() != word2.length()) {
                System.out.println("The two words must be the same length.");
            }
            else if(word1.equals(word2)){
                System.out.println("The two words must be different.");
            }
            else if(!(dictionary.contains(word1) && dictionary.contains(word2))) {
                System.out.println("The two words must be found in the dictionary.");
            }
            else {
//            System.out.println(word1 + "    " + word2);
            System.out.println(wordladder(word1, word2, dictionary));
            }
        }
        System.out.println("Have a nice day.");
    }

    public static String get_Word(int word_number){
        System.out.println("Word #" + word_number + " (or Enter to quit): ");
        Scanner scanner1 = new Scanner(System.in);
        String word = scanner1.nextLine();
        word = word.toLowerCase();
        return word;
    }

    public static HashSet read_Dictionary(String Dictionary_name) {
        File file = new File("src/main/resources/"+Dictionary_name);
        HashSet tmp_dictionary = new HashSet();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
//                System.out.println(tempString);
                tmp_dictionary.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Unable to open that file.  Try again.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return tmp_dictionary;
    }

    public static String wordladder(String word1, String word2, HashSet dictionary){
        StringBuffer res = new StringBuffer();
        LinkedBlockingQueue<Stack<String>> ladders = new LinkedBlockingQueue<Stack<String>>();
        Stack<String> W1 = new Stack<String>(), W2 = new Stack<String>() ;
        String w1, w2;
        HashSet<String> used_set = new HashSet<String>();
        boolean find = false;
        int size = word1.length();
        W1.push(word1);
        ladders.add(W1);

        while (!find && !ladders.isEmpty()) {
            W1 = ladders.poll();
            w1 = W1.peek();
            for (int i = 0; i < size; i++) {
                if (!find) {
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (!find) {
                            W2 = (Stack<String>) W1.clone();
                            w2 = w1;
                            StringBuffer tmp = new StringBuffer(w2);
                            tmp.setCharAt(i, j);
                            w2 = tmp.toString();
                            if (!used_set.contains(w2)) {
                                if (w2.equals(word2)) {
                                    find = true;
                                    W2.push(w2);
                                    System.out.println("A ladder from " + word2 + " back to " + word1 + ":" );
                                    while (!W2.isEmpty()) {
                                        res.append(W2.pop());
                                        res.append(' ');
                                    }
                                }
                                if (dictionary.contains(w2)) {
                                    W2.push(w2);
                                    ladders.add(W2);
                                    used_set.add(w2);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!find)
            System.out.println("No word ladder found from " + word2 + " back to " + word1 + ".");
        return res.toString();
    }
}
