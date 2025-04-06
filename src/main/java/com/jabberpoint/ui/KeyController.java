package main.java.com.jabberpoint.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import main.java.com.jabberpoint.command.Command;
import main.java.com.jabberpoint.factory.CommandFactory;
import main.java.com.jabberpoint.model.Presentation;

/**
 * Controls keyboard input for navigating through the presentation.
 * 
 * SOLID Principles:
 * - Single Responsibility Principle: Only responsible for handling keyboard input for presentation control.
 * - Open/Closed Principle: New key commands can be added without modifying existing code.
 * - Liskov Substitution Principle: Properly extends KeyAdapter without changing its behavior.
 * - Interface Segregation Principle: Only implements necessary methods from KeyAdapter.
 * - Dependency Inversion Principle: Depends on abstractions (Command interface) rather than concrete implementations.
 */
public class KeyController extends KeyAdapter
{
    private final Map<Integer, Command> keyCommands = new HashMap<>();
    private final Map<Character, Command> charCommands = new HashMap<>();

    /**
     * Creates a new KeyController with mapped keyboard commands.
     * 
     * @param presentation The presentation to control
     */
    public KeyController(Presentation presentation)
    {
        CommandFactory factory = CommandFactory.getInstance();
        factory.setPresentation(presentation);

        Command nextSlideCommand = factory.createNextSlideCommand();
        Command prevSlideCommand = factory.createPrevSlideCommand();
        Command exitCommand = factory.createExitCommand();

        this.keyCommands.put(KeyEvent.VK_PAGE_DOWN, nextSlideCommand);
        this.keyCommands.put(KeyEvent.VK_DOWN, nextSlideCommand);
        this.keyCommands.put(KeyEvent.VK_ENTER, nextSlideCommand);
        this.keyCommands.put(KeyEvent.VK_PAGE_UP, prevSlideCommand);
        this.keyCommands.put(KeyEvent.VK_UP, prevSlideCommand);

        this.charCommands.put('+', nextSlideCommand);
        this.charCommands.put('-', prevSlideCommand);
        this.charCommands.put('q', exitCommand);
        this.charCommands.put('Q', exitCommand);
    }

    /**
     * Handles key press events and executes the corresponding command.
     * 
     * @param keyEvent The key event to process
     */
    public void keyPressed(KeyEvent keyEvent)
    {
        int keyCode = keyEvent.getKeyCode();
        char keyChar = keyEvent.getKeyChar();

        Command command = this.keyCommands.get(keyCode);
        if (command != null)
        {
            command.execute();
            return;
        }

        command = this.charCommands.get(keyChar);
        if (command != null)
        {
            command.execute();
        }
    }
}
