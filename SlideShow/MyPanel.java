package SlideShow;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.net.URL;


public class MyPanel extends JPanel {
    private File[] selectedFiles;
    private int position;
    private boolean check(File a){
        String substring = String.valueOf(a).substring(String.valueOf(a).lastIndexOf("."));
        if(substring.equals(".png")||(substring.equals(".jpg"))||(substring.equals(".jpeg"))||(substring.equals(".gif"))){
            return true;
        }
        return false;
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        setLayout(null);

        JButton b1 = new JButton("<<");
        b1.setBounds(5, 400, 50, 100);

        JButton b2 = new JButton(">>");
        b2.setBounds(730, 400, 50, 100);

        JButton b3 = new JButton("Load");
        b3.setBounds(350, 20, 100, 40);

        add(b1);
        add(b2);
        add(b3);

        JLabel label = new JLabel();
        label.setBounds(65, 65, 650, 650);


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    position--;
                    try{
                    if(check(selectedFiles[position])){
                        ImageIcon imageIcon = new ImageIcon(String.valueOf(selectedFiles[position]));

                        Image imFit = imageIcon.getImage();

                        Image imgFit = imFit.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                        ImageIcon imageIcon1 = new ImageIcon(imgFit);
                        label.setIcon(imageIcon1);
                    }}catch (Exception e1){

                    }
                } else if (e.getSource() == b2) {
                    position++;
                    try {
                        if(check(selectedFiles[position])){
                            ImageIcon imageIcon = new ImageIcon(String.valueOf(selectedFiles[position]));

                            Image imFit = imageIcon.getImage();

                            Image imgFit = imFit.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                            ImageIcon imageIcon1 = new ImageIcon(imgFit);
                            label.setIcon(imageIcon1);
                        }
                    }catch (Exception e2){

                    }

                } else if (e.getSource() == b3) {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("4 loại hỗ trợ",
                            "jpg", "png", "jpeg", "gif");
                    fileChooser.setFileFilter(filter);
                    fileChooser.setCurrentDirectory(new File("D:/"));
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        selectedFiles = fileChooser.getSelectedFile().listFiles();
                        if (selectedFiles.length == 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Thư mục rỗng!", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else {

                            for (int i = 0; i < selectedFiles.length; i++) {
                                if (check(selectedFiles[i])) {
                                    position = i;
                                    ImageIcon imageIcon = new ImageIcon(String.valueOf(selectedFiles[i]));

                                    Image imFit = imageIcon.getImage();

                                    Image imgFit = imFit.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                                    ImageIcon imageIcon1 = new ImageIcon(imgFit);
                                    label.setIcon(imageIcon1);
                                }

                            }
                        }
                    }
                }
            }
        };
        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);


        add(label);
    }
}
