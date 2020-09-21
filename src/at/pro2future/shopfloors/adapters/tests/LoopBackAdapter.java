package at.pro2future.shopfloors.adapters.tests;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ProcessCore.AbstractCapability;
import ProcessCore.Assignment;
import ProcessCore.Event;
import ProcessCore.Parameter;
import ProcessCore.ProcessCoreFactory;
import at.pro2future.shopfloors.adapters.AdapterEventProvider;
import at.pro2future.shopfloors.adapters.EngineAdapter;
import at.pro2future.shopfloors.adapters.EventHandler;
import at.pro2future.shopfloors.adapters.EventInstance;

public class LoopBackAdapter implements EngineAdapter{

	AdapterEventProvider provider;
	
	ProcessCore.Event helloEvent;
	ProcessCore.Event responseEvent;
	
	EventHandler helloHandler;
	EventInstance evi;

	private Assignment role;
	public LoopBackAdapter(Assignment r) {
		helloEvent = ProcessCoreFactory.eINSTANCE.createEvent();
		helloEvent.setName("Hello");
		responseEvent = ProcessCoreFactory.eINSTANCE.createEvent();
		responseEvent.setName("Response");
		role = r;
		
		helloHandler = new EventHandler() {
			@Override
			public void handleEvent(EventInstance e) {
				for(Parameter p : e.parameters) {
					if(p.getName().equals("Address")) {
						System.out.println("Hello, " + p.getValue() + "!");
					}
				}
				e.eventType = responseEvent;
				provider.enqueueEvent(e);
				evi = e;
			}

			@Override
			public EventInstance getEvent() {
				return evi;
			}

			@Override
			public Event getEventType() {
				return helloEvent;
			}

			@Override
			public Assignment getRole() {
				return role;
			}
			
		};
		
	}
	@Override
	public List<Parameter> invokeCapability(AbstractCapability capability, List<Parameter> parameterValues) {
		// has no capabilities
		return null;
	}

	@Override
	public Assignment getRole() {
		return role;
	}

	@Override
	public void registerWithEngine(AdapterEventProvider engine) {
		provider = engine;
	}
	@Override
	public List<EventHandler> getReceivedEvents() {
		List<EventHandler> events = new LinkedList<>();
		events.add(helloHandler);
		return events;
	}

}
