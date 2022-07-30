package com.witalis.jkit.usage.core.invoke.section.graphics.content;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * Desc: Component Demo
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComponentDemo extends Frame implements ActionListener, ItemListener, AdjustmentListener {
    private String message;
    private Label btnStatus;
    private Label chkStatus;
    private Label choStatus;
    private Label lstStatus;
    private Label scrStatus;
    private CheckboxGroup group;
    private Choice os;
    private Choice browser;
    private List shapeL;
    private List colorL;
    private Scrollbar vertical;
    private Scrollbar horizontal;

    public ComponentDemo() {
        setBackground(Color.cyan);
        setForeground(Color.gray);
        // layout
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        setLayout(grid);
        // menu
        var menus = menus();
        setMenuBar(menus);
        // label
        var labels = labels();
        grid.setConstraints(labels, constraints);
        // button
        var buttons = buttons();
        grid.setConstraints(buttons, constraints);
        // checkbox
        var checkboxes = checkboxes();
        grid.setConstraints(checkboxes, constraints);
        // choice
        var choices = choices();
        grid.setConstraints(choices, constraints);
        // list
        var lists = lists();
        grid.setConstraints(lists, constraints);
        // scroll
        var scrolls = scrolls();
        grid.setConstraints(scrolls, constraints);
        // text
        var texts = texts();
        grid.setConstraints(labels, constraints);
        // add
        add(labels);
        add(buttons);
        add(checkboxes);
        add(choices);
        add(lists);
        add(scrolls);
        add(texts);
        // exit
        addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

    private MenuBar menus() {
        class FDialog extends Dialog implements ActionListener {

            public FDialog(Frame owner, String title) {
                super(owner, title, false);
                setLayout(new FlowLayout());
                setSize(new Dimension(200, 100));
                add(new Label("Press this button"));
                Button cancel = new Button("Cancel");
                add(cancel, BorderLayout.CENTER);
                cancel.addActionListener(this);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

            @Override
            public void paint(Graphics g) {
                g.drawString("Dialog window: not modal", 10, 80);
            }
        }

        // component
        MenuBar menuBar = new MenuBar();
        // file
        Menu file = new Menu("File");
        MenuItem fNew = new MenuItem("New");
        MenuItem fOpen = new MenuItem("Open");
        MenuItem fClose = new MenuItem("Close");
        file.add(fNew);
        file.add(fOpen);
        file.add(fClose);
        menuBar.add(file);
        // edit
        Menu edit = new Menu("Edit");
        MenuItem eCut = new MenuItem("Cut");
        MenuItem eCopy = new MenuItem("Copy");
        MenuItem ePaste = new MenuItem("Paste");
        CheckboxMenuItem eRun = new CheckboxMenuItem("Run");
        CheckboxMenuItem eDebug = new CheckboxMenuItem("Debug");
        edit.add(eCut);
        edit.add(eCopy);
        edit.add(ePaste);
        edit.addSeparator();
        edit.add(eRun);
        edit.add(eDebug);
        menuBar.add(edit);
        // listener
        fNew.addActionListener(
            e -> {
                Dialog dialog = new FDialog(this, "Dialog");
                dialog.setVisible(true);
            }
        );
        fOpen.addActionListener(
            e -> {
                FileDialog fileDialog = new FileDialog(this, "File Dialog");
                fileDialog.setVisible(true);
            }
        );
        return menuBar;
    }

    private Panel labels() {
        Panel labels = new Panel();
        // component
        Label one = new Label("One");
        Label two = new Label("Two");
        Label three = new Label("Three");
        Label four = new Label("Four");
        // add
        labels.add(one);
        labels.add(two);
        labels.add(three);
        labels.add(four);
        return labels;
    }

    private Panel buttons() {
        Panel buttons = new Panel();
        btnStatus = new Label("");
        // component
        Button yes = new Button("Yes");
        Button no = new Button("No");
        Button maybe = new Button("Maybe");
        // add
        buttons.add(yes);
        buttons.add(no);
        buttons.add(maybe);
        yes.addActionListener(this);
        no.addActionListener(this);
        maybe.addActionListener(this);
        // reset
        var reset = new Button("Clear");
        // listener
        reset.addActionListener(
            (ae) -> {
                message = "";
                repaint();
            }
        );
        buttons.add(reset);
        buttons.add(btnStatus);
        return buttons;
    }

    private Panel checkboxes() {
        Panel checkboxes = new Panel();
        chkStatus = new Label("");
        // component
        group = new CheckboxGroup();
        Checkbox windows = new Checkbox("Windows", group, true);
        Checkbox android = new Checkbox("Android", group, false);
        Checkbox solaris = new Checkbox("Solaris", group, false);
        Checkbox mac = new Checkbox("Mac OS", group, false);
        // add
        checkboxes.add(windows);
        checkboxes.add(android);
        checkboxes.add(solaris);
        checkboxes.add(mac);
        // listener
        windows.addItemListener(this);
        android.addItemListener(this);
        solaris.addItemListener(this);
        mac.addItemListener(this);
        checkboxes.add(chkStatus);
        return checkboxes;
    }

    private Panel choices() {
        Panel choices = new Panel();
        choStatus = new Label("");
        // component
        os = new Choice();
        os.add("Windows");
        os.add("Unix");
        os.add("Android");
        os.add("Solaris");
        os.add("Mac OS");
        browser = new Choice();
        browser.add("Explorer");
        browser.add("Chrome");
        browser.add("Firefox");
        browser.add("Opera");
        // add
        choices.add(os);
        choices.add(browser);
        // listener
        os.addItemListener(this);
        browser.addItemListener(this);
        choices.add(choStatus);
        return choices;
    }

    private Panel lists() {
        Panel lists = new Panel();
        lstStatus = new Label("");
        // component
        shapeL = new List(4, true);
        shapeL.add("Circle");
        shapeL.add("Square");
        shapeL.add("Triangle");
        shapeL.add("Ellipse");
        colorL = new List(4, false);
        colorL.add("Red");
        colorL.add("Blue");
        colorL.add("Green");
        colorL.add("Yellow");
        // add
        colorL.select(1);
        lists.add(shapeL);
        lists.add(colorL);
        // listener
        shapeL.addActionListener(this);
        colorL.addActionListener(this);
        lists.add(lstStatus);
        return lists;
    }

    private Panel scrolls() {
        Panel scrolls = new Panel();
        scrStatus = new Label("");
        int width = 100, height = 100;
        // component
        vertical = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, height);
        vertical.setPreferredSize(new Dimension(20, 100));
        horizontal = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, width);
        horizontal.setPreferredSize(new Dimension(100, 20));
        // add
        scrolls.add(vertical);
        scrolls.add(horizontal);
        // listener
        vertical.addAdjustmentListener(this);
        horizontal.addAdjustmentListener(this);
        scrolls.add(scrStatus);
        return scrolls;
    }

    private Panel texts() {
        Panel texts = new Panel();
        byte[] array = new byte[100];
        new Random().nextBytes(array);
        var text = new String(array, Charset.forName("UTF-8"));
        // component
        Label nameL = new Label("Name: ", Label.LEFT);
        Label passL = new Label("Pass: ", Label.RIGHT);
        TextField name = new TextField(10);
        TextField pass = new TextField(5);
        pass.setEchoChar('*');
        TextArea info = new TextArea(text, 3, 8);
        // add
        texts.add(nameL);
        texts.add(name);
        texts.add(passL);
        texts.add(pass);
        texts.add(info);
        // listener
        name.addActionListener(this);
        pass.addActionListener(this);
        return texts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        message = switch (action) {
            case "Yes" -> "Pressed 'yes'";
            case "No" -> "Pressed 'no'";
            case "Maybe" -> "Pressed 'maybe'";
            default -> "Unexpected value: " + action;
        };
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (false) {
            g.drawString(message, 20, 120);
        } else {
            btnStatus.setText(
                "Active button: " + message
            );
            chkStatus.setText(
                "Active checkbox: " + group.getSelectedCheckbox().getLabel()
            );
            choStatus.setText(
                "OS: " + os.getSelectedItem() + ", Browser: " + browser.getSelectedItem()
            );
            var shapes = new StringBuilder("");
            if (shapeL.getItemCount() > 0) {
                for (var i : shapeL.getSelectedIndexes()) {
                    shapes.append(shapeL.getItem(i)).append(" ");
                }
            } else {
                shapes.append("-");
            }
            var colors = new StringBuilder("");
            if (colorL.getItemCount() > 0) {
                for (var i : colorL.getSelectedIndexes()) {
                    colors.append(colorL.getItem(i)).append(" ");
                }
            } else {
                shapes.append("-");
            }
            lstStatus.setText(
                "Shapes: " + shapes.toString() + ", Colors: " + colors.toString()
            );
            scrStatus.setText(
                "Vertical: " + vertical.getValue() + ", Horizontal: " + horizontal.getValue()
            );
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        repaint();
    }
}
