package ch.siagile.finance.instrument.rating;

public class MoodyRating extends Rating {

	private final int 		id;
	private final String 	value;

	public MoodyRating(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public static Rating from(int id, String value) {
		return new MoodyRating(id, value);
	}

	@Override
	public boolean equals(Object obj) {
		if(!MoodyRating.class.isInstance(obj)) return false;
		MoodyRating rating = (MoodyRating)obj;
		if(id != rating.id)	return false;
		return value.equals(rating.value);
	}

	@Override
	public String toString() {
		return "Moody Rating id("+id+") value:"+value;
	}

}
 