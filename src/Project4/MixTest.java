package Project4;


import org.junit.Assert;
import org.junit.Test;

/**************************************************************************
 * @author Jason Bensel, Braedin Hendrickson
 * @version Project 4, Encrypt/Decrypt
 * UNMIX: Uses a text file to decrypt the encrypted message given
 *************************************************************************/
public class MixTest {
    private Mix mixTest = new Mix();

    @Test
    public void processInsertingMessage(){
        String s = "e message";
        mixTest.processCommand(s);
        Assert.assertEquals("message", mixTest.getOriginal().getList());
    }
    @Test
    public void processRemovingBeginningMessage(){
        mixTest.setInitialMessage("message");
        String s = "r 1";
        mixTest.processCommand(s);
        Assert.assertEquals("essage", mixTest.getOriginal().getList());
    }
    @Test
    public void processRemovingEndOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "r 7";
        mixTest.processCommand(s);
        Assert.assertEquals("messag", mixTest.getOriginal().getList());
    }
    @Test
    public void processRemovingMiddleOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "r 3";
        mixTest.processCommand(s);
        Assert.assertEquals("mesage", mixTest.getOriginal().getList());
    }
    @Test
    public void processMultipleRemovesBeginningOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "r 1";
        mixTest.processCommand(s);
        mixTest.processCommand(s);
        Assert.assertEquals("ssage", mixTest.getOriginal().getList());
    }
    @Test
    public void processMultipleRemovesEndOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "r 7";
        String r = "r 6";
        mixTest.processCommand(s);
        mixTest.processCommand(r);
        Assert.assertEquals("messa", mixTest.getOriginal().getList());
    }
    @Test
    public void processSwapWithBeginningOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "w 1 2";
        mixTest.processCommand(s);
        Assert.assertEquals("emssage", mixTest.getOriginal().getList());
    }
    @Test
    public void processSwapWithEndOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "w 6 7";
        mixTest.processCommand(s);
        Assert.assertEquals("messaeg", mixTest.getOriginal().getList());
    }
    @Test
    public void processSwapRandom(){
        mixTest.setInitialMessage("message");
        String s = "w 2 6";
        mixTest.processCommand(s);
        Assert.assertEquals("mgssaee", mixTest.getOriginal().getList());
    }
    @Test
    public void processSwapWithBeginningAndEnd(){
        mixTest.setInitialMessage("message");
        String s = "w 1 7";
        mixTest.processCommand(s);
        Assert.assertEquals("eessagm", mixTest.getOriginal().getList());
    }
    @Test
    public void processInsertBeginningOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "b c 1";
        mixTest.processCommand(s);
        Assert.assertEquals("cmessage", mixTest.getOriginal().getList());
    }
    @Test
    public void processInsertingNearEndOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "b c 7";
        mixTest.processCommand(s);
        Assert.assertEquals("messagce", mixTest.getOriginal().getList());
    }
    @Test
    public void processInsertingAtEndOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "b c 8";
        mixTest.processCommand(s);
        Assert.assertEquals("messagec", mixTest.getOriginal().getList());
    }
    @Test
    public void processInsertingMiddleOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "b c 4";
        mixTest.processCommand(s);
        Assert.assertEquals("mescsage", mixTest.getOriginal().getList());
    }
    @Test
    public void processCutBeginningOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "x 1 4";
        mixTest.processCommand(s);
        Assert.assertEquals("age", mixTest.getOriginal().getList());
    }
    @Test
    public void processCutEndOfMessage(){
        mixTest.setInitialMessage("message");
        String s= "x 4 7";
        mixTest.processCommand(s);
        Assert.assertEquals("mes", mixTest.getOriginal().getList());
    }
    @Test
    public void processCutMiddleOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "x 2 4";
        mixTest.processCommand(s);
        Assert.assertEquals("mage", mixTest.getOriginal().getList());
    }
    @Test
    public void processPasteBeginningOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "c 1 3";
        String p = "p 1";
        mixTest.processCommand(s);
        mixTest.processCommand(p);
        Assert.assertEquals("mesmessage", mixTest.getOriginal().getList());
    }
    @Test
    public void processPasteEndOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "c 5 7";
        String p = "p 8";
        mixTest.processCommand(s);
        mixTest.processCommand(p);
        Assert.assertEquals("messageage", mixTest.getOriginal().getList());
    }
    @Test
    public void processPasteMiddleOfMessage(){
        mixTest.setInitialMessage("message");
        String s = "c 3 5";
        String p = "p 2";
        mixTest.processCommand(s);
        mixTest.processCommand(p);
        Assert.assertEquals("mssaessage", mixTest.getOriginal().getList());
    }
    @Test
    public void checkClipboardWhenCopiedBeginning(){
        mixTest.setInitialMessage("message");
        String s = "c 1 3";
        mixTest.processCommand(s);
        Assert.assertEquals("mes", mixTest.getClipBoard().getList());
    }
    @Test
    public void checkClipboardWhenCopiedEnd(){
        mixTest.setInitialMessage("message");
        String s = "c 5 7";
        mixTest.processCommand(s);
        Assert.assertEquals("age", mixTest.getClipBoard().getList());
    }
    @Test
    public void checkClipboardWhenCopiedMiddle(){
        mixTest.setInitialMessage("message");
        String s = "c 3 5";
        mixTest.processCommand(s);
        Assert.assertEquals("ssa", mixTest.getClipBoard().getList());
    }
    @Test
    public void checkClipboardWhenCutBeginning(){
        mixTest.setInitialMessage("message");
        String s = "x 1 3";
        mixTest.processCommand(s);
        Assert.assertEquals("mes", mixTest.getClipBoard().getList());
    }
    @Test
    public void checkClipboardWhenCutEnd(){
        mixTest.setInitialMessage("message");
        String s = "x 5 7";
        mixTest.processCommand(s);
        Assert.assertEquals("age", mixTest.getClipBoard().getList());
    }
    @Test
    public void checkClipboardWhenCutMiddle(){
        mixTest.setInitialMessage("message");
        String s = "x 3 5";
        mixTest.processCommand(s);
        Assert.assertEquals("ssa", mixTest.getClipBoard().getList());
    }
    @Test
    public void checkMessageForAppend(){
        mixTest.setInitialMessage("message");
        String s = "a 123456789";
        mixTest.processCommand(s);
        Assert.assertEquals("message123456789",
                mixTest.getOriginal().getList());
    }

}
