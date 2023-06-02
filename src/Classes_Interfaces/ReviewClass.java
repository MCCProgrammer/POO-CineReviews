package Classes_Interfaces;

public class ReviewClass implements Review {

	enum ClassificacionEnum {
		excellent(5), good(4), average(3), poor(2), terrible(1);

		private final int numero;

		private ClassificacionEnum(int numero) {
			this.numero = numero;
		}

		public int getNumero() {
			return numero;
		}
	}

	private String title, comment;
	private String classification;
	private User writer;

	public ReviewClass(User writer, String title, String comment, String classification) {
		this.writer = writer;
		this.title = title;
		this.comment = comment;
		this.classification = classification;

	}

	private int classificacionToInt(String classification) {
		return ClassificacionEnum.valueOf(classification).getNumero();
	}

	@Override
	public User getWriter() {
		return writer;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		return comment;
	}

	@Override
	public String getClassification() {
		return classification;
	}

	@Override
	public int getClassificationValue() {
		return classificacionToInt(classification);
	}

	@Override
	public int compareTo(Review o) {
		User user1 = this.getWriter();
		User user2 = o.getWriter();
		boolean thisIsCritic = user1 instanceof CriticClass;
		boolean otherIsCritic = user2 instanceof CriticClass;
		if (thisIsCritic != otherIsCritic) {
			if (thisIsCritic) {
				return -1;
			} else {
				return 1;
			}
		}
		int thisClassificacion = this.getClassificationValue();
		int otherClassfication = o.getClassificationValue();
		if (thisClassificacion != otherClassfication) {
			return otherClassfication - thisClassificacion;
		}
		return user1.getUsername().compareTo(user2.getUsername());
	}
}
