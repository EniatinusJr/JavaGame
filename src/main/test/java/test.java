import ch.bbw.zork.Command;
import static  org.junit.Assert.*;
import org.junit.Test;
public class test {

    /* Needs testing:
    *       Command Klasse
    * -isUnknown + hasSecondWord
    *       CommandWords */

@Test public void testCommandKonstruktor1() {
    Command testCommand = new Command("test");
    assert ("test".equals(testCommand.getCommandWord()));
}
@Test public void testCommandKonstruktor2() {
    Command testCommand = new Command("test","test2");
    assert ("test".equals(testCommand.getCommandWord()) && "test2".equals(testCommand.getSecondWord()));
}
@Test public void testIsUnkown(){
    Command testCommand = new Command("help");
    assertEquals(true, testCommand.isUnknown());
    Command testCommand1 = new Command("notValid");
    assertEquals(false,testCommand1.isUnknown());
}



    /*
    * -isCommand
    * -showAll
    *       Game
    * -play
    *-processCommand
    *-goRoom
    *       Parser
    * -KonstrukorParser
    *-getCommand
    *-showCommands
    *       Room
    *-Konstruktor Room
    *-setExits
    *-shortDescription
    *-longDescription
    *-exitString
n    *-extRoom*/
}
