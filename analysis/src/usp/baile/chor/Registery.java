package usp.baile.chor;

import java.util.List;

public interface Registery {
	
	public void addService(Service service);

	public List<Service> findServices(Role role);
}
