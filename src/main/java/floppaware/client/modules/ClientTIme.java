package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;

public class ClientTIme extends Module{
	
	public long nTime;
	
	SliderSetting time = new SliderSetting("Time", this, 0, 0, 23450, true);
	
	public ClientTIme() {
		super("ClientTime", "ClientTime description", -1, Category.Render, true);
		// TODO Auto-generated constructor stub
		addSetting(time);
	}
	
//	@PogEvent
//	public void onEnable() {
//		if (mc.world == null) return;
//		nTime = mc.world.getTimeOfDay();
//	}
//	
	@PogEvent
	public void onTick(TickEvent event) {
		if (mc.world == null) return;
		mc.world.setTimeOfDay((long) time.get());
		
	}
//	
//	@PogEvent
//	public void onDisable() {
//		if (mc.world == null) return;
//		mc.world.setTimeOfDay(nTime);
//	}
	
}
