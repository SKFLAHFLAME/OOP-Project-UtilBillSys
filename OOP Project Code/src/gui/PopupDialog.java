package gui;

import java.awt.CardLayout;

import javax.swing.JDialog;

import controller.MainFrame;

public class PopupDialog extends JDialog{
	MainFrame main;
	private CardLayout card;
	
	
	public PopupDialog(MainFrame m, String panel, CharSequence param) {
		main = m;
		this.setSize(500,400);
		this.setVisible(true);
		card = new CardLayout();
		this.setLayout(card);
		String[] items = param.toString().split(",");
		this.setLocationRelativeTo(main);
		this.setResizable(false);
		
		
		switch (panel) {
		case "CAccount":
			showCustAccount(items[0]);
			break;
			
		case "SAccount":
			showStaffAccount(items[0]);
			break;
			
		case "Date":
			showEditDate();
			break;
			
		case "EditMeterReading":
			showEditMeterReading(items[0], items[1], items[2]);
			break;
		
			
		case "SSignUp":
			showSSignUp();
			break;

		default:
			break;
		}
	}


	public void showStaffAccount(String id){
    	SAccountPage ap=new SAccountPage(this, id);
    	this.add(ap,"AP");
    	card.show(getContentPane(), "AP");
    }
	public void showCustAccount(String id){
    	CAccountPage ap=new CAccountPage(this, id);
    	this.add(ap,"AP");
    	card.show(getContentPane(), "AP");
    }
	
	public void showEditDate() {
    	EditSysDate vs = new EditSysDate(this);
    	add(vs,"vs");
    	card.show(getContentPane(), "vs");
    }
	
	public void showEditMeterReading(String user, String preName, String mr){
    	EditMeterReading emr=new EditMeterReading(this,preName,mr, user);
    	add(emr, "EditMeterReading");
    	card.show(this.getContentPane(), "EditMeterReading");
    }
	
	public void showSSignUp(){
        CreateStaff su = new CreateStaff(this);
        add(su, "SSignup");
        card.show(this.getContentPane(), "SSignup");
    }
	
	

}
