package java;

import ch.bbw.zork.Command;

import static org.junit.Assert.*;

import ch.bbw.zork.CommandWords;
import ch.bbw.zork.Parser;
import ch.bbw.zork.Room;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.containsString;

public class test {

// Command Klasse Tests


    @Test // Test für den ersten Konstruktor der Command Klasse nur mit einem Wort
    public void testCommandKonstruktor1() {
        Command testCommand = new Command("test");
        assert ("test".equals(testCommand.getCommandWord()));
    }

    @Test // Test für den zweiten Konstruktor der Command Klasse  mit zwei Wörtern
    public void testCommandKonstruktor2() {
        Command testCommand = new Command("test", "test2");
        assert ("test".equals(testCommand.getCommandWord()) && "test2".equals(testCommand.getSecondWord()));
    }

    @Test // Test der IsUnknown Funktion der Command Klasse.Soll  die verfügbarkeit überprüfen eines Commands
    public void testIsUnkown() {
        Command testCommand = new Command("help");
        assertEquals(true, testCommand.isUnknown());
        Command testCommand1 = new Command("notValid");
        assertEquals(false, testCommand1.isUnknown());
    }

    @Test // Test der HasSecondWord Funktion der Command Klasse.Soll überprüfen ob der Command ein oder zwei Wörter hat
    public void testHasSecondWord() {
        Command testCommand = new Command("help");
        assertEquals(true, testCommand.hasSecondWord());
        Command testCommand1 = new Command("notValid");
        assertEquals(false, testCommand1.hasSecondWord());
    }
// CommandWords Klasse

    @Test // Test der IsCommand Funktion der CommandWords Klasse, diese soll kontrollieren ob es ein Command ist
    public void testIsCommand() {
        CommandWords commandWords = new CommandWords();
        assertEquals(true, commandWords.isCommand("go"));
        CommandWords commandWords2 = new CommandWords();
        assertEquals(false, commandWords.isCommand("gono"));
    }

    @Test // Test der ShowAll Funktion der CommandWords Klasse, diese  soll alle Commands anzeigen
    public void testShowAll() {
        CommandWords commandWords = new CommandWords();
        for (int c = 0; c < commandWords.getValidCommands().size(); c++) {
            assertTrue(commandWords.showAll().contains(commandWords.getValidCommands().get(c)));
        }
    }

    // Game: nicht getestet
// Parser
    @Test // Testet den Konstruktor der Parser Klasse
    public void testKonstruktorParser() {
        Parser parser = new Parser(new ByteArrayInputStream("go north".getBytes()));
        assert (new ByteArrayInputStream("go north".getBytes()).equals(parser.getInputStream()));
    }

    @Test // Testet die GetCommand Methode
    public void testGetCommand() {
        ByteArrayInputStream in = new ByteArrayInputStream("go north".getBytes());
        Parser parser = new Parser(in);
        Command command = parser.getCommand();
        assertEquals("go north", command.getCommandWord() + command.getSecondWord());

        ByteArrayInputStream in1 = new ByteArrayInputStream("help".getBytes());
        Parser parser1 = new Parser(in);
        Command command1 = parser.getCommand();
        assertEquals("help", command.getCommandWord());
    }

    //Room
    @Test
    public void testKonstruktorRoom() {
        Room room = new Room("name","this is a description");
        assert ("this is a description".equals(room.shortDescription()));
    }

    @Test
    public void testExitString() {
        Room room = new Room("name","desc.");
        Room room1 = new Room("name","desc.");
        Room room2 = new Room("name","desc.");
        Room room3 = new Room("name", "desc.");
        Room room4 = new Room("name","desc.");
        room.setExits(room1, room2, room3, room4);

        assertEquals("Exits:east south north west", room.exitString());
        assertTrue(
                room.exitString().contains("north") &&
                        room.exitString().contains("south") &&
                        room.exitString().contains("east") &&
                        room.exitString().contains("west")
        );
    }

    @Test
    public void testNextRoom() {
        Room room = new Room("name","desc.");
        Room room1 = new Room("name","desc");
        assertNull(room.nextRoom("north"));

        room.setExits(room1, null, null, null);
        assertSame(room1, room.nextRoom("north"));
    }
    @Test
    public void testWin(){
    }

}

