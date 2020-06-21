package xmlmodifier;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class ModifyXML extends JFrame {
	private int editIndex;
	private String fileName;
	private DefaultTableModel model = new DefaultTableModel();

	public static void main(String[] args) {
        ModifyXML xmlFile = new ModifyXML();
        xmlFile.setVisible(true);
    }

    private ModifyXML() {
        setTitle("XML");
        setSize(700, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        int width = 350;
        int height = 30;

        JLabel label = new JLabel();
        label.setText("XML MODIFIER");
        label.setBounds(275, 10, 300, 23);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        add(label);

        JLabel label1 = new JLabel();
        label1.setText("EDIT XML");
        label1.setForeground(Color.GREEN);
        label1.setBounds(200, 370, 300, 23);
        label1.setFont(new Font("Serif", Font.PLAIN, 24));
        add(label1);

        Object[][] data = {};
        String[] column = {"ID", "Name", "SALARY"};
        JTable table = new JTable(data, column);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(50, 50, 500, 300);
        add(jScrollPane);

        model.setDataVector(data, column);
        table.setModel(model);

        JTextField textField = new JTextField(25);
        textField.setBounds(200, 400, width, height);
        textField.setFont(new Font("Serif", Font.PLAIN, 20));

        JTextField textField1 = new JTextField(25);
        textField1.setBounds(200, 430, width, height);
        textField1.setFont(new Font("Serif", Font.PLAIN, 20));

        JTextField textField2 = new JTextField(25);
        textField2.setBounds(200, 460, width, height);
        textField2.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel jLabel = new JLabel("ID");
        jLabel.setBounds(80, 400, width, height);
        jLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel jLabel1 = new JLabel("Name");
        jLabel1.setBounds(80, 430, width, height);
        jLabel1.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel jLabel2 = new JLabel("SALARY");
        jLabel2.setBounds(80, 460, width, height);

        JButton b1 = new JButton("Save");
        b1.setBounds(200, 500, 100, 40);

        JButton b2 = new JButton("Add .xml");
        b2.setBounds(560, 50, 100, 40);

        JButton b3 = new JButton("Edit");
        b3.setBounds(560, 100, 100, 40);

        ActionListener actionListener = e -> {
            if (e.getSource() == b2) {
                JFileChooser file = new JFileChooser();
                int result = file.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    model.setRowCount(0);
                    fileName = file.getSelectedFile().getAbsolutePath();
					readXML(fileName);
                }
            }
            else if(e.getSource() == b3){
				editIndex = table.getSelectedRow();
				if (editIndex < 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để Edit",
							"Thông báo", JOptionPane.PLAIN_MESSAGE);
				} else {
					textField.setText((String) model.getValueAt(editIndex, 0));
					textField1.setText((String) model.getValueAt(editIndex, 1));
					textField2.setText((String) model.getValueAt(editIndex, 2));
				}
			}else if (e.getSource() == b1){
				String id = textField.getText();
				String name =textField1.getText();
				String salary = textField2.getText();
				if(checkEmpty(id,name,salary)){
					model.setValueAt(id,editIndex,0);
					model.setValueAt(name,editIndex,1);
					model.setValueAt(salary,editIndex,2);
					textField.setText("");
					textField1.setText("");
					textField2.setText("");
				}
				try {
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(fileName);

					Node nodeList = doc.getElementsByTagName("employee").item(editIndex);

					Element element = (Element) nodeList;

					element.getElementsByTagName("ID").item(0).setTextContent(id);
					element.getElementsByTagName("name").item(0).setTextContent(name);
					element.getElementsByTagName("salary").item(0).setTextContent(salary);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(fileName));
					transformer.transform(source, result);

				} catch (ParserConfigurationException | SAXException | IOException | TransformerException er) {
					er.printStackTrace();
				}
			}
        };
		b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
		b3.addActionListener(actionListener);

        add(jLabel);
        add(jLabel1);
        add(jLabel2);
        add(textField);
        add(textField1);
        add(textField2);
        add(b1);
        add(b2);
        add(b3);

    }
    private boolean checkEmpty(String id , String name , String salary){
    	if(id.isEmpty() | salary.isEmpty() | name.isEmpty()){
			JOptionPane.showMessageDialog(null, "Please Check Again ,Not Empty!");
    		return false;
		}
    	return true;
	}
    private void readXML(String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(file);

            NodeList nodeList = document.getElementsByTagName("employee");

            String[] id = new String[nodeList.getLength()];
            String[] name = new String[nodeList.getLength()];
            String[] salary = new String[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node currentNode = nodeList.item(i);

                Element element = (Element) currentNode;

                id[i] = element.getElementsByTagName("ID").item(0).getTextContent();
                name[i] = element.getElementsByTagName("name").item(0).getTextContent();
                salary[i] = element.getElementsByTagName("salary").item(0).getTextContent();
            }
            for (int i = 0; i < id.length; i ++) {
                model.addRow(new Object[]{id[i], name[i], salary[i]});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}