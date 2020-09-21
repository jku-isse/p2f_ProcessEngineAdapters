package at.pro2future.shopfloors.adapters;

import ProcessCore.Assignment;
import ProcessCore.Event;
import ProcessCore.AbstractCapability;
import ProcessCore.Parameter;
import at.pro2future.shopfloors.adapters.AdapterEventProvider;

import java.util.List;

public interface EngineAdapter {
	
	public List<Parameter> invokeCapability(AbstractCapability capability, List<Parameter> parameterValues);
	public Assignment getRole();
	public void registerWithEngine(AdapterEventProvider engine);
	
	public List<EventHandler> getReceivedEvents();
}
