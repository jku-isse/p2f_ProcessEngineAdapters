package at.pro2future.shopfloors.adapters.tests;

import ProcessCore.Assignment;
import ProcessCore.Parameter;
import ProcessCore.ProcessCoreFactory;
import at.pro2future.shopfloors.adapters.AdapterEventProvider;
import at.pro2future.shopfloors.adapters.EventInstance;

public class TestLoopBackAdapter {
	public static void main(String[] args) {
		AdapterEventProvider prov = new AdapterEventProvider();
		
		Assignment looper = ProcessCoreFactory.eINSTANCE.createAssignment();
		looper.setName("Looper");
		LoopBackAdapter lba = new LoopBackAdapter(looper);
		
		prov.registerEngineAdapter(lba);

		ProcessCore.Event ev = ProcessCoreFactory.eINSTANCE.createEvent();
		ev.setName("Hello");
		EventInstance evi = new EventInstance(ev);
		Parameter p = ProcessCoreFactory.eINSTANCE.createParameter();
		p.setType("String");
		p.setName("Address");
		p.setValue("World");
		evi.parameters.add(p);
		
		ev.setRole(looper);
		
		prov.enqueueEvent(evi);
	}
}
