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
     * Executer une requete SQL (SELECT)
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
     * Executer une requete SQL (UPDATE, CREATE...)
     * @param sql
     * @return requete réussie
     */
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
     * @return Liste d'ids
     */
    public ArrayList<Integer> getAllIds(){
    	
    	ArrayList<Integer> result=new ArrayList<Integer>();
    	try {
        	
        	ResultSet rs = this.exec("SELECT idPuzzle FROM Picross.Jeu;");
            if (rs != null) {
                while (rs.next()) {
                	result.add(rs.getInt(1));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return result;
    	
    }
    
    /**
     * Récupérer le nom du puzzle à partir de l'id du puzzle
     * @param idPuzzle id du puzzle
     * @return nom du puzzle
     */
    public String getNomFromIdPuzzle(int idPuzzle){

    	String res="";
    	
    	try {
        	
    		String requete="SELECT nom FROM Picross.Jeu WHERE idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		
    		ResultSet rs= prepare.executeQuery();
    		
        	if (rs != null) {
        		while (rs.next()) {
                	res= rs.getString(1);
                }
            }
        	
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return res;
    }
    
    
    /**
     * Récupérer le nombre de colonne pour un puzzle
     * @param idPuzzle id du puzzle
     * @return nombre de colonne
     */
    
    public int getNbColonne(int idPuzzle){

    	int res=-1;
    	
    	try {
        	
    		String requete="SELECT nbColonne FROM Picross.Jeu WHERE idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		
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
        	
    		String requete="SELECT nbLigne FROM Picross.Jeu WHERE idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		
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
    public boolean getReussite(int idPuzzle){

    	boolean res=false;
    	
    	ArrayList<Integer> result=new ArrayList<Integer>();
    	try {
        	
    		String requete="SELECT reussite FROM Picross.Jeu WHERE idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		
    		ResultSet rs= prepare.executeQuery();
    		
        	if (rs != null) {
        		while (rs.next()) {
                	res= rs.getBoolean(1);
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
    				"SELECT indices FROM Picross.Ligne "
    				+ "JOIN Picross.IndiceJeuLigne ON Picross.Ligne.idLigne=Picross.IndiceJeuLigne.idLigne "
    				+ "JOIN Picross.Jeu ON Picross.Jeu.idPuzzle=Picross.IndiceJeuLigne.idPuzzle "
    				+ "WHERE Picross.Jeu.idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		ResultSet rs= prepare.executeQuery();
    		
        	int cpt=0;
            if (rs != null) {
                while (rs.next()) {
                	result[cpt++]=rs.getString(1);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return result;
    	
    }
    
    public int setReussiteGrille(int idPuzzle, boolean reussite){
    	String requete=
    			"update Picross.Jeu SET reussite=? where idPuzzle=?;";
		PreparedStatement prepare;
		try {
			prepare = (PreparedStatement) this.dbConnect.prepareStatement(requete);
			prepare.setBoolean(1, reussite);
			prepare.setInt(2, idPuzzle);
			
			int rs= prepare.executeUpdate();
	    	return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
    }
    
    /**
     * Récupérer l'ensemble des indices de colonne pour un puzzle
     * @param idPuzzle id du puzzle
     * @param nbLigne nombre de ligne
     * @return liste des indices de colonne
     */
    public String[] getAllIndiceColonne(int idPuzzle, int nbLigne){
    	
    	String[] result=new String[nbLigne];
    	try {
        	
    		String requete=
    				"SELECT indices FROM Picross.Colonne "
    				+ "JOIN Picross.IndiceJeuColonne ON Picross.Colonne.idColonne=Picross.IndiceJeuColonne.idColonne "
    				+ "JOIN Picross.Jeu ON Picross.Jeu.idPuzzle=Picross.IndiceJeuColonne.idPuzzle "
    				+ "WHERE Picross.Jeu.idPuzzle=?";
    		
    		PreparedStatement prepare=(PreparedStatement) this.dbConnect.prepareStatement(requete);
    		prepare.setInt(1, idPuzzle);
    		ResultSet rs= prepare.executeQuery();
    		
        	int cpt=0;
            if (rs != null) {
                while (rs.next()) {
                	result[cpt++]=rs.getString(1);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return result;
    	
    }
    
    /**
     * Pour l'insertion de puzzle
     * @param idPuzzle id du puzzle
     * @param numLigne numéro de la ligne
     * @param indice indice de la ligne
     */
    public void ajoutIndiceLigne(int idPuzzle, int numLigne, String indice){
    	
    	
    	String insertLigne="insert into Picross.Ligne(indices) values(\""+indice+"\");";
    	this.execUpdate(insertLigne);
    	
    	String insertJeuLigne=
    	"insert into Picross.IndiceJeuLigne(idPuzzle,idLigne,numLigne) values"
    	+"("
    	+"(SELECT idPuzzle FROM Picross.Jeu WHERE idPuzzle=\""+idPuzzle+"\"),"
    	+"(SELECT MAX(idLigne) FROM Picross.Ligne),"+numLigne
    	+");";
    	this.execUpdate(insertJeuLigne);
    	
    }
    
    /**
     * Pour l'insertion de puzzle
     * @param idPuzzle id du puzzle
     * @param numColonne numéro de la colonne
     * @param indice indice de la ligne
     */
    public void ajoutIndiceColonne(int idPuzzle, int numColonne, String indice){
    	
    	String insertColonne="insert into Picross.Colonne(indices) values(\""+indice+"\");";
    	this.execUpdate(insertColonne);
    	
    	String insertJeuColonne=
    	"insert into Picross.IndiceJeuColonne(idPuzzle,idColonne,numColonne) values"
    	+"("
    	+"(SELECT idPuzzle FROM Picross.Jeu WHERE idPuzzle=\""+idPuzzle+"\"),"
    	+"(SELECT MAX(idColonne) FROM Picross.Colonne),"+numColonne
    	+");";
    	this.execUpdate(insertJeuColonne);
    	
    }
    
    /**
     * ajouter un puzzle dans la base de donnée
     * @param nomPuzzle nom du puzzle
     * @param nbLigne nombre de lignes
     * @param nbColonne nombre de colonnes
     * @param indicesLigne liste d'indices de ligne
     * @param indicesColonne liste d'indices de colonne
     * @throws SQLException 
     */
    public void ajoutPuzzleToDatabase(String nomPuzzle, int nbLigne, int nbColonne, 
    		String[] indicesLigne, String[] indicesColonne) throws SQLException{
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
        	if(rs.next())
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
    
    /**
     * Récuperer l'ensemble des ids des colonnes concernées par un puzzle (pour suppression)
     * @param idPuzzle
     * @return ensemble des ids
     * @throws SQLException
     */
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
    
    /**
     * Supprimer un puzzle à partir de son id
     * @param idPuzzle id du puzzle
     * @throws SQLException
     */
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
 
   
}