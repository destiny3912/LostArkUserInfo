package ProgramPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.sql.*;

public class data {
	
	public String charaterName;
	public String basic, battle, engrave, tendency, cardTxt;
	public String itemLevel, battleLevel, expeditionLevel;
	
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://root@localhost/LArkInfo?&useSSL=false";
	
	private final String USER_NAME = "root";
	private final String PASSWORD = "Destiny3910!";
	
	
	public void setName(String name)
	{
		charaterName = name;
	}
	public void getData() throws Exception{
		
		String URL = "https://lostark.game.onstove.com/Profile/Character/" + charaterName;
		Document doc = Jsoup.connect(URL).get();
		Elements itemL = doc.select("div.level-info2__expedition").select("span");
		Elements battleL = doc.select("div.level-info__item").select("span");
		Elements expeditionL = doc.select("div.level-info__expedition").select("span");
		Elements baiscAbility = doc.select("div.profile-ability-basic").select("span");
		Elements battleAblity = doc.select("div.profile-ability-battle").select("span");
		Elements engraveAblity = doc.select("div.profile-ability-engrave").select("span");
		Elements tendencyAblity = doc.select("div.profile-ability-tendency.chart-states-wrap.states_box");
		Elements cardText = doc.select("div.profile-card__text");
		
		System.out.println(tendencyAblity);
		itemLevel = itemL.text();
		battleLevel = battleL.text();
		expeditionLevel = expeditionL.text();
		basic = baiscAbility.text();
		battle = battleAblity.text();
		engrave = engraveAblity.text();
		tendency = tendencyAblity.text();
		cardTxt = cardText.text();
		
		storeInDB();
	}
	
	public void storeInDB()
	{
		Connection connectionCheck = null;
		Statement stateCheck = null;
		
		try {
			
			Class.forName(JDBC_DRIVER);
			connectionCheck = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			
			String sqlStatement;
			sqlStatement = "INSERT INTO userData (user_name, item_level, battle_level, expedition_level, basic_ablity, battle_ablity, engrave_ablity, tendency_ablity, card_text)"
					+ " VALUE ('" + charaterName +"', '" + itemLevel +"', '" + battleLevel + "', '" + expeditionLevel + "', '" + basic + "', '" + battle + "', '" + engrave + "', '" + tendency
					+ "N/A', '" + cardTxt + "')";
			System.out.println(sqlStatement);
			
			stateCheck = connectionCheck.createStatement();
			
			stateCheck.execute(sqlStatement);
			
			
		}catch(ClassNotFoundException e) {
			System.out.println("class ERROR");
		}catch(SQLException e){
			System.out.println("connection ERROR");
		}
		finally {
			try {
				if(stateCheck != null)
					stateCheck.close();
			}catch(SQLException ex1) {
				System.out.println("statement ERROR");
			}
			
			try {
				if(connectionCheck != null)
					connectionCheck.close();
			}catch(SQLException ex1) {
				System.out.println("connection ERROR");
			}
		}
	}
	
	public void setGUI()
	{
		JDialog infoPage = new JDialog();
		
		infoPage.setVisible(true);
		infoPage.setSize(900, 600);
		infoPage.setLayout(new GridLayout(2,1));
		infoPage.setTitle("캐릭터 정보 : " + charaterName);
		
		JPanel NamePanel = new JPanel();
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(5,1));
		
		infoPage.add(NamePanel);
		infoPage.add(dataPanel);
		
		JLabel charaName = new JLabel(charaterName);
		charaName.setHorizontalAlignment(JLabel.LEFT);
		NamePanel.add(charaName);
		
		JLabel iLevel = new JLabel(itemLevel);
		JLabel bLevel = new JLabel(battleLevel);
		JLabel eLevel = new JLabel(expeditionLevel);
		JLabel basicAbility = new JLabel(basic);
		JLabel battleAblity = new JLabel(battle);
		JLabel engraveAblity = new JLabel(engrave);
		JLabel tendencyAblity = new JLabel(tendency);
		JLabel cardText = new JLabel(cardTxt);
		
		iLevel.setHorizontalAlignment(JLabel.LEFT);
		basicAbility.setHorizontalAlignment(JLabel.LEFT);
		dataPanel.add(iLevel);
		dataPanel.add(bLevel);
		dataPanel.add(eLevel);
		dataPanel.add(basicAbility);
		dataPanel.add(battleAblity);
		dataPanel.add(engraveAblity);
		dataPanel.add(tendencyAblity);
		dataPanel.add(cardText);
	}
	
	public static void main(String[] args) {
		
       
    }
}
