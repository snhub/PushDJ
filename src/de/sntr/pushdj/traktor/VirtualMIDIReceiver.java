package de.sntr.pushdj.traktor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

import de.tobiaserichsen.tevm.TeVirtualMIDI;
import de.tobiaserichsen.tevm.TeVirtualMIDITest;

/**
 * Receives Traktor Messages
 * @author sntrAdmin
 *
 */
public class VirtualMIDIReceiver extends Thread {

	public static volatile boolean running = true;
	
	private TeVirtualMIDI vMidi;
	
	public VirtualMIDIReceiver(TeVirtualMIDI vMidi) {
		this.vMidi = vMidi;
	}
	
	@Override
	public void run() {
		byte[] message;
		
		while(running) {
			message = vMidi.getCommand();
			if(message != null) {
				byte status = message[0];
				byte data1 = message[1];
				byte data2 = message[2];
				ShortMessage sm = null;
				try {
					sm = new ShortMessage(status, data1, data2);
				} catch (InvalidMidiDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int command = sm.getCommand();
				if(command == 0xb0) {
					if(sm.getChannel() == 0) { //Global
						switch (sm.getData1()) {
						case 32:
							
							break;

						default:
							break;
						}
					}
					else if(sm.getChannel() == 1) { //deckA
						switch (sm.getData1()) {
						case 6:
							TraktorAdapter.fluxOnOffDeckAReturn.data2 = sm.getData2();
							TraktorAdapter.fluxOnOffDeckAReturn.sent();
							break;
						case 10:
							TraktorAdapter.beatPhaseReturnDeckA.data2 = sm.getData2();
							TraktorAdapter.beatPhaseReturnDeckA.sent();
							break;
						case 29:
							TraktorAdapter.hotcueTypeDeckA1.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA1.sent();
							break;
						case 30:
							TraktorAdapter.hotcueTypeDeckA2.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA2.sent();
							break;
						case 31:
							TraktorAdapter.hotcueTypeDeckA3.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA3.sent();
							break;
						case 32:
							TraktorAdapter.hotcueTypeDeckA4.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA4.sent();
							break;
						case 33:
							TraktorAdapter.hotcueTypeDeckA5.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA5.sent();
							break;
						case 34:
							TraktorAdapter.hotcueTypeDeckA6.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA6.sent();
							break;
						case 35:
							TraktorAdapter.hotcueTypeDeckA7.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA7.sent();
							break;
						case 36:
							TraktorAdapter.hotcueTypeDeckA8.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckA8.sent();
							break;
						case 37:
							TraktorAdapter.playReturnDeckA.data2 = sm.getData2();
							TraktorAdapter.playReturnDeckA.sent();
							break;
						case 44:
							TraktorAdapter.syncOnOffReturnDeckA.data2 = sm.getData2();
							TraktorAdapter.syncOnOffReturnDeckA.sent();
							break;
						case 46:
							TraktorAdapter.tempoMasterDeckAReturn.data2 = sm.getData2();
							TraktorAdapter.tempoMasterDeckAReturn.sent();
							break;
						case 47:
							
							break;
						case 48:
							
							break;
						case 56:
							break;

						case 97:
							TraktorAdapter.deckLoadedReturnDeckA.data2 = sm.getData2();
							TraktorAdapter.deckLoadedReturnDeckA.sent();
							break;
						case 98:
							TraktorAdapter.loopActiveReturnDeckA.data2 = sm.getData2();
							TraktorAdapter.loopActiveReturnDeckA.sent();
							break;
						default:
							break;
						}
							
					}
					else if(sm.getChannel() == 2) { //deckB
						switch (sm.getData1()) {
						case 6:
							TraktorAdapter.fluxOnOffDeckBReturn.data2 = sm.getData2();
							TraktorAdapter.fluxOnOffDeckBReturn.sent();
							break;
						case 10:
							TraktorAdapter.beatPhaseReturnDeckB.data2 = sm.getData2();
							TraktorAdapter.beatPhaseReturnDeckB.sent();
							break;
						case 29:
							TraktorAdapter.hotcueTypeDeckB1.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB1.sent();
							break;
						case 30:
							TraktorAdapter.hotcueTypeDeckB2.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB2.sent();
							break;
						case 31:
							TraktorAdapter.hotcueTypeDeckB3.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB3.sent();
							break;
						case 32:
							TraktorAdapter.hotcueTypeDeckB4.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB4.sent();
							break;
						case 33:
							TraktorAdapter.hotcueTypeDeckB5.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB5.sent();
							break;
						case 34:
							TraktorAdapter.hotcueTypeDeckB6.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB6.sent();
							break;
						case 35:
							TraktorAdapter.hotcueTypeDeckB7.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB7.sent();
							break;
						case 36:
							TraktorAdapter.hotcueTypeDeckB8.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckB8.sent();
							break;
						case 37:
							TraktorAdapter.playReturnDeckB.data2 = sm.getData2();
							TraktorAdapter.playReturnDeckB.sent();
							break;
						case 44:
							TraktorAdapter.syncOnOffReturnDeckB.data2 = sm.getData2();
							TraktorAdapter.syncOnOffReturnDeckB.sent();
							break;
						case 46:
							TraktorAdapter.tempoMasterDeckBReturn.data2 = sm.getData2();
							TraktorAdapter.tempoMasterDeckBReturn.sent();
							break;
						case 47:
							
							break;
						case 48:
							
							break;
						case 56:
							break;

						case 97:
							TraktorAdapter.deckLoadedReturnDeckB.data2 = sm.getData2();
							TraktorAdapter.deckLoadedReturnDeckB.sent();
							break;
						case 98:
							TraktorAdapter.loopActiveReturnDeckB.data2 = sm.getData2();
							TraktorAdapter.loopActiveReturnDeckB.sent();
							break;
						default:
							break;
						}	
					}
					else if(sm.getChannel() == 3) { //deckC
						switch (sm.getData1()) {
						case 6:
							TraktorAdapter.fluxOnOffDeckCReturn.data2 = sm.getData2();
							TraktorAdapter.fluxOnOffDeckCReturn.sent();
							break;
						case 10:
							TraktorAdapter.beatPhaseReturnDeckC.data2 = sm.getData2();
							TraktorAdapter.beatPhaseReturnDeckC.sent();
							break;
						case 29:
							TraktorAdapter.hotcueTypeDeckC1.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC1.sent();
							break;
						case 30:
							TraktorAdapter.hotcueTypeDeckC2.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC2.sent();
							break;
						case 31:
							TraktorAdapter.hotcueTypeDeckC3.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC3.sent();
							break;
						case 32:
							TraktorAdapter.hotcueTypeDeckC4.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC4.sent();
							break;
						case 33:
							TraktorAdapter.hotcueTypeDeckC5.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC5.sent();
							break;
						case 34:
							TraktorAdapter.hotcueTypeDeckC6.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC6.sent();
							break;
						case 35:
							TraktorAdapter.hotcueTypeDeckC7.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC7.sent();
							break;
						case 36:
							TraktorAdapter.hotcueTypeDeckC8.data2 = sm.getData2();
							TraktorAdapter.hotcueTypeDeckC8.sent();
							break;
						case 37:
							TraktorAdapter.playReturnDeckC.data2 = sm.getData2();
							TraktorAdapter.playReturnDeckC.sent();
							break;
						case 44:
							TraktorAdapter.syncOnOffReturnDeckC.data2 = sm.getData2();
							TraktorAdapter.syncOnOffReturnDeckC.sent();
							break;
						case 46:
							TraktorAdapter.tempoMasterDeckCReturn.data2 = sm.getData2();
							TraktorAdapter.tempoMasterDeckCReturn.sent();
							break;
						case 47:
							
							break;
						case 48:
							
							break;
						case 56:
							break;

						case 97:
							TraktorAdapter.deckLoadedReturnDeckC.data2 = sm.getData2();
							TraktorAdapter.deckLoadedReturnDeckC.sent();
							break;
						case 98:
							TraktorAdapter.loopActiveReturnDeckC.data2 = sm.getData2();
							TraktorAdapter.loopActiveReturnDeckC.sent();
							break;
						default:
							break;
						}
					}
				}
				System.out.println("VMR> " +TeVirtualMIDITest.byteArrayToString(message));
			}
		}
		
	}
	
}
