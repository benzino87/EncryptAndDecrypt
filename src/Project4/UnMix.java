package Project4;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**************************************************************************
 * @author Jason Bensel, Braedin Hendrickson
 * @version Project 4, Encrypt/Decrypt
 * UNMIX: Uses a text file to decrypt the encrypted message given
 *************************************************************************/
public class UnMix implements IUnMix {
    /** Key: holds all operations for undoing encryption **/
    private LinkedList key = new LinkedList();
    /** Helps decrypt message **/
    private Mix mix = new Mix();

    /**********************************************************************
     * Method to load the text file and save the incoming parameters into
     * a linked list
     * @param filename desired file name to be loaded
     * @throws ParseException problem loading file
     * @throws IOException problem loading file
     *********************************************************************/
    public void loadTextFile(String filename) throws ParseException,
            IOException {
        // open the data file
        try {
            Scanner fileReader = new Scanner(new File(filename));
            //read file line by line and store data into node
            while (fileReader.hasNext()) {
                String scanMessageParts = fileReader.nextLine();
                Node temp = new Node();
                temp.setData(scanMessageParts);
                key.insertTop(temp.getData());
            }
        } catch (IOException ioe) {
        }
    }

    /**********************************************************************
     * Handles all operations for decrypting an encrypted message.
     * @param filename desired filename to be loaded
     * @param mixedMessage correct mixed message
     * @return unmixed (decrypted message)
     *********************************************************************/
    @Override
    public String unMixUsingFile(String filename, String mixedMessage) {
        String message = "";
        String command = "";
        //Currently only allowed one decrypt per console instance
        if (mix.getOriginal().head != null) {
            message = "You can currently only decrypt one message per " +
                    "console. [check back for future updates]";
            return message;
        }
        mix.setInitialMessage(mixedMessage);
        try {
            loadTextFile(filename);

        } catch (ParseException e) {
            message = "Something went wrong";
            return message;
        } catch (IOException e) {
            message = "Something went wrong";
            return message;
        }
        //Begin to read the key
        Node temp;
        temp = key.head;
        while (temp != null) {
            command = temp.getData().toString();
            //helper class break apart string input
            unMixHelper(command);
            temp = temp.getNext();
        }
        //return the original decrypted message
        message = mix.getOriginal().getList().toString();
        return message;
    }

    /**********************************************************************
     * Helper method that handles all of the message decryption by breaking
     * apart the key and appropriately assigning new variables
     * @param command input to be processed and converted
     *********************************************************************/
    public void unMixHelper(String command) {
        //break apart the input
        char first = command.charAt(0);
        String size = command.substring(2);
        //check for remove
        if (first == 'r') {
            int count = 0;
            while(!size.equals(" ")){
                count++;
                break;
            }
            //convert to insert
            if(count == 1){
                String temp = size.substring(0, 1);
                String msg = size.substring(count+1);
                String cmd = "b " + msg + " " + temp;
                mix.processCommand(cmd);
            }else {
                String temp = size.substring(0, count - 1);
                String msg = size.substring(count + 1);
                String cmd = "b " + msg + " " + temp;
                mix.processCommand(cmd);
            }
        }
        //check for insert and convert to remove
        if (first == 'b') {
            String pos = command.substring(2);
            String cmd = "r "+ pos;
            mix.processCommand(cmd);
        }
        //check for swap and reverse the swap
        if (first == 'w') {
            String[] split = command.split(" ");
            String cmd = "w " + split[2] + " " + split[1];
            mix.processCommand(cmd);
        }
        //check for cut
        if (first == 'x') {
            //get size of cut
            int count = 0;
            while(!size.equals(" ")){
                count++;
                break;
            }
            //special case for substring if count is one
            if(count == 1){
                String temp = size.substring(0, 1);
                String msg = size.substring(count + 1);
                for (int i = msg.length() - 1; i >= 0; i--) {
                    char pos = msg.charAt(i);
                    String cmd = "b " + pos + " " + temp;
                    mix.processCommand(cmd);
                    }
//                String temp = size.substring(0, 1);
//                String msg = size.substring(count+1);
//                String cmd = "b " + msg + " " + temp;
//                mix.processCommand(cmd);
            }else {
                String temp = size.substring(0, count - 1);
                String msg = size.substring(count + 1);
                if (msg.length() > 1) {
                    for (int i = msg.length() - 1; i >= 0; i--) {
                        char pos = msg.charAt(i);
                        String cmd = "b " + pos + " " + temp;
                        mix.processCommand(cmd);
                    }
                } else {
                    String cmd = "b " + msg + " " + temp;
                    mix.processCommand(cmd);
                }
            }
        }
        //check for paste and remove
        if (first == 'p') {
            String[] split = command.split(" ");
            if(split.length < 2){
                System.exit(1);
            }
            //one = starting position
            int one = intConverter(split[1]);
            //two = paste size
            int two = intConverter(split[2]);
            //continuously remove until paste size
            for(int i = 0; i < two; i++) {
                if(one > mix.getOriginal().count()){
                    one = mix.getOriginal().count();
                }
                String cmd = "r " + one;
                mix.processCommand(cmd);
            }
        }
        //check for append to end and convert to remove
        if (first == 'a') {
            //check for append size
            int pos = intConverter(command.substring(2));
            //check for message size
            int count = mix.getOriginal().count();
            for(int i = 0; i < pos; i++){
                String cmd = "r "+count;
                mix.processCommand(cmd);
            }
        }
    }

    /**********************************************************************
     * Helper method to safely convert user input to an integer
     * @param input string representation of number
     * @return int representation of number
     *********************************************************************/
    private int intConverter(String input) {
        int number = -1;
        try {
            number = Integer.parseInt(input);
            return number;
        } catch (NumberFormatException nfe) {
            return number;
        }
    }
    public Mix getMix(){
        return mix;
    }

}

