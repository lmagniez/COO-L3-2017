package com.model;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
 
/**
 * MySQL client
 * @author Fobec 2010
 */
public class MySQLCli {
 
    private String dbURL = "";
    private String user = "";
    private String password = "";
    private java.sql.Connection dbConnect = null;
    private java.sql.Statement dbStatement = null;
 
    
    /**
     * Constructeur
     * @param url
     * @param user
     * @param password
     */
    public MySQLCli(String url, String user, String password) {
        this.dbURL = url;
        this.user = user;
        this.password = password;
    }
 
    /**
     * Connecter à la base de donnée
     * @return false en cas d'échec
     */
    public Boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.dbConnect = DriverManager.getConnection("jdbc:mysql:" + this.dbURL, this.user, this.password);
            this.dbStatement = this.dbConnect.createStatement();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
    /**
     * Executer une requete SQL
     * @param sql
     * @return resultat de la requete
     */
    public ResultSet exec(String sql) {
        try {
            ResultSet rs = this.dbStatement.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Recuper l'ensemble des ids des puzzles
     * @return
     */
    public ArrayList<Integer> getAllIds(){
    	
    	ArrayList<Integer> result=new ArrayList<Integer>();
    	try {
        	
        	ResultSet rs = this.exec("SELECT idPuzzle FROM db.Jeu;");
            if (rs != null) {
                while (rs.next()) {
                	result.add(rs.getInt(1));
                    System.out.println("Valeur: " + rs.getString(1));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return result;
    	
    }
    
    /**
     * Récupérer le nombre de colonne pour un puzzle
     * @param idPuzzle id du puzzle
     * @return nombre de colonne
     */
    
    public int getNbColonne(int idPuzzle){

    	int res=-1;
    	
    	ArrayList<Integer> result=new ArrayList<Integer>();
    	try {
        	
    		String requete="SELECT nbColonne FROM db.Jeu WHERE idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		
    		System.out.println(prepare.toString());
    		
    		ResultSet rs= prepare.executeQuery();
    		
        	if (rs != null) {
        		while (rs.next()) {
                	res= rs.getInt(1);
                }
            }
        	
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return res;
    }
    
    /**
     * Récupérer le nombre de ligne pour un puzzle
     * @param idPuzzle id du puzzle
     * @return nombre de ligne
     */
    public int getNbLigne(int idPuzzle){

    	int res=-1;
    	
    	ArrayList<Integer> result=new ArrayList<Integer>();
    	try {
        	
    		String requete="SELECT nbLigne FROM db.Jeu WHERE idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		
    		System.out.println(prepare.toString());
    		
    		ResultSet rs= prepare.executeQuery();
    		
        	if (rs != null) {
        		while (rs.next()) {
                	res= rs.getInt(1);
                }
            }
        	
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return res;
    }    
    
    /**
     * Récupérer l'ensemble des indices de ligne pour un puzzle
     * @param idPuzzle
     * @param nbLigne
     * @return liste des indices de ligne
     */
    public String[] getAllIndiceLigne(int idPuzzle, int nbLigne){
    	
    	String[] result=new String[nbLigne];
    	try {
        	
    		String requete=
    				"SELECT indices FROM Ligne "
    				+ "JOIN IndiceJeuLigne ON Ligne.idLigne=IndiceJeuLigne.idLigne "
    				+ "JOIN Jeu ON Jeu.idPuzzle=IndiceJeuLigne.idPuzzle "
    				+ "WHERE Jeu.idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		System.out.println(prepare.toString());
    		ResultSet rs= prepare.executeQuery();
    		
        	int cpt=0;
            if (rs != null) {
                while (rs.next()) {
                	result[cpt++]=rs.getString(1);
                    System.out.println("Valeur: " + rs.getString(1));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return result;
    	
    }
    
    /**
     * Récupérer l'ensemble des indices de colonne pour un puzzle
     * @param idPuzzle
     * @param nbColonne
     * @return liste des indices de colonne
     */
    public String[] getAllIndiceColonne(int idPuzzle, int nbLigne){
    	
    	String[] result=new String[nbLigne];
    	try {
        	
    		String requete=
    				"SELECT indices FROM Colonne "
    				+ "JOIN IndiceJeuColonne ON Colonne.idColonne=IndiceJeuColonne.idColonne "
    				+ "JOIN Jeu ON Jeu.idPuzzle=IndiceJeuColonne.idPuzzle "
    				+ "WHERE Jeu.idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		System.out.println(prepare.toString());
    		ResultSet rs= prepare.executeQuery();
    		
        	int cpt=0;
            if (rs != null) {
                while (rs.next()) {
                	result[cpt++]=rs.getString(1);
                    System.out.println("Valeur: " + rs.getString(1));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return result;
    	
    }
    
    
 
    /**
     * Fermer la connexion au serveur de DB
     */
    public void close() {
        try {
            this.dbStatement.close();
            this.dbConnect.close();
            this.dbConnect.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /**
     * Exemple d'utilisation de la class
     * @param args
     */
    public static void main(String[] args) {
        MySQLCli mysqlCli = new MySQLCli("//localhost:3306/db", "", "");
        if (mysqlCli.connect()) {
            
        	
        	try {
            	
            	/*
            	DatabaseMetaData md = (DatabaseMetaData) mysqlCli.dbConnect.getMetaData();
            	ResultSet rs = md.getTables(null, null, "%", null);
            	while (rs.next()) {
            	  System.out.println(rs.getString(3));
            	}*/
            	
            	//java.sql.PreparedStatement prep=mysqlCli.dbConnect.prepareStatement("SELECT * FROM Jeu");
            	//ResultSet rs = mysqlCli.exec("SELECT * FROM Jeu");
                
            	System.out.println(mysqlCli.getNbLigne(1));
            	
            	System.out.println("from here");
            	String[] res= mysqlCli.getAllIndiceLigne(1, 15);
            	for(int i=0; i<15; i++){
            		System.out.println(res[i]);
            	}
            	
            	
            	
            	ResultSet rs = mysqlCli.exec("SELECT * FROM db.Ligne;");
                if (rs != null) {
                	System.out.println("nb: "+rs.getFetchSize());
                    while (rs.next()) {
                        System.out.println("Valeur: " + rs.getString(2));
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Mysql connection failed !!!");
        }
        mysqlCli.close();
    }
}