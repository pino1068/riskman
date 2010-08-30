package riskman.app;

import riskman.position.*;

class WorkspaceData {

	private final String name;
	private final Positions positions;
	private final WorkspaceData previous;

	public WorkspaceData(String name, Positions positions, WorkspaceData previous) {
		this.name = name;
		this.positions = positions;
		this.previous = previous;
	}

	public WorkspaceData(String name, Positions positions) {
		this(name,positions,null);
	}

	public String name() {
		return name;
	}

	public Positions positions() {
//		Collection.sort
		return positions;
	}

	public WorkspaceData previous() {
		return previous;
	}

	public boolean hasPrevious() {
		return previous!=null;
	}

	public String path() {
		StringBuffer result = new StringBuffer();
		if(hasPrevious())
			result.append(previous.path()).append(":");
		return result.append(name).toString();
	}

}
