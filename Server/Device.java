public class Device {
	private boolean compA, compB, compC;

	public Device(boolean componentA, boolean componentB, boolean componentC) {
		compA = componentA;
		compB = componentB;
		compC = componentC;
	}

	public void setStatus(String comp, boolean status) {
		if (comp != null) {
			if (comp.equals("A"))
				compA = status;
			else if (comp.equals("B"))
				compB = status;
			else if (comp.equals("C"))
				compC = status;
		}
	}

	public boolean getStatus(String comp) {
		if (comp.equals("A"))
			return compA;
		else if (comp.equals("B"))
			return compB;
		else if (comp.equals("C"))
			return compC;
		else
			return false;
	}

	public String convertToString() {
		if (!compA && !compB && !compC)
			return "0";
		else if (!compA && !compB && compC)
			return "1";
		else if (!compA && compB && !compC)
			return "2";
		else if (!compA && compB && compC)
			return "3";
		else if (compA && !compB && !compC)
			return "4";
		else if (compA && !compB && compC)
			return "5";
		else if (compA && compB && !compC)
			return "6";
		else if (compA && compB && compC)
			return "7";
		else
			return "8";
	}
	
	public void convertFromString(String input) {
		switch (input) {
		case "A":
			compA = !compA;
			break;
		case "B":
			compB = !compB;
			break;
		case "C":
			compC = !compC;
			break;
		default:
			break;
		}
	}

}

