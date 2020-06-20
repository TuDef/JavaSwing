package StudentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MyPanel extends JPanel implements WindowListener {
    private static String fileName;
    private static DefaultTableModel model = new DefaultTableModel();
    private int editIndex;
    private boolean isOK = true;
    private int check = 0;
    private boolean flag = true;

    private boolean isEmpty(String name) {
        return !name.isEmpty();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        setLayout(null);
        int width = 350;
        int height = 30;


        Object[][] data = {};
        String[] column = {"Student ID", "Student Name", "D.O.B", "Contact Info"};
        JTable table = new JTable(data, column);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(50, 50, 500, 300);
        add(jScrollPane);

        //Hiển thị các Label
        model.setDataVector(data, column);
        table.setModel(model);

        //Bảng nhập thông tin
        JTextField textField = new JTextField(25);
        textField.setBounds(200, 400, width, height);
        textField.setFont(new Font("Serif", Font.PLAIN, 20));

        JTextField textField1 = new JTextField(25);
        textField1.setBounds(200, 430, width, height);
        textField1.setFont(new Font("Serif", Font.PLAIN, 20));


        JFormattedDateTextField textField2 = new JFormattedDateTextField();
        JTextField normalTf = new JTextField(25);
        textField2.setBounds(200, 460, width, height);
        textField2.setFont(new Font("Serif", Font.PLAIN, 20));


        JTextField textField3 = new JTextField(25);
        textField3.setBounds(200, 490, width, height);
        textField3.setFont(new Font("Serif", Font.PLAIN, 20));


        // label
        JLabel jLabel = new JLabel("Student ID");
        jLabel.setBounds(80, 400, width, height);
        jLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel jLabel1 = new JLabel("Student Name");
        jLabel1.setBounds(80, 430, width, height);
        jLabel1.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel jLabel2 = new JLabel("Date Of birth");
        jLabel2.setBounds(80, 460, width, height);

        JLabel jLabel3 = new JLabel("Contact");
        jLabel3.setBounds(80, 490, width, height);
        jLabel3.setFont(new Font("Serif", Font.PLAIN, 16));


        //Các Button
        JButton b1 = new JButton("Save");
        b1.setBounds(230, 530, 100, 40);

        JButton b2 = new JButton("Cancel");
        b2.setBounds(420, 530, 100, 40);

        JButton b3 = new JButton("Load");
        b3.setBounds(560, 50, 100, 40);

        JButton b4 = new JButton("Add new");
        b4.setBounds(560, 100, 100, 40);

        JButton b5 = new JButton("Edit");
        b5.setBounds(560, 150, 100, 40);

        JButton b6 = new JButton("Delete");
        b6.setBounds(560, 200, 100, 40);

        JButton b7 = new JButton("Exit");
        b7.setBounds(560, 700, 100, 40);

        JButton b8 = new JButton("|<");
        b8.setBounds(440, 350, 50, 20);

        JButton b9 = new JButton(">|");
        b9.setBounds(500, 350, 50, 20);


        textField.setEditable(false);
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        ActionListener actionListener = e -> {
            ArrayList<String> array = new ArrayList<>();
            for (int i = 0; i <model.getRowCount() ; i++) {
                array.add((String) model.getValueAt(i, 0));
            }
            String id = "";
            String name = "";
            if (e.getSource() == b4) {//nut addNew
                textField.setEditable(true);
                textField1.setEditable(true);
                textField2.setEditable(true);
                textField3.setEditable(true);
                textField.requestFocus();
            }
            if (e.getSource() == b1) {//nut Save
                try {
                    id = textField.getText();
                    if (isEmpty(id)) {
                        isOK = true;
                    } else {
                        int zero = 0;
                        int average = 10 / zero;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "StudentID không được bỏ trống!");
                    isOK = false;
                }

                if (isOK == true) {
                    try {
                        for (int i = 0; i <model.getRowCount() ; i++) {
                            if(id.equals(array.get(i))){
                                isOK =false;
                                break;
                            }
                        }
                        if(isOK == true){

                        }else {
                            int zero = 0;
                            int average = 10 / zero;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "StudentID không được Trùng !");
                        isOK = false;
                    }
                }

                if (isOK == true) {
                        try {
                            name = textField1.getText();
                            if (isEmpty(name)) {
                                isOK = true;
                            } else {
                                int zero = 0;
                                int average = 10 / zero;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "StudentName không được bỏ trống!");
                            isOK = false;
                        }
                    }
                    if (isOK == true) {
                        try {
                            Long.parseLong(textField3.getText());
                            isOK = true;
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                    "ContactInfo phải là số và ko chứa kí tự khác!");
                            isOK = false;
                        }
                    }
                    if (isOK == true) {
                        try {
                            normalTf.setText((String) textField2.getValue());
                            if (normalTf.getText().isEmpty()) {
                                int zero = 0;
                                int average = 10 / zero;
                            } else {
                                isOK = true;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                    " Date Of Birth không được bỏ trống!");
                            isOK = false;
                        }


                    }
                    if (editIndex >= 0 && isOK == true && check == 1) {
                        model.setValueAt(textField.getText(), editIndex, 1);
                        model.setValueAt(textField1.getText(), editIndex, 1);
                        model.setValueAt(textField2.getText(), editIndex, 2);
                        model.setValueAt(textField3.getText(), editIndex, 3);
                        textField.setText("");
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        editIndex = -1;
                    } else if (isOK == true) {
                        model.addRow(new Object[]{textField.getText(),
                                textField1.getText(), textField2.getText(), textField3.getText()});
                        textField.setText("");
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                    }
                    textField.setEditable(true);
                } else if (e.getSource() == b2) { // nut Cancel
                    textField.setText("");
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField.requestFocus();

                } else if (e.getSource() == b6) {// Nut delete
                    int removeIndex = table.getSelectedRow();
                    if (removeIndex == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để xóa",
                                "Thông báo", JOptionPane.DEFAULT_OPTION);
                    } else {
                        int result = JOptionPane.showConfirmDialog(
                                null, "Bạn Có Muốn Xóa Không ?",
                                "Thong báo", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            model.removeRow(removeIndex);
                        }
                    }
                } else if (e.getSource() == b5) {// nut Edit
                    editIndex = table.getSelectedRow();
                    if (editIndex < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để Edit",
                                "Thông báo", JOptionPane.DEFAULT_OPTION);
                    } else {
                        textField.setEditable(false);
                        textField1.setEditable(true);
                        textField2.setEditable(true);
                        textField3.setEditable(true);
                        textField.setText((String) model.getValueAt(editIndex, 0));
                        textField1.setText((String) model.getValueAt(editIndex, 1));
                        textField2.setText((String) model.getValueAt(editIndex, 2));
                        textField3.setText((String) model.getValueAt(editIndex, 3));

                    }
                    check = 1;


                } else if (e.getSource() == b7) { //Nut Exit
                    ArrayList<String> arr = new ArrayList<>();
                    int result = JOptionPane.showConfirmDialog(
                            null, "Bạn Có Muốn Thoát Không",
                            "Thong báo", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        for (int i = 0; i < model.getRowCount(); i++) {
                            arr.add((String) model.getValueAt(i, 0));
                            arr.add((String) model.getValueAt(i, 1));
                            arr.add((String) model.getValueAt(i, 2));
                            arr.add((String) model.getValueAt(i, 3));
                        }
                        if (!flag) {
                            File f = new File(fileName);
                            f.delete();
                            try {
                                File myObj = new File(fileName);
                                myObj.createNewFile();
                                FileWriter myWriter = new FileWriter(myObj);
                                for (String s : arr) {
                                    myWriter.write(s + ",");
                                }
                                myWriter.close();
                            } catch (IOException ex) {
                                System.out.println("An error occurred.");
                                ex.printStackTrace();
                            }
                        }
                        System.exit(0);
                    }
                } else if (e.getSource() == b3) { // Nut Load
                    String content = "";
                    JFileChooser file = new JFileChooser();
                    var result = file.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        model.setRowCount(0);
                        fileName = file.getSelectedFile().getAbsolutePath();
                        try {
                            content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
                        } catch (IOException er) {
                            er.printStackTrace();
                        }
                    }
                    String[] split = content.split(",");
                    try {
                        for (int i = 0; i < split.length; i += 4) {
                            model.addRow(new Object[]{split[i], split[i + 1], split[i + 2], split[i + 3]});
                        }
                    } catch (Exception ex) {

                    }
                    flag = false;
                    editIndex = -1;
                } else if (e.getSource() == b8) {//nút backFirst
                    table.setRowSelectionInterval(0, 0);


                } else if (e.getSource() == b9) {//nút goEnd
                    int endRow = table.getRowCount() - 1;
                    table.setRowSelectionInterval(endRow, endRow);
                }
            };

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);
        b5.addActionListener(actionListener);
        b6.addActionListener(actionListener);
        b7.addActionListener(actionListener);
        b8.addActionListener(actionListener);
        b9.addActionListener(actionListener);


            add(b1);

            add(b2);

            add(b3);

            add(b4);

            add(b5);

            add(b6);

            add(b7);

            add(b8);

            add(b9);

            add(jLabel);

            add(jLabel1);

            add(jLabel2);

            add(jLabel3);

            add(textField);

            add(textField1);

            add(textField2);

            add(textField3);
        }


        @Override
        public void windowOpened (WindowEvent windowEvent){

        }

        @Override
        public void windowClosing (WindowEvent windowEvent){
            if (fileName != null) {
                try {
                    JOptionPane.showConfirmDialog(
                            null, "Đã Save File ",
                            "Thông báo", JOptionPane.DEFAULT_OPTION);
                    File file = new File(fileName);
                    FileWriter fileWriter = new FileWriter(file);
                    int rowNumber = model.getRowCount();

                    for (int i = 0; i < rowNumber; i++) {
                        fileWriter.write(model.getValueAt(i, 0) + ","
                                + model.getValueAt(i, 1) + ","
                                + model.getValueAt(i, 2) + ","
                                + model.getValueAt(i, 3) + "," + "\r\n");
                    }

                    fileWriter.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR TO SAVE");
                }
            }

        }


        @Override
        public void windowClosed (WindowEvent windowEvent){

        }

        @Override
        public void windowIconified (WindowEvent windowEvent){

        }

        @Override
        public void windowDeiconified (WindowEvent windowEvent){

        }

        @Override
        public void windowActivated (WindowEvent windowEvent){

        }

        @Override
        public void windowDeactivated (WindowEvent windowEvent){

        }
    }




