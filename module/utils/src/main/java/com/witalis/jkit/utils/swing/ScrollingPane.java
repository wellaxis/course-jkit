package com.witalis.jkit.utils.swing;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrollingPane {
  public static void main(String args[]) throws Exception {
    JFrame frame = new JFrame("Cornering Sample");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton button = new JButton("+");
    
    JLabel bigLabel = new JLabel("A");
    bigLabel.setPreferredSize(new Dimension(400,400));
    bigLabel.setBorder(new LineBorder(Color.red));
    
    JScrollPane scrollPane = new JScrollPane(bigLabel);
    scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, button);
    scrollPane.setColumnHeaderView(new JLabel("B"));
    scrollPane.setRowHeaderView(new JLabel("C"));

    ActionListener actionListener = new JScrollPaneToTopAction(scrollPane);
    button.addActionListener(actionListener);

    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(300, 200);
    frame.setVisible(true);
  }

}


class JScrollPaneToTopAction implements ActionListener {
  JScrollPane scrollPane;
  public JScrollPaneToTopAction(JScrollPane scrollPane) {
    if (scrollPane == null) {
      throw new IllegalArgumentException("JScrollPaneToTopAction: null JScrollPane");
    }
    this.scrollPane = scrollPane;
  }
  public void actionPerformed(ActionEvent actionEvent) {
    JScrollBar verticalScrollBar   = scrollPane.getVerticalScrollBar();
    JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
    verticalScrollBar.setValue(verticalScrollBar.getMinimum());
    horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
  }
}