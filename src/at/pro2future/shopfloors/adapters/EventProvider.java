package at.pro2future.shopfloors.adapters;

import java.util.HashMap;
import java.util.Map;


import ProcessCore.Event;
import at.pro2future.shopfloors.adapters.EventHandler;
import at.pro2future.shopfloors.adapters.EventManager;

// Starting point for developing your own event provider.
// example provider for opcua events in 
// at.pro2future.shopfloors.sts.mocks.MockOpcEventProvider

public abstract class EventProvider {
	protected Map<String, EventManager> managers;
	public EventProvider() {

		managers = new HashMap<>();
	}

	public void registerEventHandler(EventHandler handler) {
		if(!managers.containsKey(handler.getEventType().getName())) {
			managers.put(handler.getEventType().getName(), new EventManager(handler.getEventType().getName()));
		}
		managers.get(handler.getEventType().getName()).addEventHandler(handler);
	}

	public void deregisterEventHandler(EventHandler handler) {
		if(managers.containsKey(handler.getEventType().getName())) {
			managers.get(handler.getEventType().getName()).removeEvent(handler);
		}
	}

	public abstract void checkForEvents();

}
