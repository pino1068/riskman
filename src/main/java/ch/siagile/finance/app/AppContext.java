package ch.siagile.finance.app;

import ch.siagile.finance.position.*;

public class AppContext {

	private AppContextData current;

	public AppContext(String name, Positions positions) {
		this.current = new AppContextData(name,positions);
	}

	public String name() {
		return current.name();
	}

	public Positions positions() {
		return current.positions();
	}
	
	public void remove() {
		this.current = current.parent();
	}

	public void changeTo(String name, Positions positions) {
		this.current = new AppContextData(name, positions, current);
	}

	public boolean isNotRoot() {
		return current.hasParent();
	}

	public String path() {
		return current.path();
	}

}
