/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Random;

import modele.Carte.Enseigne;
import modele.Carte.Face;



/**
 * Paquet represente un ensemble de 52 carte qui sera distribue au joueur et au dealer
 * @author MANEL REKIK
 */
public class Paquet {
//	
	
	/**
	 * cet attribut represente une liste de carte d'un paquet
	 */
	private ArrayList<Carte>paquet;
	
	/**
	 * cet attribut represente le nombre des cartes utilisees par le joueur ou le dealer
	 */
	private int carteUtiliser; 
	
	/**
	 * cet attribut represente la liste des cartes defaussees
	 */
	private ArrayList<Carte>defausse;

	/**
	 * la methode d'instance qui va se charger de creer l'objet Paquet
	 */
	public Paquet() {
	
		paquet=new ArrayList<>();
		defausse=new ArrayList<>();
		
		/*
		 * initialization du paquet
		 */
		for(int i=0;i<52;i++){
			paquet.add(null);
		}
		
		/*
		 *Remplissage du paquet avec des cartes non melangees 
		 */
		int j=1;
		for(int i = 0; i < 13; i++){
			paquet.set(i,new Carte(Enseigne.COEUR, j));
			paquet.set(i+13,new Carte(Enseigne.CARREAU,j));
			paquet.set(i+26,new Carte(Enseigne.PIQUE,j));
			paquet.set(i+39,new Carte(Enseigne.TREFLE,j));
			j++;
			}
			carteUtiliser=0;
		}
	
	/**
	 * 
	 * @param min
	 * limite minimale 
	 * @param max
	 * limite maximal
	 * @return un nombre entier au hasard entre une valeur min et une valeur max
	 */
	public int getRandomInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
			}
	/**
	 * melanger les cartes du paquet
	 */
	public void melangerLesCartes(){
		for(int i=0;i<paquet.size();i++){
			/*
			 * avoir un numero au hasard
			 */
			int index=getRandomInt(0, paquet.size()-1);
			Carte carteAleatoire=paquet.get(index);
			paquet.set(index, paquet.get(i));
			paquet.set(i, carteAleatoire);
			}
		}	
		
	/**
	 * 
	 * @return le nombre de cartes restantes
	 */
	public int cartesRestantes() {
		return paquet.size();
	    }
	
	/**
	 * @param carte
	 * la carte a verifier	
	 * @return vrai si la carte existe sinon faux
	 */
	public Boolean verifierPresenceCarte(Carte carte){
		for(Carte c:paquet){
			if(c==carte)return true;
		}
		return false;
	}
	
	/**
	 * Ajouter une carte en dessous du paquet ,
	 * l'objet Arraylist ajoute automatiquement l'objet a la fin 
	 * @param carte 
	 * la nouvelle carte a ajouter au paquet
	 */
	public void ajouterCarteEnDessous(Carte carte){
		/*
		 * impossible d'ajouter une carte si le paquet est complet
		 */
		if(cartesRestantes()==52){
			throw new IndexOutOfBoundsException();
		}
		/*
		 * verification si la carte existe deja
		 */
		if(!verifierPresenceCarte(carte)){
			paquet.add(carte);return;
		}
		return;
		}
	
	/**
	 * Ajouter une carte au dessus du paquet , 
	 * @param carte 
	 * la nouvelle carte a ajouter au paquet
	 */
	public void ajouterCarteAuDessus(Carte carte){
		/*
		 * impossible d'ajouter une carte si le paquet est complet
		 */
		if(cartesRestantes()==52){
			throw new IndexOutOfBoundsException();
		}
		/*
		 * verification si la carte existe deja
		 */ 
		if(!verifierPresenceCarte(carte)){
			paquet.add(0,carte);return;
		}
		return;
		}
	
	/**
	 * pour tirer une carte du dessous du paquet
	 * 
	 * @return la carte tiree
	 */
	public Carte tirerCarteDuDessous(){
		Carte carteRetire=null;
		/*
		 * verifier si le paquet a toujours des cartes
		 */
		if(cartesRestantes()>0){
			carteRetire =paquet.get(paquet.size()-1);
			carteRetire.setFace(Face.VISIBLE);
			paquet.remove(paquet.size()-1);
			carteUtiliser++;
			defausse.add(carteRetire);
			return carteRetire;
		}
		return null;
		}
	
	/**
	 * pour tirer une carte du dessus du paquet
	 * 
	 * @return la carte tiree
	 */
	public Carte tirerCarteduDessus(){
		Carte carteRetire=null;
		/*
		 * verifier si le paquet a toujours des cartes
		 */
		if(cartesRestantes()>0){
			carteRetire=paquet.get(0);
			paquet.remove(0);
			carteUtiliser++;
			defausse.add(carteRetire);
			return carteRetire;
			}
		return null;
		}
		
	/**
	 *pour tirer une carte au hasard du paquet
	 * @return la carte tiree au hasard
	 */
	public Carte tirerUneCarteAuHasard(){
		Carte carteRetire=null;
		if(cartesRestantes()>0){
			/*
			 * on melange les cartes avant pour eviter le comptage (triche)
			 */
			melangerLesCartes();
			/*
			 * avoir un index au hasard de la carte a tirer 
			 */
			int index=getRandomInt(0, cartesRestantes());
			carteRetire=paquet.get(index);
			paquet.remove(index);
			carteUtiliser++;
			defausse.add(carteRetire);
			return carteRetire;
		}
		return null;
		}
	
	/**
	 * pour couper un paquet a une position aleatoire uniforme (differente des trois 
	 * premieres et des trois dernieres)
	 */
	public void couperPaquet(){
		int index=getRandomInt(4,cartesRestantes()-4);
		System.out.println("numero de coupage est "+index);
		ArrayList<Carte>partie1=new ArrayList<>();
		ArrayList<Carte>partie2=new ArrayList<>();
		ArrayList<Carte>concatPartis=new ArrayList<>();	
		for(int i=0;i<index;i++){
			partie1.add(paquet.get(i));
		}
		for(int i=index;i<cartesRestantes();i++){
			partie2.add(paquet.get(i));
		}
		concatPartis.addAll(partie2);
		concatPartis.addAll(partie1);
		setPaquet(concatPartis);
		}
        /**
	 * 		
	 * @return le nombre de carte utilise
	 */
	public int getCarteUtiliser() {
		return carteUtiliser;
	}
	
	/**
	 * Met a jour le nombre de carte utilise
	 * @param carteUtiliser
	 * la nouvelle valeur du nombre des cartes utilisees 
	 */
	public void setCarteUtiliser(int carteUtiliser) {
		this.carteUtiliser = carteUtiliser;
	}
	
	
	
	/**
	 * @return le paquet
	 */
	public ArrayList<Carte> getPaquet() {
		return paquet;
	}
	
	/**
	 * Met a jour le paquet
	 * @param paquet
	 * nouvel valeur du paquet		
	 */
	public void setPaquet(ArrayList<Carte> paquet) {
		this.paquet = paquet;
	}
	
	/**
	 * 
	 * @return une liste des cartes defaussees
	 */
	public ArrayList<Carte> getDefausse() {
	return defausse;
	}
	
	/**
	 * Met a jour la liste des cartes defaussees
	 * @param defausse
	 * nouvelle valeur de la liste des cartes defaussees
	 */
	public void setDefausse(ArrayList<Carte> defausse) {
	this.defausse = defausse;
	}	
	
}
