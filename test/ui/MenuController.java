package ui;

import main.java.com.jabberpoint.command.Command;
import main.java.com.jabberpoint.factory.CommandFactory;
import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.ui.MenuController;
import main.java.com.jabberpoint.ui.SlideViewerFrame;
import main.java.com.jabberpoint.util.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuControllerTest {

    private MenuController menuController;
    private CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        // Prepare
        Frame frame = new Frame();
        Presentation presentation = Presentation.getInstance();
        menuController = new MenuController(frame, presentation);
        commandFactory = CommandFactory.getInstance();
    }

    @Test
    void executeNewCommand_callFunction_actionPerformed() {
        // Prepare
        Command command = commandFactory.createNewCommand();
        ActionListener listener = menuController.createActionListener(command);

        // Act
        listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "test"));

        // Assert
        // This test only checks that the command executes without errors.
        // It's difficult to assert specific results without mock objects.
    }

    @Test
    void executeSaveCommand_callFunction_actionPerformed() {
        // Prepare
        SlideViewerFrame.getInstance("test", Presentation.getInstance());
        Command command = commandFactory.createSaveCommand(MenuController.SAVEFILE);
        ActionListener listener = menuController.createActionListener(command);

        // Act
        listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "test"));

        // Assert
        // This test only checks that the command executes without errors.
        // It's difficult to assert specific results without mock objects.
    }


    @Test
    void executeNextSlideCommand_callFunction_actionPerformed() {
        // Prepare
        Command command = commandFactory.createNextSlideCommand();
        ActionListener listener = menuController.createActionListener(command);

        // Act
        listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "test"));

        // Assert
        // This test only checks that the command executes without errors.
        // It's difficult to assert specific results without mock objects.
    }

    @Test
    void executePrevSlideCommand_callFunction_actionPerformed() {
        // Prepare
        Command command = commandFactory.createPrevSlideCommand();
        ActionListener listener = menuController.createActionListener(command);

        // Act
        listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "test"));

        // Assert
        // This test only checks that the command executes without errors.
        // It's difficult to assert specific results without mock objects.
    }

    @Test
    void executeGotoCommand_callFunction_actionPerformed() {
        // Prepare
        Command command = commandFactory.createGotoCommand("Go to page NR:");
        ActionListener listener = menuController.createActionListener(command);

        // Act
        listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "test"));

        // Assert
        // This test only checks that the command executes without errors.
        // It's difficult to assert specific results without mock objects.
    }

    @Test
    void executeAboutCommand_callFunction_actionPerformed() {
        // Prepare
        Command command = commandFactory.createAboutCommand();
        ActionListener listener = menuController.createActionListener(command);

        // Act
        listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "test"));

        // Assert
        // This test only checks that the command executes without errors.
        // It's difficult to assert specific results without mock objects.
    }
}