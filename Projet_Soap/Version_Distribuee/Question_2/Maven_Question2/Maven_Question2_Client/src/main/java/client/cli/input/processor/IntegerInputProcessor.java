package client.cli.input.processor;

import java.util.Scanner;

public class IntegerInputProcessor extends ComplexeUserInputProcessor<Integer> {

	public IntegerInputProcessor(Scanner inputReader, String message) {
		super(inputReader, message);
	}

	@Override
	protected void setMessage(String message) {
		this.message = message;

	}
	@Override
	protected void setValidityCriterion() {
		isValid = str -> {
			try {
				Integer value = Integer.parseInt(str);
				return value instanceof Integer;
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
				parser = Integer.class.getMethod("parseInt", String.class);
			} catch (SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
}
		

