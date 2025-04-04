package factory;


import main.java.com.jabberpoint.command.*;
import main.java.com.jabberpoint.factory.CommandFactory;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.ui.SlideViewerFrame;
import main.java.com.jabberpoint.util.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    private CommandFactory commandFactory;


    @BeforeEach
    void setUp() {
        // Prepare
        Presentation presentation = Presentation.getInstance();
        this.commandFactory = CommandFactory.getInstance();
        this.commandFactory.setPresentation(presentation);
        this.commandFactory.setFrame(SlideViewerFrame.getInstance("Test", presentation));
    }

    @Test
    void getInstance_returnsSingleton_sameInstance() {
        // Prepare
        CommandFactory factory1 = CommandFactory.getInstance();
        CommandFactory factory2 = CommandFactory.getInstance();

        // Assert
        assertSame(factory1, factory2);
    }

    @Test
    void createNextSlideCommand_returnsNextSlideCommand_correctInstance() {
        // Act
        Command command = commandFactory.createNextSlideCommand();

        // Assert
        assertTrue(command instanceof NextSlideCommand);
    }

    @Test
    void createPrevSlideCommand_returnsPrevSlideCommand_correctInstance() {
        // Act
        Command command = commandFactory.createPrevSlideCommand();

        // Assert
        assertTrue(command instanceof PrevSlideCommand);
    }

    @Test
    void createExitCommand_returnsExitCommand_correctInstance() {
        // Act
        Command command = commandFactory.createExitCommand();

        // Assert
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    void createOpenCommand_returnsOpenCommand_correctInstance() {
        // Act
        Command command = commandFactory.createOpenCommand();

        // Assert
        assertTrue(command instanceof OpenCommand);
    }

    @Test
    void createSaveCommand_returnsSaveCommand_correctInstance() {
        // Act
        Command command = commandFactory.createSaveCommand("test.xml");

        // Assert
        assertTrue(command instanceof SaveCommand);
    }

    @Test
    void createNewCommand_returnsNewCommand_correctInstance() {
        // Act
        Command command = commandFactory.createNewCommand();

        // Assert
        assertTrue(command instanceof NewCommand);
    }

    @Test
    void createAboutCommand_returnsAboutCommand_correctInstance() {
        // Act
        Command command = commandFactory.createAboutCommand();

        // Assert
        assertTrue(command instanceof AboutCommand);
    }

    @Test
    void createGotoCommand_returnsGotoCommand_correctInstance() {
        // Act
        Command command = commandFactory.createGotoCommand("Go to slide:");

        // Assert
        assertTrue(command instanceof GotoCommand);
    }
}