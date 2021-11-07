package codebreaker;
/*
* I have created input and output Windows applications which are a custom made java windows designed to take input from user or display some information as output to the user. 
* They have been designed and written by using Java Swing Components like Jarea, Jlabel, Jbutton, Jframe etc. 
* I have also used notifyAll() method to make an application wait for users input.
*
* - You may use this Class in your application for non commercial use if you wish but you have to keep this comment below :
*
* @author Michal Switala
* Copyright © 2021 belongs solely to Michal Switala. 
* You can reach me out on : https://github.com/michaelPro89
*/
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;

//stage is our subclass or extended class and Jframe is superclass or also so called "base class"
//A subclass inherits all the members (fields, methods, and nested classes) from its superclass
//so we can use their methods for example. 
//Constructors are not members, so they are not inherited by subclasses, but the constructor of the 
//superclass can be invoked from the subclass.

class OutputWindow implements ActionListener {

    //creating top-level container JFrame
    JFrame window;

    //add 3 labels and make them centered inside your window
    JLabel labelTop;

    JLabel labelBottom;

    //You need to create new GridBagLayout within JPanel (Container) so you can create your new Grid(Display area).
    JPanel mainPanel;
    JPanel buttonPanel;

    //The GridBagConstraints specifies where a component's display area should be located 
    GridBagConstraints c;

    //Creates new Font object with default settings
    Font newFont;

    //create new buttons
    JButton buttonYes;
    JButton buttonNo;

    //Creating scrollbar
    JScrollPane scrollbar;

    //create textarea
    JTextArea textArea;

    //Borders
    Border myBorder;
    Border myBorder2;

    //Change colors here to apply them to the window
    final Color BACKGROUND_COLOR = new Color(51,153,255);
    final Color TEXTAREA_COLOR = new Color(242, 242, 242);
    final Color BUTTON_COLOR = new Color(51, 102, 255);
    final Color TEXT_COLOR = new Color(255, 255, 255);

    //Here we instantiate our boolean for pause
    private boolean pause = true;
    boolean waitingForDecision = true;

    //The only one Constructor:
    OutputWindow(String title) {

        //Instantiate new objects
        window = new JFrame(title);
        ImageIcon icon = createImageIcon("codebreaker.png", "logo");
        labelTop = new JLabel(icon);

        labelBottom = new JLabel(" ", JLabel.CENTER);
        mainPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new BorderLayout());
        c = new GridBagConstraints();
        newFont = new Font("Century Gothic", Font.BOLD, 29);
        buttonYes = new JButton("Yes");
        buttonNo = new JButton("No");
        myBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        myBorder2 = BorderFactory.createEmptyBorder(50, 50, 50, 50);
        textArea = new JTextArea();
        scrollbar = new JScrollPane(textArea);

