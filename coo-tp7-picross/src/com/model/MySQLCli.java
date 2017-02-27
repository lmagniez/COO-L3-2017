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
    
    
    public int execUpdate(String sql){
    	try{
    		int rs = this.dbStatement.executeUpdate(sql);
            return rs;
    	}
    	catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
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
     * Pour l'insertion de puzzle
     * @param nomPuzzle nom du puzzle
     * @param numLigne numéro de la ligne
     * @param indice indice de la ligne
     */
    public void ajoutIndiceLigne(int idPuzzle, int numLigne, int indice){
    	
    	
    	String insertLigne="insert into Picross.Ligne(indices) values("+indice+");";
    	this.execUpdate(insertLigne);
    	
    	String insertJeuLigne=
    	"insert into Picross.IndiceJeuLigne(idPuzzle,idLigne,numLigne) values"
    	+"("
    	+"(SELECT idPuzzle FROM Picross.Jeu WHERE nom=\""+idPuzzle+"\"),"
    	+"(SELECT MAX(idLigne) FROM Picross.Ligne),"+numLigne
    	+");";
    	this.execUpdate(insertJeuLigne);
    	
    }
    
    /**
     * Pour l'insertion de puzzle
     * @param nomPuzzle nom du puzzle
     * @param numLigne numéro de la ligne
     * @param indice indice de la ligne
     */
    public void ajoutIndiceColonne(int idPuzzle, int numColonne, int indice){
    	
    	System.out.println("AJOUT INDICE COLONNE "+numColonne + " "+indice);
    	
    	String insertColonne="insert into Picross.Colonne(indices) values("+indice+");";
    	this.execUpdate(insertColonne);
    	
    	String insertJeuColonne=
    	"insert into Picross.IndiceJeuColonne(idPuzzle,idColonne,numColonne) values"
    	+"("
    	+"(SELECT idPuzzle FROM Picross.Jeu WHERE idPuzzle=\""+idPuzzle+"\"),"
    	+"(SELECT MAX(idColonne) FROM Picross.Colonne),"+numColonne
    	+");";
    	this.execUpdate(insertJeuColonne);
    	
    }
    
    public void ajoutPuzzleToDatabase(String nomPuzzle, int nbLigne, int nbColonne, 
    		int[] indicesLigne, int[] indicesColonne) throws SQLException{
    	String insertJeu="insert into Picross.Jeu(nom,nbLigne,nbColonne,reussite) values(\""
    		+nomPuzzle+"\","+nbLigne+","+nbColonne+",false);";
    	this.execUpdate(insertJeu);
    	
    	String getIdPuzzle="(SELECT MAX(idPuzzle) FROM Picross.Jeu)"; 
    	ResultSet rs=this.exec(getIdPuzzle);
    	rs.next();
    	int idPuzzle=rs.getInt(1);
    	
    	
    	for(int i=0; i<nbLigne; i++)
    		ajoutIndiceLigne(idPuzzle, i, indicesLigne[i]);
    	for(int i=0; i<nbColonne; i++)
    		ajoutIndiceColonne(idPuzzle, i, indicesColonne[i]);
    	
    }
    
    public int getIdPuzzleFromName(String nomPuzzle) throws SQLException{
    	int id=-1;
    	String getId="Select idPuzzle from Picross.Jeu where nom=\""+nomPuzzle+"\"";
    	ResultSet rs=this.exec(getId);
    	if (rs != null) {
        	rs.next();
        	id=rs.getInt(1);
    		
        }
    	return id;
    }
    
    /**
     * Récuperer l'ensemble des ids des lignes concernées par un puzzle (pour suppression)
     * @param idPuzzle
     * @return ensemble des ids
     * @throws SQLException
     */
    public ArrayList<Integer> getIdLignesFromIdPuzzle(int idPuzzle) throws SQLException{
    	ArrayList<Integer> res=new ArrayList<Integer>();
    	String getId="Select idLigne from Picross.IndiceJeuLigne where idPuzzle=\""+idPuzzle+"\"";
    	ResultSet rs=this.exec(getId);
    	if (rs != null) {
        	while(rs.next())
        	{
        		int id=rs.getInt(1);
        		res.add(id);
        	}
    		
        }
    	return res;
    }
    
    public ArrayList<Integer> getIdColonnesFromIdPuzzle(int idPuzzle) throws SQLException{
    	ArrayList<Integer> res=new ArrayList<Integer>();
    	String getId="Select idColonne from Picross.IndiceJeuColonne where idPuzzle=\""+idPuzzle+"\"";
    	ResultSet rs=this.exec(getId);
    	if (rs != null) {
        	while(rs.next())
        	{
        		int id=rs.getInt(1);
        		res.add(id);
        	}
    		
        }
    	return res;
    }
    
    public void deletePuzzleFromDatabase(int idPuzzle) throws SQLException{
    	
    	
    	ArrayList<Integer> idsLigne= getIdLignesFromIdPuzzle(idPuzzle);
    	ArrayList<Integer> idsColonne= getIdColonnesFromIdPuzzle(idPuzzle);
    	
    	
    	String delJeu ="delete from Picross.IndiceJeuColonne where idPuzzle="+idPuzzle;
    	int id=this.execUpdate(delJeu);
    	delJeu ="delete from Picross.IndiceJeuLigne where idPuzzle="+idPuzzle;
    	id=this.execUpdate(delJeu);
    	
    	
    	for(int i=0; i<idsLigne.size(); i++){
    		String delLigne ="delete from Picross.Ligne where idLigne="+idsLigne.get(i);
    		id=this.execUpdate(delLigne);
    	}
    	
    	for(int i=0; i<idsColonne.size(); i++){
    		String delColonne ="delete from Picross.Colonne where idColonne="+idsColonne.get(i);
    		id=this.execUpdate(delColonne);
    	}
    	
    	delJeu ="delete from Picross.Jeu where idPuzzle="+idPuzzle;
    	id=this.execUpdate(delJeu);
    	
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
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        MySQLCli mysqlCli = new MySQLCli("//localhost:3306/db", "", "");
        if (mysqlCli.connect()) {
            
        	/*
        	try {
            	
        		
        		
            	/*
            	DatabaseMetaData md = (DatabaseMetaData) mysqlCli.dbConnect.getMetaData();
            	ResultSet rs = md.getTables(null, null, "%", null);
            	while (rs.next()) {
            	  System.out.println(rs.getString(3));
            	}
            	
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
        	*/
        	
        	 System.out.println(mysqlCli.execUpdate(ConstantesRequetes.createDataBase));
        	 System.out.println(mysqlCli.execUpdate(ConstantesRequetes.createJeu));
        	 System.out.println(mysqlCli.execUpdate(ConstantesRequetes.createLigne));
        	 System.out.println(mysqlCli.execUpdate(ConstantesRequetes.createColonne));
        	 System.out.println(mysqlCli.execUpdate(ConstantesRequetes.createIndiceJeuColonne));
        	 System.out.println(mysqlCli.execUpdate(ConstantesRequetes.createIndiceJeuLigne));
        	 
        	 
        	 mysqlCli.ajoutPuzzleToDatabase(ConstantesRequetes.nomStar, 
        			 ConstantesRequetes.nbLigneStar, ConstantesRequetes.nbColonneStar, 
        			 ConstantesRequetes.indicesLigneStar, ConstantesRequetes.indicesLigneStar);

         	int idPuzzle=mysqlCli.getIdPuzzleFromName("star");
        	mysqlCli.deletePuzzleFromDatabase(idPuzzle);
             
        	
        } else {
            System.out.println("Mysql connection failed !!!");
        }
        mysqlCli.close();
    }
}