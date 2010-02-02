package ch.siagile.finance.app;

import static java.text.MessageFormat.*;
import ch.siagile.finance.position.*;

public class AppContextData {

	private final Positions positions;
	private final String name;
	private AppContextData parent;

	public AppContextData(String name, Positions positions) {
		this.name = name;
		this.positions = positions;
	}

	public AppContextData(String name, Positions positions,
			AppContextData parent) {
		this(name,positions);
		this.parent = parent;
	}

	public Positions positions() {
		return positions;
	}

	public String name() {
		return name;
	}

	public AppContextData parent() {
		return parent;
	}

	public String path() {
		if(hasParent())
			return buildPath();
		return name();
	}

	private String buildPath() {
		if(!"".equals(parent().name.trim()))
			return format("{0}:{1}",parent().path(),name());
		return name();
	}

	boolean hasParent() {
		return parent!=null;
	}
}
