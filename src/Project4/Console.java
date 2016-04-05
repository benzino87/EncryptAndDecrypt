package Project4;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

/**************************************************************************
 *
 * @author Jason Bensel, Braedin Hendrickson
 * @version Project 4, Encrypt/Decrypt
 *
 *************************************************************************/
public class Console {

    /** Default commands **/
    private String[] commands = {"b", "r", "w", "x", "p", "c", "s", "Q",
            "e", "h", "dis", "clr", "size", "d"};
    /** Scanner to read user input **/
    private Scanner scanner;
    /** User input to be converted **/
    private String input;
    /** Takes user input to modify message **/
    private Mix mix;
    /** Decrypts message **/
    private UnMix unMix;


    /**********************************************************************
     * Constructor that initializes a new scanner
     * @param input Scanner input from the main method
     *********************************************************************/
    public Console(Scanner input){
        scanner = input;
        mix = new Mix();
        unMix = new UnMix();
        mix.getMessageParts().insertTop("clipboard end");
        System.out.println("Welcome to the Java encrypter, it's super " +
                "sophisticated and impossible to crack!\nTo enter the " +
                "message you want to encrypt, type: e 'message' " +
                "\nThen enter commands to encrypt the message how you" +
                " like\nDon't forget to save your files when done!\n" +
                "If you need help, type: 'h'");
    }

    /**********************************************************************
     * Helper method used by the readInput class to provide the user with
     * information based on the command they typed in
     * @param input desired command to be interpreted
     * @return information about the command the user wishes to know about
     *********************************************************************/
    private String helpCommands(String input){
        String helpCommand;
        switch(input){
            case "h Q":
                helpCommand ="Q: Quit the program";
                break;
            case "h b":
                helpCommand ="b: Insert char 'c' before position # " +
                        "format: b [c #]";
                break;
            case "h r":
                helpCommand ="r: Remove character at position # format: " +
                        "r [#]";
                break;
            case "h a":
                helpCommand ="a: Append a string at position # format: a" +
                        " [# message]";
                break;
            case "h w":
                helpCommand ="w: Switch characters at position & with # " +
                        "format: w [& #]";
                break;
            case "h x":
                helpCommand ="x: Cut to clipboard at starting at " +
                        "position & and ending at position # " +
                        "format x [& #]";
                break;
            case "h p":
                helpCommand ="p: Paste starting at position # format: " +
                        "p [#]";
                break;
            case "h c":
                helpCommand ="c: Copy to clipboard starting at position " +
                        "& and ending at position # format: c [& #]";
                break;
            case "h s":
                helpCommand ="s: Save, format: 's [filename]'";
                break;
            case "h e":
                helpCommand ="e: Enter a message you want to encrypt " +
                        "type: e [message]";
                break;
            case "h dis":
                helpCommand ="dis: Display current message";
                break;
            case "h clr":
                helpCommand ="clr: Erase message";
                break;
            case "h size":
                helpCommand ="size: Displays the number of characters " +
                        "in your message";
                break;
            case "h d":
                helpCommand ="d: (decrypt) enter the filename [name] " +
                        "which holds the key, then the mixed message " +
                        "[msg] format: unmix [[name] [msg]]";
                break;
            default:
                helpCommand = "Invalid command, to see the " +
                        "functionality of each command type: h [command]";
        }
        return helpCommand;
    }

    /**********************************************************************
     * Helper method for readInput method that provides the output for
     * the user
     * @return help all command that provides user with information
     *********************************************************************/
    private String helpAllCommand(){
        String response = "Q: Quit the program\nb: Insert char 'c' " +
                "before position # format: b [c #]\nr: Remove character " +
                "at position # format: r [#]\na: Append a string at " +
                "position # format: a [# message]\nw: Switch characters " +
                "at position & with # format: w [& #]\nx: Cut to clipboard" +
                " at starting at position & and ending at position # " +
                "format x [& #]\np: Paste from clipboard starting at" +
                " position # format: p [#]\nc: Copy to clipboard " +
                "starting at position & and ending at position # format:" +
                " c [& #]\ns: Save, format: s [filename]\ne: Enter a " +
                "message you want to encrypt type: e [message]\ndis: " +
                "Display current message\nclr: Erase message\nsize: " +
                "Displays the number of characters in your message\n" +
                "d: (decrypt) enter the filename [name] which holds the" +
                " key, then the mixed message [msg] format: " +
                "d [[name] [msg]]";
        return response;
    }

