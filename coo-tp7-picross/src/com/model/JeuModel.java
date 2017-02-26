package com.model;

public class JeuModel extends AbstractModel{

	MySQLCli client;
	GrilleModel grilles[];
	
	public JeuModel(){
		this.client=new MySQLCli("//localhost:3306/db", "", "");
	    
	}

	
	
	
	
	@Override
	public boolean ajoutJeton(int x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean columnFull(int x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifWin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajoutJetonIA(int x, CaseValue v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retirerJetonIA(int x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
