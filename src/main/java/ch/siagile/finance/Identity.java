package ch.siagile.finance;


public class Identity {

	private String id;

	public void identifiedBy(String id) {
		this.id = id;
	}

	public boolean isIdentifiedBy(String anId) {
		return id.equals(anId);
	}

}