    /**********************************************************************
     * Handles all basic user input, displays appropriate message to
     * inform user of functionality.
     * @param scanner messages to be input
     * @return a response to user
     *********************************************************************/
    private String readInput(Scanner scanner) throws IOException,
            ParseException {
        String response = "";
        String help = "Here is a list of commands: ";
        String invalidInput = "Invalid input, type: 'h' to see a " +
                "list of commands";
        this.input = scanner.nextLine();

        //Check for no input
        if (input.length() == 0) {
            return invalidInput;
        }

        //Check for help/print input
        if (input.length() == 1) {
            if(input.equals("Q")){
                System.exit(1);
            }
            //Help command
            if (input.equals("h")) {
                for (int pos = 0; pos < commands.length; pos++) {
                    response = response + " " + "[" + commands[pos] + "]";
                }
                return help + response + '\n' +
                        "To see the description of each command type: " +
                        "h [command]\nTo see a description of all the " +
                        "commands type: h*";
            }
            return invalidInput;

        }

        //CHECK FOR ALL HELP COMMANDS

        if (input.length() > 1) {
            String temp = input.substring(0, 2);

            //Gets help for specific command
            if (temp.equals("h ")) {
                response = helpCommands(input);
                return response;
            }

            //Gets the help all command
            if (temp.equals("h*")) {
                return helpAllCommand();
            }
            //Send message to Mix class
        }

        //Display message
        if (input.equals("dis")) {
            if (mix.getOriginal().head == null) {
                response = "No message to display";
                return response;
            }
            return "Current message: \n" + mix.getOriginal().getList();
        }


        //Gives user the size of the message
        if (input.equals("size")) {
            if (mix.getOriginal().head == null) {
                response = "No message to display";
                return response;
            }
            return "Message size: " + mix.getOriginal().count();
        }

        //Clear message
        if (input.equals("clr")) {
            if (mix.getOriginal().head != null) {
                mix.getOriginal().clear();
                mix.getSavedcmd().clear();

                //NEED TO CLEAR CLIPBOARDS
                response = "Message has been deleted and commands have " +
                        "been removed";
                return response;
            }
            response = "There is no message to delete, to enter a" +
                    " message type: e [message]";
            return response;
        }


        //COMMANDS FOR ALTERING THE MESSAGE
        //b r c x p s w
        char first = input.charAt(0);
        if ((first == 'p' || first == 'r' || first == 'b' || first == 'c' ||
                first == 'x' || first == 'w' || first == 's') &&
                mix.getOriginal() == null) {
            response = "There is no message to alter";
            return response;
        }

        //Check for decrypt
        if (first == 'd') {
            String[] inputSplit = input.split(" ");
            if (inputSplit.length == 1) {
                response = "You must enter a file name";
                return response;
            }
            if (inputSplit.length == 2) {
                response = "You must enter a message";
                return response;
            }
            //Split the string apart to get necessary file name
            if (inputSplit.length >= 3) {
                int count = 0;
                int numOfSpaces = 0;
                String newInput = "";
                char[] inpArr = input.toCharArray();
                for (count = 0; count < input.length(); count++) {
                    if (inpArr[count] == ' ') {
                        numOfSpaces++;
                        if (numOfSpaces == 2) {
                            newInput = input.substring(count+1);
                            break;
                        }
                    }
                }
                //Send new information to the unmix class
                response = unMix.unMixUsingFile(inputSplit[1], newInput);
                String success = "If your decrypted message resembles the" +
                        " one entered, or if it doesn't make sense" +
                        ",\nthen the file was corrupted or you were given" +
                        " the wrong encrypted message.\n" +
                        "Otherwise SUCCESS!!";
                return response+"\n"+success;
            }
        }
        return mix.processCommand(input);
    }


    /**********************************************************************
     * Method to print all outputs of the scanner
     *********************************************************************/
    private void printOutputs() throws IOException, ParseException {
        System.out.println(readInput(scanner));
    }

    public static void main(String[] args) throws IOException,
            ParseException {
        Scanner scanner = new Scanner(System.in);
        Console c = new Console(scanner);
        while(scanner.hasNextLine()){
            c.printOutputs();
        }
    }


}
