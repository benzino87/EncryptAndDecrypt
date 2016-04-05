package Project4;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**************************************************************************
 * @author Jason Bensel, Braedin Hendrickson
 * @version Project 4, Mix class
 * Handles all user input to modify (encrypt) message
 *************************************************************************/

public class Mix implements IMix {
    /** Message to hold pieces of user input **/
    private String message;
    /** The actual message being altered **/
    private LinkedList original = new LinkedList();
    /** Holds pieces of message removed from original **/
    private LinkedList messageParts = new LinkedList();
    /** Clipboard to hold copies and cuts from original **/
    private LinkedList clipBoard = new LinkedList();
    /** Message given to user **/
    private String userMessage = "";
    /** Default invalid input user message **/
    private String invalidInput = "Invalid input, type: 'h' to see a " +
            "list of commands";
    /** Holds a the commands given STRICTLY for save(nothing else) **/
    // I know what you're thinking ITS ONLY USED FOR A TEXT FILE!!  =)
    private ArrayList<String> savedCmd = new ArrayList<>();

    /**********************************************************************
     * Method that initially sets the message as a linked list
     * @param message user input desired message
     *********************************************************************/
    @Override
    public void setInitialMessage(String message) {
        for (int po = 0; po < message.length(); po++) {
            original.insertBottom(message.charAt(po));
        }
    }

    /**********************************************************************
     * Helper method used by process command method to break apart the
     * cut command
     * @param command incoming cut command
     * @return user message
     *********************************************************************/
    private String handleX(String command) {
        String[] twoints = command.split(" ");
        //get starting position of cut
        int one = intConverter(twoints[1]);
        //get end position of cut
        int two = intConverter(twoints[2]);
        String tempCmd = "";
        //if intconverter failed user entered bad information
        if (one == -1 || two == -1) {
            return invalidInput;
        }
        //Check for valid inputs of x command
        if (one == 0) {
            userMessage = "The first number must have a minimum value " +
                    "of 1";
            return userMessage;
        }
        //cut at position one cannot be larger than two
        if (one >= two || one >= original.count()) {
            userMessage = "The first number is too large";
            return userMessage;
        }
        //cannot cut entire message
        if (one == 1 && two >= original.count()) {
            userMessage = "You cannot cut the entire clipboard.";
            return userMessage;
        }
        //If the second value is larger it uses the size of the list
        if (two > original.count()) {
            clipBoard = original.cut(one, original.count());
            //create appropriate user message
            userMessage = "Cut message to clipboard 1:" + clipBoard.
                    getList() +"\nCurrent message:" + original.getList();
            //create save command
            String tmpCmd = twoints[0] +" "+one+" "+clipBoard.getList();
            savedCmd.add(tmpCmd);
            //populate clipboard
            Node temp = clipBoard.head;
            while (temp != null) {
                messageParts.insertTop(temp.getData());
                temp = temp.getNext();


            }
            return userMessage;
        }
        //Other checks pass, does operation as given
        clipBoard = original.cut(one, two);
        //create appropriate user message
        userMessage = "Cut message to clipboard 1:" + clipBoard.getList() +
                "\nCurrent message:" + original.getList();
        //create save command
        String tmpCmd = twoints[0] +" "+one+" "+clipBoard.getList();
        savedCmd.add(tmpCmd);
        //populate clipboard
        Node temp = clipBoard.head;
        while (temp != null) {
            messageParts.insertTop(temp.getData());
            temp = temp.getNext();


        }
        return userMessage;
    }

