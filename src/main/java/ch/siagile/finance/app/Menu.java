package ch.siagile.finance.app;


public interface Menu {

	boolean isCalled(String line);

	void perform(AppContext context, String line);

	void parent(MenuList menuList);

	String describe();

}
