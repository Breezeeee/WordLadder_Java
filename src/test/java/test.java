import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;


public class test {

    HashSet smalldict1 = main.read_Dictionary("smalldict1.txt");
    HashSet dictionary = main.read_Dictionary("dictionary.txt");
    HashSet wrong = main.read_Dictionary("wrong.txt");

    @Test
    public void read_Dictionary(){
        Assert.assertEquals(91, smalldict1.size());
        Assert.assertEquals(267751, dictionary.size());
        Assert.assertEquals(0, wrong.size());
    }

    @Test
    public void wordladder(){
        String res1 = "data date cate cade code ";
        String res2 = "play pray bray brak boak bork work ";
        String res3 = "awake aware sware stare starn stern steen steep sleep ";
        String res4 = "";
        Assert.assertEquals(res1, main.wordladder("code", "data", smalldict1));
        Assert.assertEquals(res2, main.wordladder("work", "play", dictionary));
        Assert.assertEquals(res3, main.wordladder("sleep", "awake", dictionary));
        Assert.assertEquals(res4, main.wordladder("metal", "azure", dictionary));
    }
}
