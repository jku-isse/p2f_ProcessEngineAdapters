package at.pro2future.shopfloors.adapters;

import java.util.LinkedList;
import java.util.List;

import ProcessCore.Event;
import ProcessCore.Parameter;

// class stub for an Event instance of a given Event type.
public class EventInstance {
	public Event eventType;
	public List<Parameter> parameters;

	public EventInstance(Event ev) {
		this.eventType = ev;
		parameters = new LinkedList<>();
	}

	public EventInstance(EventInstance evi) {
		this.eventType = evi.eventType;
		parameters = new LinkedList<>(evi.parameters);
	}

}