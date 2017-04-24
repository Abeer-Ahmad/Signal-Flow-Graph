package sfg;

import java.util.ArrayList;

public interface IMasonSolver {

	public Double calculateDeltaTotal();

	public Double calculateDelta(ArrayList<String> forwardPath);

	public Double calculateTF();

}
