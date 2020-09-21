package at.pro2future.shopfloors.adapters;

import at.pro2future.shopfloors.adapters.EventProvider;

import java.util.LinkedList;
import java.util.List;

import ProcessCore.AbstractCapability;
import ProcessCore.Assignment;
import ProcessCore.Event;
import ProcessCore.Parameter;
import at.pro2future.shopfloors.adapters.EventHandler;
import at.pro2future.shopfloors.adapters.EventInstance;
import at.pro2future.shopfloors.adapters.EventManager;

public class AdapterEventProvider extends EventProvider {

	@Override
	public void checkForEvents() {
		// TODO Auto-generated method stub
		
	}
	
	public void registerEngineAdapter(EngineAdapter adapter) {
		for(EventHandler ev : adapter.getReceivedEvents()) {
			if(!managers.containsKey(ev.getEventType().getName())) {
				EventManager em = new EventManager(ev.getEventType().getName());
				managers.put(ev.getEventType().getName(), em);
			}
			managers.get(ev.getEventType().getName()).addEventHandler(ev);
			adapter.registerWithEngine(this);
		}
		
	}
	public void deregisterEngineAdapter(EngineAdapter adapter) {
		
	}
	
	public void enqueueEvent(EventInstance ev) {
		String name = ev.eventType.getName();
		EventManager manager = managers.get(name);
		if(manager == null) {
			System.out.println("No Event manager for " + name);
			return;
		}
		String role = ev.eventType.getRole().getName();
		LinkedList<EventHandler> handlerList = manager.getHandlers(role);
		for(EventHandler handler : handlerList) {
    		EventInstance inst = new EventInstance(ev);
    		handler.handleEvent(inst);
		}
	}

}
