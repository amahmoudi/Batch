package com.uff.logger;
/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class DetailMessage {
	
	private int numeroLigne;
	private String sLigne;
	private String sException;
	
	public DetailMessage() {
	}
	public DetailMessage(int numeroLigne, String sLigne, String sException) {
		super();
		this.numeroLigne = numeroLigne;
		this.sLigne = sLigne;
		this.sException = sException;
	}
	public int getNumeroLigne() {
		return numeroLigne;
	}
	public void setNumeroLigne(int numeroLigne) {
		this.numeroLigne = numeroLigne;
	}
	public String getsLigne() {
		return sLigne;
	}
	public void setsLigne(String sLigne) {
		this.sLigne = sLigne;
	}
	public String getsException() {
		return sException;
	}
	public void setsException(String sException) {
		this.sException = sException;
	}
	@Override
	public String toString() {
		return String.format("DetailMessage [numeroLigne=%s, sLigne=%s, sException=%s]", numeroLigne, sLigne,
				sException);
	}
	
}