            //Here we instantiate new actions used when we press ENTER or ESCAPE on keyboard
        Action enterPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // method "doClick"  does work as if you have just clicked the button
               buttonYes.doClick(20);

            }
        };

        Action escapePressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buttonNo.doClick(20);

            }
        };
        
        // "enterPressed" are javax.swing.Action objects
        // This code adds key "ENTER"  and name of action "enterPressed" to the inputMap
        // "WHEN_IN_FOCUSED_WINDOW" is used to react to pressed key no matter where the focus is
        buttonYes.getInputMap(buttonYes.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
        // This code adds key "ESCAPE"  and name of action "enterPressed" to the inputMap
        buttonNo.getInputMap(buttonNo.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escapePressed");

        
        //Here we add our action to action map
        buttonYes.getActionMap().put("enterPressed", enterPressed);
        buttonNo.getActionMap().put("escapePressed", escapePressed);
        
        //This code prevents key "SPACE" to react on Enter button and Exit button
        buttonYes.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        buttonNo.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

        //Here we set vertical bar to be displayed always and horizontal scrollbar never
        scrollbar.setHorizontalScrollBarPolicy(scrollbar.HORIZONTAL_SCROLLBAR_NEVER);
        scrollbar.setVerticalScrollBarPolicy(scrollbar.VERTICAL_SCROLLBAR_ALWAYS);

        //adding action listeners and setting up names of action commands
        //so we will identify which button has been clicked and add proper code to it
        buttonNo.addActionListener(this);
        buttonNo.setActionCommand("No");
        buttonYes.addActionListener(this);
        buttonYes.setActionCommand("Yes");

        //Change settings of Components here
        textArea.setBorder(myBorder);
        buttonPanel.setPreferredSize(new Dimension(170, 26));

        //Change settings of window here (JFrame)
          window.setSize(490, 600); // (width, height)
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

        //changed background color of the panel
        mainPanel.setBackground(this.BACKGROUND_COLOR);
        buttonPanel.setBackground(this.BACKGROUND_COLOR);

        //Here change settings of textField
        textArea.setBackground(this.TEXTAREA_COLOR);
        //If we add a scrollbar to panel we then set size of our scrollbar instead of panel
     scrollbar.setPreferredSize(new Dimension(400, 260)); // (width, height) 
        //Makes textArea unable to edit 
        textArea.setEditable(false);
        //Makes textArea content will always fit inside textArea width
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Century Gothic", Font.BOLD, 15));
        //Display empty text inside as default
        textArea.append(" ");

        //Change size of Buttons
        buttonYes.setPreferredSize(new Dimension(75, 26));
        buttonYes.setBackground(this.BUTTON_COLOR);
        buttonYes.setForeground(this.TEXT_COLOR);

        // turn off focus  and borders of the buttons
        buttonNo.setBorderPainted(false);
        buttonYes.setBorderPainted(false);
        buttonYes.setFocusPainted(false);
        buttonNo.setFocusPainted(false);

        buttonNo.setPreferredSize(new Dimension(75, 26));
        buttonNo.setBackground(this.BUTTON_COLOR);
        buttonNo.setForeground(this.TEXT_COLOR);

        //Set font of Labels
        labelTop.setPreferredSize(new Dimension(160, 90));
        labelTop.setFont(new Font("Century Gothic", Font.BOLD, 20));
        labelBottom.setFont(new Font("Century Gothic", Font.BOLD, 17));
        labelBottom.setForeground(this.TEXT_COLOR);

        c.weighty = 0.2;
        c.ipady = 9;
        // Make the component fill its display area entirely 
        //in this case labelTop, scrollbar and labelBottom
        c.fill = c.BOTH;

        //Change grids before adding labels
        c.gridy = 0;
        mainPanel.add(labelTop, c);
        c.gridy = 1;
        mainPanel.add(scrollbar, c);
        c.gridy = 2;
        mainPanel.add(labelBottom, c);

        // create new panel and add buttons to it, then add this new panel2 to panel1
        // then add panel2 to panel with a Grid so we can center it all vertically
        buttonPanel.add(buttonYes, BorderLayout.WEST);
        buttonPanel.add(buttonNo, BorderLayout.EAST);       // <-- adding buttons like this, will make them position from left to right by default

        //if you want to arrange positions of components  in JPanel
        //You have to add(new BorderLayout to the JPanel)
        c.gridy = 3;
        //Do not resize the component, in this case buttonPanel. 
        c.fill = c.NONE;
        mainPanel.add(buttonPanel, c);

        //Add panel to the frame
        window.getContentPane().add(mainPanel);
        //Displays our window (JFrame) in the center of display screen
        window.setLocationRelativeTo(null);
        window.setResizable(false);

    }

    /**
     * *******************************************************************
     *
     * METHODS:
     * _________________________________________________________________
     *
     * public void actionPerformed(ActionEvent e);
     *
     * - it overrides actionPerformed method
     * -------------------------------------------------------------------------------------------------------------
     * public void waitLooop();
     *
     * - this method will make program pause until boolean pause will be set to
     * false
     * -------------------------------------------------------------------------------------------------------------
     * public void pause(int time);
     *
     * -this method will pause for int time and then executes whats after it
     * -------------------------------------------------------------------------------------------------------------
     * public void setWindowTitle(String title);
     *
     * - sets title of the window
     * -------------------------------------------------------------------------------------------------------------
     * public void showWindow();
     *
     * - to set window visible you have to use this method
     * -------------------------------------------------------------------------------------------------------------
     * public void hideWindow();
     *
     * - to hide window and set visible to false use this method
     * -------------------------------------------------------------------------------------------------------------
     * public void setFont(String fontName, int fontSize);
     *
     * - to change font and size pass fontName and fontSize parameters
     * -------------------------------------------------------------------------------------------------------------
     * public void changeFont(String fontName, int fontSize);
     *
     * - changes font and font size
     * -------------------------------------------------------------------------------------------------------------
     * public void clearWindow();
     *
     * - clears the textArea
     * -------------------------------------------------------------------------------------------------------------
     * public void addText(String text);
     *
     * - adds text you want to the textArea
     * -------------------------------------------------------------------------------------------------------------
     * public void nextLine();
     *
     * - skips the line
     * -------------------------------------------------------------------------------------------------------------
     * public void setFontRed(); public void setFontOrange(); public void
     * setFontYellow(); public void setFontBrown(); public void setFontGreen();
     * public void setFontIndigo(); public void setFontViolet();
     *
     * - sets font to different color
     * -------------------------------------------------------------------------------------------------------------
     * public void setWindowColorRed(); public void setWindowColorOrange();
     * public void setWindowColorYellow(); public void setWindowColorGreen();
     * public void setWindowColorBrown(); public void setWindowColorIndigo();
     * public void setWindowColorViolet();
     *
     * - sets window color to different color
     * -------------------------------------------------------------------------------------------------------------
     * protected ImageIcon createImageIcon(String path,String description) ;
     *
     * - this method finds the specified file and returns an ImageIcon for that
     * file, or null if that file couldn't be found
     * -------------------------------------------------------------------------------------------------------------
     *
     *
     *
     ********************************************************************
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //if you click button "No", then there will be set Action "No" so it will execute System.exit(0), but if you click "Yes" it will make window dissapear.
        if ("No".equals(e.getActionCommand())) {

            System.exit(0);

            ///If "Yes" then the window will be invisible and you have to use class InputWindow.showWindow(); method 
            // to take input from the user
        } else if ("Yes".equals(e.getActionCommand())) {
            realeseTheLock();
        }

    }

    public synchronized void waitForDecision() {

        while (this.waitingForDecision) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

    }

    public synchronized void realeseTheLock() {
        // notify all threads that are waiting that there's been a change
        notifyAll();

        //Set to false to get out of the loop and do something after that...
        this.waitingForDecision = false;
        this.window.setVisible(false);

        //Debug code
        System.out.println(" i just realsed lock..");

    }

    public void separateLines() {
        this.textArea.append("\n");
        this.textArea.append("*******************************************************");
        this.textArea.append("\n");
    }

    public void setMessageLabel(String message) {

        this.labelBottom.setText(message);

    }

    public void ubuntuStyledText(String text) {
        String mytext = text;

        for (int l = 0; l < mytext.length(); l++) {

            this.textArea.append(mytext.substring(l, l + 1));

            pause(6);

        }

    }

    public void setVisibleButtons(boolean show) {
        this.buttonNo.setVisible(show);
        this.buttonYes.setVisible(show);

    }

    public void pause(int time) {
        //Pass int with value "20000000" to pause for around 2 hours and then executes whats after it
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    public void setWindowTitle(String title) {
        this.window.setTitle(title);
    }

    public void showWindow() {
        this.window.setVisible(true);
    }

    public void hideWindow() {
        this.window.setVisible(false);
    }

    public void setFont(String fontName, int fontSize) {

        this.textArea.setFont(new Font(fontName, Font.BOLD, fontSize));

    }

    public void clearWindow() {
        this.textArea.setText("");
    }

    public String addText(String text) {

        this.textArea.append(text);

        return text;

    }

    public void nextLine() {
        this.textArea.append("\n");
    }

    public void nextLineWithSeparators() {
        this.textArea.append("\n");
        this.textArea.setTabSize(11);
        this.textArea.append("\t*  *  *");
        this.textArea.append("\n");
    }

    public void appendTabSizeAt(int tabsize) {
        this.textArea.setTabSize(tabsize);
        this.textArea.append("\t");
    }

    public void setFontRed() {
        this.textArea.setForeground(new Color(255, 0, 0));
    }

    public void setFontOrange() {
        this.textArea.setForeground(new Color(255, 165, 0));
    }

    public void setFontYellow() {
        this.textArea.setForeground(new Color(255, 255, 0));
    }

    public void setFontGreen() {
        this.textArea.setForeground(new Color(0, 128, 0));
    }

    public void setFontBrown() {
        this.textArea.setForeground(new Color(102, 51, 0));
    }

    public void setFontIndigo() {
        this.textArea.setForeground(new Color(75, 0, 130));
    }

    public void setFontViolet() {
        this.textArea.setForeground(new Color(102, 0, 102));
    }

    public void setWindowColorRed() {
        this.mainPanel.setBackground(new Color(255, 0, 0));
        this.buttonPanel.setBackground(new Color(255, 0, 0));
    }

    public void setWindowColorOrange() {
        this.mainPanel.setBackground(new Color(255, 165, 0));
        this.buttonPanel.setBackground(new Color(255, 165, 0));
    }

    public void setWindowColorYellow() {
        this.mainPanel.setBackground(new Color(255, 255, 0));
        this.buttonPanel.setBackground(new Color(255, 255, 0));
    }

    public void setWindowColorGreen() {
        this.mainPanel.setBackground(new Color(0, 128, 0));
        this.buttonPanel.setBackground(new Color(0, 128, 0));
    }

    public void setWindowColorBrown() {
        this.mainPanel.setBackground(new Color(102, 51, 0));
        this.buttonPanel.setBackground(new Color(102, 51, 0));
    }

    public void setWindowColorIndigo() {
        this.mainPanel.setBackground(new Color(75, 0, 130));
        this.buttonPanel.setBackground(new Color(75, 0, 130));
    }

    public void setWindowColorViolet() {
        this.mainPanel.setBackground(new Color(102, 0, 102));
        this.buttonPanel.setBackground(new Color(102, 0, 102));
    }

    // The "createImageIcon" method finds the specified file and returns an ImageIcon for that file, or null if that file couldn't be found. Here is a typical implementation:
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }

    }
}
