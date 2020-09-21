package at.pro2future.shopfloors.adapters;

import java.net.URI;

import ProcessCore.Assignment;
import ProcessCore.Event;

// interface for Event handlers used in process Engine.
public interface EventHandler{
	public void handleEvent(EventInstance e);
	public EventInstance getEvent();
	public Event getEventType();
	public Assignment getRole();
}
