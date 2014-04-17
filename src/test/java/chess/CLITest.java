package chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Basic unit tests for the CLITest
 */
@RunWith(MockitoJUnitRunner.class)
public class CLITest {

    @Mock
    private PrintStream testOut;

    @Mock
    private InputStream testIn;

    /**
     * Make sure the CLI initially prints a welcome message
     */
    @Test
    public void testCLIWritesWelcomeMessage() {
        new CLI(testIn, testOut);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(testOut, times(1)).println(captor.capture());

        String message = captor.getValue();

        assertTrue("The CLI should initially print a welcome message", message.startsWith("Welcome"));
    }

    /**
     * Test that the CLI can initially accept input
     */
    @Test
    public void testHelpCommand() throws Exception {
        runCliWithInput("help");

        List<String> output = captureOutput();
        assertEquals("Should have 13 output calls", 13, output.size());
    }

    @Test
    public void testNewCommand() throws Exception {
        runCliWithInput("new");
        List<String> output = captureOutput();

        assertEquals("Should have had 6 calls to print strings", 6, output.size());
        assertEquals("It should have printed the board first", 721, output.get(2).length());
        assertEquals("It should have printed the board again", 721, output.get(4).length());
    }

    @Test
    public void testBoardCommand() throws Exception {
        runCliWithInput("new", "board");
        List<String> output = captureOutput();

        assertEquals("Should have had 9 output calls", 9, output.size());
        assertEquals("It should have printed the board three times", output.get(2), output.get(4));
    }

    @Test
    public void testListCommand() throws Exception {
        runCliWithInput("list");
        List<String> output = captureOutput();

        assertEquals("Should have had 27 output calls", 27, output.size());
        assertEquals("First pawn one step move", "\ta2 a3", output.get(5));
        assertEquals("First pawn two step move", "\ta2 a4", output.get(6));
        assertEquals("Knight left move", "\tb1 a3", output.get(7));
        assertEquals("Knight right move", "\tb1 c3", output.get(8));
    }

    @Test
    public void testMoveCommandIncorrectSignature() throws Exception {
        runCliWithInput("move");
        List<String> output = captureOutput();
        assertEquals("Incorrect move command", "Incorrect command format. Use: 'move <colrow> <colrow>'", output.get(4));
    }

    @Test
    public void testMoveCommand() throws Exception {
        runCliWithInput("move e2 e4");
        List<String> output = captureOutput();
        assertEquals("Move done successfully", "White's Move Done", output.get(4));
    }

    @Test
    public void testMoveCommandIncorrectPosition() throws Exception {
        runCliWithInput("move e5 e6");
        List<String> output = captureOutput();
        assertEquals("Position e5 is free", "Move from e5 to e6 is not acceptable", output.get(4));
    }

    private List<String> captureOutput() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        // 9 times means we printed Welcome, the input prompt twice, and the 'help' screen
        verify(testOut, atLeastOnce()).println(captor.capture());

        return captor.getAllValues();
    }

    private CLI runCliWithInput(String... inputLines) {
        StringBuilder builder = new StringBuilder();
        for (String line : inputLines) {
            builder.append(line).append(System.getProperty("line.separator"));
        }

        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        CLI cli = new CLI(in, testOut);
        cli.startEventLoop();

        return cli;
    }
}
