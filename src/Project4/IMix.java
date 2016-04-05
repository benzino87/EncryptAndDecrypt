package Project4;

/**************************************************************************
 * @author Jason Bensel, Braedin Hendrickson
 * @version Project 4, Encrypt/Decrypt
 *************************************************************************/
public interface IMix {

        /** Set original message into a linked-list of characters**/
        void setInitialMessage(String message);


        /** processes the given mix command and returns the current message after processing the mix command */
        String processCommand(String command);
}
