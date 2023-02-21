package rest.agence.cli;

import java.io.BufferedReader;
import java.io.IOException;

public class FloatPriceInputProcessorFirstPriceMin extends ComplexUserInputProcessor<Integer> {
	/* Attribut */
	protected float prixMin; 

	/* Getter then Setters */
	public float getPrixMin() {
		return prixMin;
	}
	public void setPrixMin(float prixMin) {
		this.prixMin = prixMin;
	}
	
	/* Constructeurs */
	public  FloatPriceInputProcessorFirstPriceMin(BufferedReader reader, String message) {
		super(reader, message);
	}

	class FloatPriceMax extends FloatPriceInputProcessorFirstPriceMin {

		public FloatPriceMax(BufferedReader reader, String message) {
			super(reader, message);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void setMessage(String message) {
			this.message = message;

		}
		@Override
		protected void setValidityCriterion() {
			isValid = str -> {
				try {
					Float prixMax = Float.parseFloat(str);
					if (prixMax < prixMin) {
						System.err.println("Le prix maximal ne peut être inférieur au prix minimal : "+prixMin);
						return false; 
					}else {	return true;}
				} catch (NumberFormatException e) {
					System.err.println("Merci de bien vouloir entrée un nombre entier et non un "
							+ "caractère spécial ou des lettres");
					return false;
				}
			};
		}

			@Override
			protected void setParser() {
				try {
					parser = Float.class.getMethod("parseFloat", String.class);
				} catch (SecurityException | NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		
	}
	
	@Override
	protected void setMessage(String message) {
		this.message =	message;
	}
	
	@Override
	protected void setValidityCriterion() {
		isValid = str -> {
			try {
//				Integer value = Integer.parseInt(str);
				Float prixMin = Float.parseFloat(str);
				if (prixMin < 0){
					return false;
				}else {	
					setPrixMin(prixMin);
					addToListPriceIntervalle(prixMin);
					FloatPriceMax prixMax = new FloatPriceMax(inputReader, 
							"Veuillez indiquer le prix maximum pour un intervalle de prix : \n" 
							 +"sachant que vous avez indiqué le prix minimum est de " 
							 + getPrixMin());
					addToListPriceIntervalle(prixMax.floatProcess());
					return true; 
					}
			} catch (NumberFormatException | IOException e) {
				System.err.println("Merci de bien vouloir entrée un nombre entier et non un "
						+ "caractère spécial ou des lettres");
				return false;
			}
		};
	}

		@Override
		protected void setParser() {
			try {
				parser = Float.class.getMethod("parseFloat", String.class);
//				parser = Integer.class.getMethod("parseInt", String.class);
			} catch (SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
}
		

