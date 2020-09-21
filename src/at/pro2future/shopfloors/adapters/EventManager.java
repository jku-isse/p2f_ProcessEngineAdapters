package at.pro2future.shopfloors.adapters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import ProcessCore.Event;
import at.pro2future.shopfloors.adapters.EventHandler;

// an event manager distributes an incoming event
// based on the event role
// to all event handlers listening this event and role.
// do not use this class directly, it is included in EventProvider

public class EventManager {
	final String EVENTNAME;
	Map<String, LinkedList<EventHandler>> handlers;
	public EventManager(String eventName) {
		EVENTNAME = eventName;
		handlers = new HashMap<>();
	}
	public void addEventHandler(EventHandler handler) {
		//System.out.println(handler.getEventType().getRole());
		if(handler.getRole() != null) {
			addEventWithRole(handler);
		} else {
			addEventWithoutRole(handler);
		}
	}
	private void addEventWithRole(EventHandler handler) {
		//System.out.println(handler.getEventType().getRole());
		if(!handlers.keySet().contains(handler.getRole().getName())) {
			handlers.put(handler.getRole().getName(), new LinkedList<EventHandler>());
		} 
		LinkedList<EventHandler> handlerList = handlers.get(handler.getRole().getName());
		handlerList.add(handler);
	}
	private void addEventWithoutRole(EventHandler handler) {
		if(!handlers.keySet().contains("")) {
			handlers.put("", new LinkedList<EventHandler>());
		} 
		LinkedList<EventHandler> handlerList = handlers.get("");
		handlerList.add(handler);
	}
	public void removeEvent(EventHandler handler) {
		if(handler.getEventType().getRole() != null) {
			removeEventWithRole(handler);
		} else {
			removeEventWithoutRole(handler);
		}
	}
	private void removeEventWithRole(EventHandler handler) {
		if(handlers.keySet().contains(handler.getEventType().getRole().getName())) {
			LinkedList<EventHandler> handlerList = handlers.get(handler.getRole().getName());
			handlerList.remove(handler);
		} 
	}
	private void removeEventWithoutRole(EventHandler handler) {
		if(!handlers.keySet().contains("")) {
			LinkedList<EventHandler> handlerList = handlers.get("");
			handlerList.remove(handler);
		} 
	}
	public LinkedList<EventHandler> getHandlers(String role) {
		if(handlers.containsKey(role)) {
			return handlers.get(role);
		}
		return new LinkedList<>();
	}
}
