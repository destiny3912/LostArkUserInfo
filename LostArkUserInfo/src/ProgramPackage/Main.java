package ProgramPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JFrame MainFrame= new JFrame();
	public String charaterName = " ";
	
	public void setGUI()
	{
		MainFrame.setVisible(true);
		MainFrame.setSize(900, 400);
		MainFrame.setTitle("LostArk Charater info.");
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.setLayout(new GridLayout(2,1));
		
		JPanel textPanel = new JPanel();//Program info text panel
		JPanel dataPanel = new JPanel();//Data get panel
		textPanel.setLayout(new FlowLayout());
		dataPanel.setLayout(new FlowLayout());
		
		JLabel programInfo = new JLabel("검색할 캐릭터 이름을 쓰고 버튼을 눌러주세요");
		programInfo.setHorizontalAlignment(JLabel.CENTER);
		textPanel.add(programInfo);
		MainFrame.add(textPanel);
		
		JTextField textfield = new JTextField();
		textfield.setText("캐릭터 이름을 입력해주세요");
		JButton getNameButton = new JButton("입력");
		
		dataPanel.add(textfield);
		dataPanel.add(getNameButton);
		MainFrame.add(dataPanel);
		
		getNameButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				charaterName = textfield.getText();
				
				data setData = new data();
				
				setData.setName(charaterName);
				
				try {
					setData.getData();
				}
				catch (Exception error)
				{
					
				}
				
				setData.setGUI();
			}
		});
	}
	
	public String getCharaterName()
	{
		return charaterName;
	}
	
	public static void main(String[] args)
	{
		Main runner = new Main();
		
		runner.setGUI();
	}
}
