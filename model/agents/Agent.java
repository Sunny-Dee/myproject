package myproject.model.agents;

/**
 * Interface for active model objects.
 */
public interface Agent {
	public void run(double time);

	public boolean isNull();

	public double getDimension();

	public int getState();
}