    /**********************************************************************
     * Helper method used by process command method to break apart the
     * remove command
     * @param command incoming cut command
     * @return user message
     *********************************************************************/
    private String handleR(String command) {
        String tempCmd = "";
        //splits command
        int position = intConverter(command.substring(2));
        //uses int checker to see if valid input
        if (position == -1) {
            return invalidInput;
        }
        //check for invalid input of 0
        if (position == 0){
            userMessage = "Remove must have minimum value of 1";
            return userMessage;
        }
        //if message size is one, do not remove
        if (original.count() == 1) {
            userMessage = "You cannot remove the entire message";
            return userMessage;
        }
        //check if number entered is greater than message size
        if (position > original.count()) {
            Node tempNode = original.remove(original.count());
            //create appropriate user message
            userMessage = "Removed: '" + tempNode.getData() + "' " +
                    "at position: " + position + "\n" +
                    "Current message:" + original.getList();
            //create save command
            tempCmd = command.substring(0, 1)+" "+original.count()+" "
                    +tempNode.getData().toString();
            savedCmd.add(tempCmd);
            return userMessage;

        }
        //do operation as normal
        Node tempNode = original.remove(position);
        //create appropriate user message
        userMessage = "Removed: '" + tempNode.getData() + "' " +
                "at position: " + position + "\n" +
                "Current message:" + original.getList();
        //create save command
        tempCmd = command +" "+tempNode.getData().toString();
        savedCmd.add(tempCmd);


        return userMessage;

    }
    /**********************************************************************
     * Helper method used by process command method to break apart the
     * switch command
     * @param command incoming cut command
     * @return user message
     *********************************************************************/
    private String handleW(String command) {
        String[] twoints = command.split(" ");
        //get first swap position
        int one = intConverter(twoints[1]);
        //get second swap position
        int two = intConverter(twoints[2]);
        //use int check to make sure valid input
        if(one == -1 || two == -1){
            return invalidInput;
        }
        //check for invalid swap at 0
        if(one == 0 || two == 0){
            userMessage = "Swap must have a minimum value of 1";
            return userMessage;
        }
        //check to see if they're equal
        if(one == two){
            userMessage = "Unable to swap if both values are the same";
            return userMessage;
        }
        //check to see if they're both greater than message size
        if(one >= original.count() && two >= original.count()){
            userMessage = "Unable to swap two unknown values";
            return userMessage;
        }
        //check if first value is greater than message size and swap
        //character at value two with character at end of message
        if(one >= original.count()) {
            original.Switch(original.count(), two);
            //create save comand
            String cmd = "w "+original.count()+" "+two;
            savedCmd.add(cmd);
            //create appropriate user message
            userMessage = "Swapped character at position "+
                    original.count()+" with character at position "
                    +two+"\nCurrent message:" +
                    original.getList();
            return userMessage;
        }
        //check if second value is greater than message size and swap
        //character at value one with character at end of message
        if(two >= original.count()){
            original.Switch(one, original.count());
            //create save command
            String cmd = "w "+one+" "+original.count();
            savedCmd.add(cmd);
            //create appropriate user message
            userMessage = "Swapped character at position "+one+" with " +
                    "character at position "+original.count()+"\n" +
                    "Current message:" +
                    original.getList();
            return userMessage;
        }
        //perform operation as normal
        original.Switch(one, two);
        //create save command
        savedCmd.add(command);
        //create appropriate user message
        userMessage = "Swapped character at position "+one+" with " +
                "character at position "+two+"\nCurrent message:" +
                original.getList();
        return userMessage;
    }
    /**********************************************************************
     * Helper method used by process command method to break apart the
     * paste
     * @param command incoming cut command
     * @return user message
     *********************************************************************/
    private String handleP(String command) {
        String tempCmd = "";
        //split the command
        message = command.substring(2);
        //check for valid user input
        int position = intConverter(message);
        //cannot paste if clipboard is empty
        if(clipBoard.head == null) {
            userMessage = "Clipboard is empty";
            return userMessage;
        }
        //soft exit for bad input
        if(position == -1){
            return invalidInput;
        }
        //check for invalid paste value
        if(position == 0){
            userMessage = "Paste must have the minimum value of 1";
            return userMessage;
        }
        //if user input is larger than message size, paste to end
        if(position > original.count()){
            original.paste(clipBoard, original.count()+1);
            //create appropriate user message
            userMessage = "Pasted message from Clipboard to end of " +
                    "message\nCurrent message:"+ original.getList();
            int pasteSize = clipBoard.count();
            //create save command
            tempCmd = command.substring(0,1)+" "+original.count()+" "
                    +pasteSize;
            savedCmd.add(tempCmd);
            return userMessage;
        }
        //perform paste as normal
        original.paste(clipBoard, position);
        //create appropriate user message
        userMessage = "Pasted message from Clipboard to position: "+
                position+"\nCurrent message:" + original.getList();
        int pasteSize = clipBoard.count();
        //create save command
        tempCmd = command+" "+pasteSize;
        savedCmd.add(tempCmd);
        return userMessage;
    }
    /**********************************************************************
     * Helper method used by process command method to break apart the
     * insert command
     * @param command incoming cut command
     * @return user message
     *********************************************************************/
    private String handleB(String command){
        String split[] = command.split(" ");
        String two = command.substring(2, 3);
        int three = intConverter(command.substring(4));
        //check for improper user input
        if(split.length <= 2){
            return invalidInput;
        }
        //soft exit for bad input
        if(three == -1){
            userMessage = "There number entered is invalid";
            return userMessage;
        }
        //check for invalid insert input
        if(three == 0){
            userMessage = "Insert must have a minimum value of 1";
            return userMessage;
        }
        //if insert is larger than message size, insert character at end
        if(three > original.count()) {
            original.insertBottom(two);
            //create appropriate user message
            userMessage = "Inserted: " + two + " at position: " +
                    original.count()+"\n"+"Current message:"
                    + original.getList();
            //create save command
            String input = command.substring(0, 1);
            String pos = "" + original.count();
            String save = input + " " + pos;
            savedCmd.add(save);
            return userMessage;
        }
        //perform insert as normal
        original.insert(two, three);
        //create appropriate user message
        userMessage = "Inserted: " + two + " at position: " + three + "\n" +
                        "Current message:" + original.getList();
        //create save command
        String input = command.substring(0, 1);
        String pos = ""+three;
        String save = input+" "+pos;
        savedCmd.add(save);
        return userMessage;
    }
    /**********************************************************************
     * Helper method used by process command method to break apart the
     * copy command
     * @param command incoming cut command
     * @return user message
     *********************************************************************/
    private String handleC(String command){
        String tempCmd = "";
        //split command
        String[] commandSplit = command.split(" ");
        //start position of copy
        int start = intConverter(commandSplit[1]);
        //end position of copy
        int end = intConverter(commandSplit[2]);
        //soft exit if either are invalid
        if(start == -1 || end == -1){
            return invalidInput;
        }
        //check for starting position of 0
        if(start == 0){
            userMessage = "The first number must have a minimum value of 1";
            return userMessage;
        }
        //starting position cannot be same or greater than end
        if(start >= end || start >= original.count()){
            userMessage = "The first number is too large";
            return userMessage;
        }
        if(end > original.count()){
            userMessage = "Cannot copy entire message, Not sure why..." +
                    "Try a smaller number! =)";
//            clipBoard = original.copy(start, original.count());
//            //create user message
//            userMessage = "Copied message to Clipboard: " +
//                    clipBoard.getList()+"\nCurrent message:"
//                    + original.getList();
//            //create save command
//            tempCmd = commandSplit[0]+" "+start+" "+original.count()+
//                    " "+clipBoard.getList();
//            savedCmd.add(tempCmd);
            return userMessage;
        }
        //create clipboard
        clipBoard = original.copy(start, end);
        //create appropriate user message
        userMessage = "Copied message to Clipboard:" + clipBoard.getList()+
                "\nCurrent message:" + original.getList();
        //create save command
        tempCmd = command+" "+clipBoard.getList();
        savedCmd.add(tempCmd);
        return userMessage;

    }
    /**********************************************************************
     * Helper method used by process command method to break apart the
     * append command
     * @param command incoming cut command
     * @return user message
     *********************************************************************/
    private String handleA(String command){
        if(command.charAt(1)!= ' '){
            return invalidInput;
        }
        //split command
        command = command.substring(2);
        int size = command.length();
        //insert at end of the list, continue for length of substring
        for(int pos = 0; pos < size; pos++){
            original.insertBottom(command.charAt(0));
            command = command.substring(1);
        }
        //create save command
        String cmd = "a "+size;
        savedCmd.add(cmd);
        userMessage = "Message Entered:"+original.getList();
        return userMessage;
    }

