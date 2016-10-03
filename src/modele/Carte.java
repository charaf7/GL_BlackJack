/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author MANEL REKIK
 */
public class Carte {
	
	/**
	 * Chaque carte a une enseigne , on definira un enum representant cette derniere
	 * 
	 * @see Enseigne
	 */
	private Enseigne enseigneCarte;
	
	/**
	 * cet attribut represente la valeur d'une carte 
	 */
	private int valeur;
	
	/**
	 * cet attribut verifie si la carte est disponible ou pas
	 */
	private Boolean disponible;
	
	/**
	 * cet attribut represente l'url de l'image de la carte
	 */
	
	private Face face;	
	
	/**
	 * l'enum representant la face de la carte
	 */
	public enum Face {
		VISIBLE, CACHE;
	}
	
	/**
	 * la methode d'instance qui va se charger de creer l'objet Carte
	 * 
	 * @param enseigneCarte
	 * la nouvelle enseigne de la carte
	 * @param valeur
	 * la nouvelle valeur de la carte
	 */
		public Carte (Enseigne enseigneCarte,int valeur){
			this.enseigneCarte=enseigneCarte;
			this.valeur=valeur;
	
			/*
			 * Face Cache par defaut
			 */
			this.face=Face.CACHE;
		}
	
	/**
	 * l'enum representant l'enseigne de la carte
	 */
	public enum Enseigne {
		
		/**
		 * les enseignes des cartes pour le jeux Blackjack
		 */
		COEUR(0), CARREAU(1), PIQUE(2), TREFLE(3);
			
		/**
		 * cet attribut represente l'index  de l'enseigne , on peut aussi utilise la methode 
		 * ordinal qui renvoie l'index de l'element dans une liste
		 */
		private int valeur;
		
		/**
		 * un consctructeur privee pour eviter l'instanciation de l'objet a l'externe de la classe
		 * 
		 * @param valeur
		 * une nouvelle valeur d'index pour l'enseigne
		 */
		private  Enseigne(int valeur) {
		this.valeur=valeur;
		}
		
		/**
		 * 
		 * @return la valeur qui correspond a l'index de l'enseigne
		 */
		public int getValeur() {
			return valeur;
		}
		
		public static Enseigne getEnseigneParValeur(int valeur){
			switch(valeur) {
				case 0:	return COEUR;
				case 1: return CARREAU;
				case 2: return PIQUE;
				case 3: return TREFLE;
					
				default: return null;
				}
			}
		}
	
	/**
	 * 
	 * @return true si la carte est un Ace
	 */
	public Boolean isAce(){
		return valeur==1;
	}
	
	/**
	 * 
	 * @return true si la carte est une figure  
	 */ 
	public Boolean isFigure(){
		return valeur <= 13 && valeur >10; 
	}
	/**
	 * Met la face de la carte cache
	 * @see Face
	 */
	public void setFaceCache(){
		setFace(Face.CACHE);
	}
	/**
	 * Met la face de la carte visible
	 * @see Face
	 */
	public void setFaceVisible(){
		setFace(Face.VISIBLE);
	}

	/**
	 * 
	 * @return l'enseigne de la carte
	 */
	public Enseigne getEnseigneCarte() {
		return enseigneCarte;
	}
	
	/**
	 * Met a jour l'enseigne de la carte
	 * @param enseigneCarte
	 */
	public void setEnseigneCarte(Enseigne enseigneCarte) {
		this.enseigneCarte = enseigneCarte;
	}
	
	/**
	 * 
	 * @return la valeur exacte de la carte dependement du jeu de Blackjack
	 * par defaut 1 si c'est un As
	 */
	public int getValeur() {
		if(isFigure()){return valeur=10;}
		if(isAce()){return valeur=1;}
		return valeur;
	}

	/**
	 *  Met a jour la valeur de la carte
	 * @param valeur
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	/**
	 * 
	 * @return la valeur dans la carte pas celle par rapport au jeu
	 */
	public int lireValeur(){
		return valeur;
	}
	
	/**
	 * 
	 * @return disponibilite de la carte
	 */
	public Boolean getDisponible() {
		return disponible;
	}
	
	/**
	 *  Met a jour la disponibilite de la carte
	 * @param disponibilite
	 */
	public void setDisponible(Boolean disponibilite) {
		this.disponible = disponibilite;
	}
	
	
	
	/**
	 * 
	 * @return la face de la carte
	 */
	public Face getFace() {
		return face;
	}
	
	/**
	 *  Met a jour la face de l'image
	 * @param face
	 */
	public void setFace(Face face) {
		this.face = face;
	}	
	/**
	 * 
	 * @return true si la face de la carte est cachee
	 */
	public boolean isFaceCache(){
		return face==Face.CACHE;
	}
}

