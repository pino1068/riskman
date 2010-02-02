package ch.siagile.finance.app;


public class EmptyMenu extends BaseMenu {

	public boolean isCalled(String line) {
		return line == null || "".equals(line.replaceAll(" ", ""));
	}

	public void perform(AppContext context, String line) {
		println("and now what?");
	}

	@Override
	public String describe() {
		return "";
	}

}
