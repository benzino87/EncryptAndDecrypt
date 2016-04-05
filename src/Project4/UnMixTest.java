package Project4;

import org.junit.Assert;
import org.junit.Test;

/**************************************************************************
 *
 * @author Jason Bensel, Braedin Hendrickson
 *@version Project 4, Encrypt/Decrypt
 *
 *************************************************************************/
public class UnMixTest {
    private UnMix unMixTest = new UnMix();

    @Test
    public void processUndoRemoveAtBeginning(){
        String s = unMixTest.unMixUsingFile("testrb.txt", "essage");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoRemoveAtEnd(){
        String s = unMixTest.unMixUsingFile("testre.txt", "messag");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoRemoveAtMiddle(){
        String s = unMixTest.unMixUsingFile("testrm.txt", "mesage");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoInsertAtBeginning(){
        String s = unMixTest.unMixUsingFile("testib.txt", "smessage");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoInsertAtMiddle(){
        String s = unMixTest.unMixUsingFile("testim.txt", "messsage");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoInsertAtEnd(){
        String s = unMixTest.unMixUsingFile("testie.txt", "messages");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCutAtBeginning(){
        String s = unMixTest.unMixUsingFile("testcb.txt", "age");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCutAtEnd(){
        String s = unMixTest.unMixUsingFile("testce.txt", "mes");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCutAtMiddle(){
        String s = unMixTest.unMixUsingFile("testcm.txt", "mge");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCopyPasteAtBeginning(){
        String s = unMixTest.unMixUsingFile("testcppb.txt", "messmessage");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCopyPasteAtEnd(){
        String s = unMixTest.unMixUsingFile("testcppe.txt", "messagemess");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCopyPasteAtMiddle(){
        String s = unMixTest.unMixUsingFile("testcppm.txt", "mesmesssage");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCutPasteAtBeginning(){
        String s = unMixTest.unMixUsingFile("testctpb.txt", "essamge");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCutPasteAtEnd(){
        String s = unMixTest.unMixUsingFile("testctpe.txt", "mgeessa");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoCutPasteAtMiddle(){
        String s = unMixTest.unMixUsingFile("testctpm.txt", "ssamege");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoSwapBeginning(){
        String s = unMixTest.unMixUsingFile("testwb.txt", "sesmage");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoSwapEnd(){
        String s = unMixTest.unMixUsingFile("testwe.txt", "meesags");
        Assert.assertEquals("message", s);
    }
    @Test
    public void processUndoSwapBothEnds(){
        String s = unMixTest.unMixUsingFile("testwbe.txt", "eessagm");
        Assert.assertEquals("message", s);
    }
    @Test
    public void checkForProperUndoRemoveMiddle(){
        unMixTest.getMix().setInitialMessage("mescsage");
        String s = "b 4";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoRemoveBeginning(){
        unMixTest.getMix().setInitialMessage("cmessage");
        String s = "b 1";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoRemoveEnd(){
        unMixTest.getMix().setInitialMessage("messagec");
        String s = "b 1231";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoInsertBeginning(){
        unMixTest.getMix().setInitialMessage("essage");
        String s = "r 1 m";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoInsertEnd(){
        unMixTest.getMix().setInitialMessage("messag");
        String s = "r 7 e";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoInsertMiddle(){
        unMixTest.getMix().setInitialMessage("mesage");
        String s = "r 4 s";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoSwapRandom(){
        unMixTest.getMix().setInitialMessage("mesgase");
        String s = "w 4 6";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoSwapBeginning(){
        unMixTest.getMix().setInitialMessage("sesmage");
        String s = "w 1 4";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoSwapEnd(){
        unMixTest.getMix().setInitialMessage("meseags");
        String s = "w 4 7";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoCut(){
        unMixTest.getMix().setInitialMessage("mege");
        String s = "x 3 ssa";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoCutBeginning(){
        unMixTest.getMix().setInitialMessage("sage");
        String s = "x 1 mes";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoCutEnd(){
        unMixTest.getMix().setInitialMessage("mess");
        String s = "x 7 ega";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoPaste(){
        unMixTest.getMix().setInitialMessage("mesmessage");
        String s = "p 4 3";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoPasteBeginning(){
        unMixTest.getMix().setInitialMessage("mesmessage");
        String s = "p 1 3";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoPasteEnd(){
        unMixTest.getMix().setInitialMessage("messagemes");
        String s = "p 8 3";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
    @Test
    public void checkForProperUndoAppend(){
        unMixTest.getMix().setInitialMessage("message123");
        String s = "a 3";
        unMixTest.unMixHelper(s);
        String c = unMixTest.getMix().getOriginal().getList();
        Assert.assertEquals("message", c);
    }
}
