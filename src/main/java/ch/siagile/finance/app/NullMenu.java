package ch.siagile.finance.app;


public class NullMenu extends BaseMenu {

	public boolean isCalled(String line) {
		return true;
	}

	public void perform(AppContext context, String line) {
		println(line + "- command not found. Are you kidding me?");
	}

	@Override
	public String describe() {
		return "";
	}

}