    /**********************************************************************
     * Method that takes the user input and checks for the first character
     * value then uses one of the above helper commands to process the
     * given action
     * @param command incoming command
     * @return user message output
     *********************************************************************/
    @Override
    public String processCommand(String command) {
        char first = command.charAt(0);
        //Check first for enter or save
        if (first == 'e' || first == 's') {
            if (first == 'e') {
                message = command.substring(2);
                // Put it in a linkedList
                setInitialMessage(message);
                userMessage = "Message Entered:"+original.getList();
                return userMessage;
            }
            if (first == 's') {
                //break string apart
                message = command.substring(2);
                //check for existing message
                if(original.head == null){
                    userMessage = "There is no file to save";
                    return userMessage;
                }
                //save text file
                saveTextFile(message+".txt");
                userMessage = "File saved! ["+message+".txt]";
                return userMessage;
            }
        }
        //Prevent any operation is performed before there is a message
        if(original.head == null){
            userMessage = "There is no message to alter, to enter a " +
                    "message type: e [message]";
            return userMessage;
        }
        //insert command
        if (first == 'b') {
            return handleB(command);
        }
        //paste command
        if (first == 'p') {
            return handleP(command);
        }
        //remove command
        if(first == 'r') {
            return handleR(command);
        }
        //swap command
        if (first == 'w') {
            return handleW(command);
        }
        //cut command
        if (first == 'x') {
            return handleX(command);
        }
        //copy command
        if (first == 'c' ){
            return handleC(command);
        }
        //append command
        if (first == 'a'){
            return handleA(command);
        }
        //return default if none are met
        return invalidInput;
    }

    /**********************************************************************
     * private method to check for valid string conversion
     * @param input incoming string to be converted
     * @return int value based on string
     *********************************************************************/
    private int intConverter(String input){
        int number = -1;
        try {
            number = Integer.parseInt(input);
            return number;
        }catch (NumberFormatException nfe){
            return number;
        }
    }

    /**********************************************************************
     * Method to return the encrypted / decrypted message
     * @return Linkedlist representation of the message
     *********************************************************************/
    public LinkedList getOriginal(){
        return original;
    }

    /**********************************************************************
     * Method to return the parts of the message cut from the original
     * @return parts removed from the message
     *********************************************************************/
    public LinkedList getMessageParts(){
        return messageParts;
    }

    /**********************************************************************
     * Method to return the saved commands
     * @return saved commands
     *********************************************************************/
    public ArrayList getSavedcmd(){
        return savedCmd;
    }

    /**********************************************************************
     * Method to return the current state of the clipboard
     * @return current clipboard
     *********************************************************************/
    public LinkedList getClipBoard(){
        return clipBoard;
    }

    /**********************************************************************
     * Method to save required information to unmix the message
     * @param filename desired filename
     *********************************************************************/
    public void saveTextFile(String filename) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter
                    (new FileWriter(filename)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for(String s: savedCmd){
            out.println(s);
        }
        out.close();
    }

}
