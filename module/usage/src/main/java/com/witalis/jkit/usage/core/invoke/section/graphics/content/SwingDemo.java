package com.witalis.jkit.usage.core.invoke.section.graphics.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Desc: Swing Demo
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class SwingDemo extends JFrame {
    JLabel buttonLabel = new JLabel();
    JLabel statusLabel = new JLabel();

    public SwingDemo() {
        JFrame jFrame = new JFrame("Swing - a simple swing application.");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // scroll
        {
            JScrollPane jScroll = new JScrollPane();
            jFrame.add(jScroll);
        }
        // label
        {
            JLabel jLabel = new JLabel("Swing means powerful GUIs. Try it ...");
            jFrame.add(jLabel);
        }
        // button
        {
            buttonLabel = new JLabel("Press a button...");
            JButton jAlpha = new JButton("Alpha");
            jAlpha.addActionListener(
                (e) -> buttonLabel.setText("Alpha was pressed.")
            );
            JButton jBeta = new JButton("Beta");
            jBeta.addActionListener(
                (e) -> buttonLabel.setText("Beta was pressed.")
            );
            jFrame.add(jAlpha);
            jFrame.add(jBeta);
            jFrame.add(buttonLabel);
        }
        // text
        {
            JTextField jTextField = new JTextField(16);
            jTextField.addActionListener(
                (e) -> statusLabel.setText(jTextField.getText())
            );
            jFrame.add(jTextField);
        }
        // toggle
        {
            JToggleButton jToggle = new JToggleButton("On/Off");
            jToggle.addActionListener(
                (e) -> {
                    if (jToggle.isSelected())
                        statusLabel.setText("Toggle is on.");
                    else
                        statusLabel.setText("Toggle if off.");
                }
            );
            jFrame.add(jToggle);
        }
        // check box
        {
            JCheckBox jCheckBox = new JCheckBox("Active");
            jCheckBox.setSelected(true);
            jCheckBox.addActionListener(
                (e) -> {
                    if (jCheckBox.isSelected())
                        statusLabel.setText("Box is enabled.");
                    else
                        statusLabel.setText("Box is disabled.");
                }
            );
            jFrame.add(jCheckBox);
        }
        // radio
        {
            JRadioButton jHome = new JRadioButton("Home");
            jHome.addActionListener(
                (e) -> statusLabel.setText("Work at home.")
            );
            JRadioButton jOffice = new JRadioButton("Office");
            jOffice.addActionListener(
                (e) -> statusLabel.setText("Work at office.")
            );
            ButtonGroup workPlace = new ButtonGroup();
            workPlace.add(jHome);
            workPlace.add(jOffice);
            jFrame.add(jHome);
            jFrame.add(jOffice);
        }
        // list
        {
            JList<String> jList = new JList<>(
                new String[]{"I", "you", "we", "he", "she", "it", "they"}
            );
            jList.addListSelectionListener(
                (e) -> statusLabel.setText("Selected list: " + jList.getSelectedValue())
            );
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane jListScroll = new JScrollPane(jList);
            jListScroll.setPreferredSize(new Dimension(150, 50));
            jFrame.add(jListScroll);
        }
        // combo
        {
            JComboBox<String> jCombo = new JComboBox<String>(
                new String[]{"England", "France", "Ukraine", "New Zeeland", "Germany", "the USA"}
            );
            jCombo.addActionListener(
                (e) -> statusLabel.setText("Selected combo: " + jCombo.getSelectedItem())
            );
            jFrame.add(jCombo);
            // tab
            JTabbedPane jTab = new JTabbedPane();
            JPanel jJ = new JPanel();
            jTab.addTab("Java", jJ);
            jJ.add(new JLabel("Tab for Java programming"));
            JPanel jC = new JPanel();
            jC.add(new JLabel("Tab for C++ programming"));
            jTab.addTab("C++", jC);
            JPanel jP = new JPanel();
            jTab.addTab("Python", jP);
            jP.add(new JLabel("Tab for Python programming"));
            jFrame.add(jTab);
        }
        // tree
        {
            DefaultMutableTreeNode top = new DefaultMutableTreeNode("Options");
            var a = new DefaultMutableTreeNode("A");
            top.add(a);
            var a1 = new DefaultMutableTreeNode("A1");
            a.add(a1);
            var a2 = new DefaultMutableTreeNode("A2");
            a.add(a2);
            var a21 = new DefaultMutableTreeNode("A21");
            a2.add(a21);
            var b = new DefaultMutableTreeNode("B");
            top.add(b);
            var b1 = new DefaultMutableTreeNode("B1");
            b.add(b1);
            var b2 = new DefaultMutableTreeNode("B2");
            b.add(b2);
            var b3 = new DefaultMutableTreeNode("B3");
            b.add(b3);
            JTree jTree = new JTree(top);
            jTree.addTreeSelectionListener(
                (e) -> statusLabel.setText("Selected tree: " + e.getPath())
            );
            JScrollPane jTreeScroll = new JScrollPane(jTree);
            jTreeScroll.setPreferredSize(new Dimension(150, 80));
            jFrame.add(jTreeScroll);
        }
        // table
        {
            var headers = new String[]{"Name", "Phone", "Salary"};
            var content = new Object[][]{
                {"Bob", "+38(099)275-54-24", 23400},
                {"Ted", "+38(084)184-27-91", 28900},
                {"Jay", "+38(042)376-52-54", null},
                {"Don", null, 88800}
            };
            JTable jTable = new JTable(content, headers);
            jTable.addPropertyChangeListener(
                (e) -> {
                    if (false) statusLabel.setText("Table property " + e.getPropertyName());
                }
            );
            JScrollPane jTableScroll = new JScrollPane(jTable);
            jTableScroll.setPreferredSize(new Dimension(300, 80));
            jFrame.add(jTableScroll);
        }
        // menu
        {
            JMenuBar jBar = new JMenuBar();
            // file
            JMenu jFile = new JMenu("File");
            jFile.setMnemonic(KeyEvent.VK_F);
            JMenuItem jOpen = new JMenuItem("Open", KeyEvent.VK_O);
            jOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
            JMenuItem jClose = new JMenuItem("Close", KeyEvent.VK_C);
            jClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
            JMenuItem jSave = new JMenuItem("Save", KeyEvent.VK_S);
            jSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
            JMenuItem jExit = new JMenuItem("Exit", KeyEvent.VK_E);
            jExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
            jOpen.addActionListener(new MenuActionListener());
            jClose.addActionListener(new MenuActionListener());
            jSave.addActionListener(new MenuActionListener());
            jExit.addActionListener(new MenuActionListener());
            jFile.add(jOpen);
            jFile.add(jClose);
            jFile.add(jSave);
            jFile.addSeparator();
            jFile.add(jExit);
            jBar.add(jFile);
            // options
            JMenu jOptions = new JMenu("Options");
            // color
            JMenu jColors = new JMenu("Colors");
            JCheckBoxMenuItem jRed = new JCheckBoxMenuItem("Red");
            JCheckBoxMenuItem jGreen = new JCheckBoxMenuItem("Green");
            JCheckBoxMenuItem jBlue = new JCheckBoxMenuItem("Blue");
            jRed.addActionListener(new MenuActionListener());
            jGreen.addActionListener(new MenuActionListener());
            jBlue.addActionListener(new MenuActionListener());
            jColors.add(jRed);
            jColors.add(jGreen);
            jColors.add(jBlue);
            // priority
            JMenu jPriority = new JMenu("Priority");
            JRadioButtonMenuItem jHigh = new JRadioButtonMenuItem("High");
            JRadioButtonMenuItem jLow = new JRadioButtonMenuItem("Low");
            jHigh.addActionListener(new MenuActionListener());
            jLow.addActionListener(new MenuActionListener());
            jPriority.add(jHigh);
            jPriority.add(jLow);
            jOptions.add(jColors);
            jOptions.addSeparator();
            jOptions.add(jPriority);
            ButtonGroup group = new ButtonGroup();
            group.add(jHigh);
            group.add(jLow);
            jBar.add(jOptions);
            // help
            JMenu jHelp = new JMenu("Help");
            JMenuItem jAbout = new JMenuItem("About");
            jAbout.addActionListener(new MenuActionListener());
            jHelp.add(jAbout);
            jBar.add(jHelp);
            // frame
            jFrame.setJMenuBar(jBar);
        }
        // status
        {
            JPanel statusPanel = new JPanel();
            statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            jFrame.add(statusPanel, BorderLayout.SOUTH);
            statusPanel.setPreferredSize(
                new Dimension(jFrame.getWidth() - 15, 16)
            );
            statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
            statusLabel = new JLabel("Status");
            statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
            statusPanel.add(statusLabel);
        }
        // done
        jFrame.setVisible(true);
    }

    class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var command = e.getActionCommand();
            if (command.equals("Exit"))
                System.exit(0);
            else
                statusLabel.setText("Menu: " + command);
        }
    }
}
