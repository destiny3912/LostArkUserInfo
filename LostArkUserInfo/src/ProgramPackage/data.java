package ProgramPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class data {
	
	public String charaterName;
	public String basic, battle, engrave, tendency;
	public String itemLevel, battleLevel, expeditionLevel;
	
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
		Elements tendencyAblity = doc.select("div.states_box");
		
		System.out.println(tendencyAblity);
		itemLevel = itemL.text();
		battleLevel = battleL.text();
		expeditionLevel = expeditionL.text();
		basic = baiscAbility.text();
		battle = battleAblity.text();
		engrave = engraveAblity.text();
		tendency = tendencyAblity.text();
		
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
		
		iLevel.setHorizontalAlignment(JLabel.LEFT);
		basicAbility.setHorizontalAlignment(JLabel.LEFT);
		dataPanel.add(iLevel);
		dataPanel.add(bLevel);
		dataPanel.add(eLevel);
		dataPanel.add(basicAbility);
		dataPanel.add(battleAblity);
		dataPanel.add(engraveAblity);
		dataPanel.add(tendencyAblity);
	}
	
	public static void main(String[] args) {
		
       
    }
}
