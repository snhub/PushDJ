package de.sntr.pushdj.traktor;

import java.util.ArrayList;
import java.util.List;

public class TraktorMessage {

	public String name;
	public int command;
	public int channel;
	public int data1;
	public int data2;
	
	public List<TraktorMessageListener> listeners = new ArrayList<>();
	
	public TraktorMessage(String name, int[] values) {
		this.name = name;
		this.command = values[0];
		this.channel = values[1];
		this.data1 = values[2];
		this.data2 = values[3];
	}
	
	public void addListener(TraktorMessageListener l) {
		listeners.add(l);
	}
	
	public void sent() {
System.out.println("Msg> " +command +":"+channel+","+data1+","+data2);
		for (TraktorMessageListener l : listeners) {
			l.traktorMessageSent(this);
		}
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj == null || !(obj instanceof TraktorMessage)) {
//			return false;
//		}
//		TraktorMessage m = (TraktorMessage)obj;
////		if(this.command == m.command && this.channel == m.channel && this.data1 == m.data1 && this.data2 == m.data2) {
////			return true;
////		}
//		if(this.name.equals(m.name)) {
//			return true;
//		}
//		return false;
//		
//	}
}
