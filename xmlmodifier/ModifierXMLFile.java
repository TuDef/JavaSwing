package xmlmodifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@SuppressWarnings("serial")
public class ModifierXMLFile extends JFrame 
{
	private String dir;
	private String[] id;
	private String[] name;
	private String[] salary;
	private String idText;
	private String nameText;
	private String salaryText;
	private int index;
	
	public static void main(String[] args) 
	{
		 new ModifierXMLFile().setVisible(true);
	}
	
	public ModifierXMLFile() 
	{
		setTitle("XML MODIFIER");
		setSize(800, 700);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Label
		JLabel label1 = new JLabel("EMPLOYEE");
		label1.setBounds(370, 20, 100, 30);
		add(label1);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(50, 400, 50, 30);
		add(idLabel);
		
		JLabel nameLabel = new JLabel("EMPLOYEE NAME");
		nameLabel.setBounds(50, 480, 120, 30);
		add(nameLabel);
		
		JLabel salaryLabel = new JLabel("SALARY");
		salaryLabel.setBounds(50, 560, 100, 30);
		add(salaryLabel);
		
		// Tabel
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 70, 500, 300);
		add(scrollPane);
		
		DefaultTableModel defaultTableModel = new DefaultTableModel()
		{
			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			};
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(defaultTableModel);
		
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Salary");
		
		//button 
		JButton addButton = new JButton("ADD NEW FILE");
		addButton.setBounds(590, 70, 130, 30);
		add(addButton);
		
		JButton editButton = new JButton("EDIT DATA");
		editButton.setBounds(590, 120, 130, 30);
		add(editButton);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.setBounds(590, 600, 130, 30);
		add(saveButton);
		
		// TextField
		JTextField idTextField = new JTextField();
		idTextField.setBounds(50, 440, 500, 30);
		add(idTextField);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(50, 520, 500, 30);
		add(nameTextField);
		
		JTextField salaryTextField = new JTextField();
		salaryTextField.setBounds(50, 600, 500, 30);
		add(salaryTextField);
		
		ActionListener actionListener = new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == addButton)
				{
					JFileChooser fileChooser = new JFileChooser();
					int result = fileChooser.showOpenDialog(null);
					if(result == JFileChooser.APPROVE_OPTION)
					{
						defaultTableModel.setRowCount(0);
						dir = fileChooser.getSelectedFile().getAbsolutePath();
						readXML(dir);
						
						for(int i = 0; i < id.length; i++)
						{
							defaultTableModel.addRow(new Object[] {id[i], name[i], salary[i]});
						}
					}
					
				}
				else if(e.getSource() == editButton) 
				{
					index = table.getSelectedRow();
					
					if(index < 0)
						JOptionPane.showMessageDialog(null, "Please choose a row in table to edit");
					else 
					{
						idTextField.setText((String) defaultTableModel.getValueAt(index, 0));
						nameTextField.setText((String) defaultTableModel.getValueAt(index, 1));
						salaryTextField.setText((String) defaultTableModel.getValueAt(index, 2));
					}
				}
				else if(e.getSource() == saveButton)
				{
					idText = idTextField.getText();
					nameText = nameTextField.getText();
					salaryText = salaryTextField.getText();
					
					if(idText.isEmpty() == false && nameText.isEmpty() == false && salaryText .isEmpty() == false)
					{
						defaultTableModel.setValueAt(idText, index, 0);
						defaultTableModel.setValueAt(nameText, index, 1);
						defaultTableModel.setValueAt(salaryText, index, 2);
					}
					else
						JOptionPane.showMessageDialog(null, "Something is empty");
					
					writeXML(dir);
				}
			}
		};
		
		addButton.addActionListener(actionListener);
		editButton.addActionListener(actionListener);
		saveButton.addActionListener(actionListener);
	}
	
	private void readXML(String dir)
	{
		try
		{
			File file = new File(dir);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(file);
			
			NodeList nodeList = document.getElementsByTagName("employee");
			
			id = new String[nodeList.getLength()];
			name = new String[nodeList.getLength()];
			salary = new String[nodeList.getLength()];
			
			for(int i = 0; i < nodeList.getLength(); i++)
			{
				Node currentNode = nodeList.item(i);
				
				Element element = (Element) currentNode;
				 
				id[i] = element.getElementsByTagName("id").item(0).getTextContent();
				name[i] = element.getElementsByTagName("name").item(0).getTextContent();
				salary[i] = element.getElementsByTagName("salary").item(0).getTextContent();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void writeXML(String dir) 
	{
		File file = new File(dir);
		
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(file);
			
			Node employee = document.getElementsByTagName("employee").item(index);
			
			Element element = (Element) employee;
			
			element.getElementsByTagName("id").item(0).setTextContent(idText);
			element.getElementsByTagName("name").item(0).setTextContent(nameText);
			element.getElementsByTagName("salary").item(0).setTextContent(salaryText);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			 
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource domSource = new DOMSource(document);
	
	        StreamResult streamResult = new StreamResult(file);
	        transformer.transform(domSource, streamResult); 
		}
		catch(Exception e) 
		{
            e.printStackTrace();
        } 
	}
}
