package riskman.command;
import static java.lang.String.*;
import riskman.position.Positions;

public class DeleteCommand extends BaseCommand {

	public String describe() {
		return "	'd or delete'			- removes from memory all the positions loaded";
	}

	public void execute(String definition) {
		Positions positions = workspace.clearAll();
		println(format("Removed %1$s positions!",positions.size()));
	}

}
