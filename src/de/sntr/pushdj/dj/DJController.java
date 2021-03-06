package de.sntr.pushdj.dj;

import static de.sntr.pushdj.push.PushAdapter.setColor;
import static de.sntr.pushdj.traktor.TraktorAdapter.send;
import de.sntr.pushdj.dj.Deck.Colors;
import de.sntr.pushdj.push.ButtonListener;
import de.sntr.pushdj.push.Encoder;
import de.sntr.pushdj.push.EncoderListener;
import de.sntr.pushdj.push.MatrixButton;
import de.sntr.pushdj.push.PushAdapter;
import de.sntr.pushdj.push.Button;
import de.sntr.pushdj.push.ResolutionButton;
import de.sntr.pushdj.push.TitleButton;
import de.sntr.pushdj.push.Display.Graphics;
import de.sntr.pushdj.traktor.TraktorAdapter;
import de.sntr.pushdj.traktor.TraktorMessage;

/**
 * Main control class.
 * Processes all top-level functions
 * @author adm9
 *
 */
public class DJController implements ButtonListener, EncoderListener {

	static final int deckActiveColor = MatrixButton.GRAY_DARK;
	static final int deckInactiveColor = MatrixButton.GRAY_BRIGHT;
	static final int viewDeckUpperOff = ResolutionButton.GREEN_MEDIUM_ON;
	static final int viewDeckLowerOff = MatrixButton.GREEN1_MEDIUM;
	static final int viewDeckUpperOn = ResolutionButton.GREEN_BRIGHT_ON;
	static final int viewDeckLowerOn = MatrixButton.GREEN1_BRIGHT;
	
	Button shiftGreenControl, shiftRedControl;
	Button tempoFocusDeckAControl, tempoFocusDeckBControl, tempoFocusDeckCControl, tempoFocusDeckDControl;
	Encoder tempoFineControl, tempoCoarseControl;
	
	Button browserUpControl, browserDownControl;
	TraktorMessage browserUpMessage, browserDownMessage;
	
	Button loadLeftControl, loadRightControl;
	TraktorMessage loadDeckAMessage, loadDeckBMessage, loadDeckCMessage, loadDeckDMessage;
	
	Button viewLeftDeckAControl, viewLeftDeckBControl, viewLeftDeckCControl, viewLeftDeckDControl;
	Button viewRightDeckAControl, viewRightDeckBControl, viewRightDeckCControl, viewRightDeckDControl;
	
	TraktorMessage sendMonitorState;
	
	private TrackDeck deckALeft = new TrackDeck(this);
	private TrackDeck deckARight = new TrackDeck(this);
	private TrackDeck deckBLeft = new TrackDeck(this);
	private TrackDeck deckBRight = new TrackDeck(this);
	private TrackDeck deckCLeft = new TrackDeck(this);
	private TrackDeck deckCRight = new TrackDeck(this);
	private TrackDeck deckDLeft = new TrackDeck(this);
	private TrackDeck deckDRight = new TrackDeck(this);

	public boolean shiftGreenDown = false;
	public boolean shiftRedDown = false;


	Focus focus = Focus.Clock;

	public DJController() {

		PushAdapter.display.writeColumn(0, Graphics.BigA);
		PushAdapter.display.writeColumn(3, Graphics.BigB);
		PushAdapter.display.update();

		DeckInit.initTrackDeckALeft(deckALeft);
		DeckInit.initTrackDeckARight(deckARight);
		DeckInit.initTrackDeckBLeft(deckBLeft);
		DeckInit.initTrackDeckBRight(deckBRight);
		DeckInit.initTrackDeckCLeft(deckCLeft);
		DeckInit.initTrackDeckCRight(deckCRight);
//		DeckInit.initTrackDeckDLeft(deckDLeft);
//		DeckInit.initTrackDeckDRight(deckDRight);
		
		setFocusDeckAButton(PushAdapter.undo);
		setFocusDeckBButton(PushAdapter.delete);
		setFocusDeckCButton(PushAdapter.doubble);
		setFocusDeckDButton(PushAdapter.quantize);
		
		setShiftGreenControl(PushAdapter.play);
		setShiftRedControl(PushAdapter.record);

		setTempoCoarseEncoder(PushAdapter.encoders[0]);
		setTempoFineEncoder(PushAdapter.encoders[1]);
		
		setBrowserUp(PushAdapter.up);
		setBrowserDown(PushAdapter.down);
		setLoadLeft(PushAdapter.left);
		setLoadRight(PushAdapter.right);

		setViewLeftDeckAControl(PushAdapter.trackBottom[0]);
		setViewLeftDeckBControl(PushAdapter.trackBottom[1]);
		setViewLeftDeckCControl(PushAdapter.trackBottom[2]);
		setViewLeftDeckDControl(PushAdapter.trackBottom[3]);

		setViewRightDeckAControl(PushAdapter.trackBottom[4]);
		setViewRightDeckBControl(PushAdapter.trackBottom[5]);
		setViewRightDeckCControl(PushAdapter.trackBottom[6]);
		setViewRightDeckDControl(PushAdapter.trackBottom[7]);
		
		activate();
	}
	
	private void activate() {

		setColor(shiftGreenControl, TitleButton.BRIGHT_ON);
		setColor(shiftRedControl, TitleButton.BRIGHT_ON);
		
		setColor(browserUpControl, TitleButton.BRIGHT_ON);
		setColor(browserDownControl, TitleButton.BRIGHT_ON);
		setColor(loadLeftControl, TitleButton.BRIGHT_ON);
		setColor(loadRightControl, TitleButton.BRIGHT_ON);

		setColor(viewLeftDeckAControl, viewDeckLowerOn);
		setColor(viewLeftDeckBControl, viewDeckLowerOff);
		setColor(viewLeftDeckCControl, viewDeckLowerOff);
		setColor(viewLeftDeckDControl, viewDeckLowerOff);

		setColor(viewRightDeckAControl, viewDeckLowerOff);
		setColor(viewRightDeckBControl, viewDeckLowerOn);
		setColor(viewRightDeckCControl, viewDeckLowerOff);
		setColor(viewRightDeckDControl, viewDeckLowerOff);
		
		deckALeft.activate();
		deckBRight.activate();

	}

//	public Button getActiveStepButton(Deck deck) {
//		if(deck == deckALeft || deck == deckARight) {
//			return activeStepButtonDeckA;
//		}
//		if(deck == deckBLeft || deck == deckBRight) {
//			return activeStepButtonDeckB;
//		}
//		if(deck == deckCLeft || deck == deckCRight) {
//			return activeStepButtonDeckC;
//		}
//		if(deck == deckDLeft || deck == deckDRight) {
//			return activeStepButtonDeckD;
//		}
//		return null;
//	}
	
//	public Button setActiveStepButton(Deck deck, Button button) {
//		if(deck == deckALeft || deck == deckARight) {
//			activeStepButtonDeckA = button;
//		}
//		if(deck == deckBLeft || deck == deckBRight) {
//			activeStepButtonDeckB = button;
//		}
//		if(deck == deckCLeft || deck == deckCRight) {
//			activeStepButtonDeckC = button;
//		}
//		if(deck == deckDLeft || deck == deckDRight) {
//			activeStepButtonDeckD = button;
//		}
//		return null;
//	}
	
	public void setActiveStepButton(Deck deck, Button button) {
		if(deck == deckALeft || deck == deckARight) {
			deckALeft.activeStepButton = button;
			deckARight.activeStepButton = button;
		}
		if(deck == deckBLeft || deck == deckBRight) {
			deckBLeft.activeStepButton = button;
			deckBRight.activeStepButton = button;
		}
		if(deck == deckCLeft || deck == deckCRight) {
			deckCLeft.activeStepButton = button;
			deckCRight.activeStepButton = button;
		}
		if(deck == deckDLeft || deck == deckDRight) {
			deckDLeft.activeStepButton = button;
			deckDRight.activeStepButton = button;
		}
	}
	
	/**
	 * Internal Focus
	 * 
	 * @param button
	 */
	public void setFocusDeckAButton(Button button) {
		tempoFocusDeckAControl = button;
		button.addListener(this);
	}

	public void setFocusDeckBButton(Button button) {
		tempoFocusDeckBControl = button;
		button.addListener(this);
	}

	public void setFocusDeckCButton(Button button) {
		tempoFocusDeckCControl = button;
		button.addListener(this);
	}
	
	public void setFocusDeckDButton(Button button) {
		tempoFocusDeckDControl = button;
		button.addListener(this);
	}

	public void setTempoCoarseEncoder(Encoder encoder) {
		tempoCoarseControl = encoder;
		tempoCoarseControl.addListener(this);
	}
	
	public void setTempoFineEncoder(Encoder encoder) {
		tempoFineControl = encoder;
		tempoFineControl.addListener(this);
	}

	public void setBrowserUp(Button button) {
		browserUpControl = button;
		browserUpControl.addListener(this);
	}
	
	public void setBrowserDown(Button button) {
		browserDownControl = button;
		browserDownControl.addListener(this);
	}

	public void setLoadLeft(Button button) {
		loadLeftControl = button;
		loadLeftControl.addListener(this);
	}

	public void setLoadRight(Button button) {
		loadRightControl = button;
		loadRightControl.addListener(this);
	}

	
	public void setShiftGreenControl(Button button) {
		shiftGreenControl = button;
		button.addListener(this);
	}
	
	public void setShiftRedControl(Button button) {
		shiftRedControl = button;
		button.addListener(this);
	}

	public void setViewLeftDeckAControl(Button button) {
		viewLeftDeckAControl = button;
		button.addListener(this);
	}
	
	public void setViewLeftDeckBControl(Button button) {
		viewLeftDeckBControl = button;
		button.addListener(this);
	}

	public void setViewLeftDeckCControl(Button button) {
		viewLeftDeckCControl = button;
		button.addListener(this);
	}
	
	public void setViewLeftDeckDControl(Button button) {
		viewLeftDeckDControl = button;
		button.addListener(this);
	}

	public void setViewRightDeckAControl(Button button) {
		viewRightDeckAControl = button;
		button.addListener(this);
	}
	
	public void setViewRightDeckBControl(Button button) {
		viewRightDeckBControl = button;
		button.addListener(this);
	}
	
	public void setViewRightDeckCControl(Button button) {
		viewRightDeckCControl = button;
		button.addListener(this);
	}
	
	public void setViewRightDeckDControl(Button button) {
		viewRightDeckDControl = button;
		button.addListener(this);
	}
	
	@Override
	public void buttonPressed(Button control) {
		if(control == shiftGreenControl) {
			shiftGreenPressed();
		}
		else if(control == shiftRedControl) {
			shiftRedPressed();
		}
		else if (control == tempoFocusDeckAControl) {
			focusDeckAPressed();
		} else if (control == tempoFocusDeckBControl) {
			focusDeckBPressed();
		} else if (control == tempoFocusDeckCControl) {
			focusDeckCPressed();
		} else if (control == tempoFocusDeckDControl) {
			focusDeckDPressed();
		}
		else if(control == browserUpControl) {
			browserUpPressed();
		}
		else if(control == browserDownControl) {
			browserDownPressed();
		}
		else if(control == loadLeftControl) {
			loadLeftPressed();
		}
		else if(control == loadRightControl) {
			loadRightPressed();
		}
		else if(control == viewLeftDeckAControl) {
			viewLeftDeckAPressed();
		}
		else if(control == viewLeftDeckBControl) {
			viewLeftDeckBPressed();
		}
		else if(control == viewLeftDeckCControl) {
			viewLeftDeckCPressed();
		}
		else if(control == viewRightDeckAControl) {
			viewRightDeckAPressed();
		}
		else if(control == viewRightDeckBControl) {
			viewRightDeckBPressed();
		}
		else if(control == viewRightDeckCControl) {
			viewRightDeckCPressed();
		}
	}

	@Override
	public void buttonReleased(Button control) {
		if (control == shiftGreenControl) {
			shiftGreenReleased();
		}
		else if (control == shiftRedControl) {
			shiftRedReleased();
		}
		else if(control == browserDownControl) {
			browserDownReleased();
		}
		else if(control == browserUpControl) {
			browserUpReleased();
		}
	}

	@Override
	public void increased(Encoder encoder) {
		if (encoder == tempoFineControl) {
			switch (focus) {
			case Clock:
				tempoMasterFineIncreased();
				break;
			case DeckA:
				tempoDeckAFineIncreased();
				break;
			case DeckB:
				tempoDeckBFineIncreased();
				break;
			case DeckC:
				tempoDeckCFineIncreased();
				break;
			case DeckD:
				tempoDeckDFineIncreased();
				break;
			}
		} else if (encoder == tempoCoarseControl) {
			switch (focus) {
			case Clock:
				tempoMasterCoarseIncreased();
				break;
			case DeckA:
				tempoDeckACoarseIncreased();
				break;
			case DeckB:
				tempoDeckBCoarseIncreased();
				break;
			case DeckC:
				tempoDeckCCoarseIncreased();
				break;
			case DeckD:
				tempoDeckDCoarseIncreased();
				break;
			}
		}
	}

	@Override
	public void decreased(Encoder control) {
		if (control == tempoFineControl) {
			switch (focus) {
			case Clock:
				tempoMasterFineDecreased();
				break;
			case DeckA:
				tempoDeckAFineDecreased();
				break;
			case DeckB:
				tempoDeckBFineDecreased();
				break;
			case DeckC:
				tempoDeckCFineDecreased();
				break;
			case DeckD:
				tempoDeckDFineDecreased();
				break;
			}
		} else if (control == tempoCoarseControl) {
			switch (focus) {
			case Clock:
				tempoMasterCoarseDecreased();
				break;
			case DeckA:
				tempoDeckACoarseDecreased();
				break;
			case DeckB:
				tempoDeckBCoarseDecreased();
				break;
			case DeckC:
				tempoDeckCCoarseDecreased();
				break;
			case DeckD:
				tempoDeckDCoarseDecreased();
				break;
			}
		}
	}

	void focusDeckAPressed() {
		if (focus == Focus.DeckA) {
			focus = Focus.Clock;
			setColor(tempoFocusDeckAControl, TitleButton.OFF);
		} else {
			focus = Focus.DeckA;
			tempoFocusAllOff();
			setColor(tempoFocusDeckAControl, TitleButton.BRIGHT_ON);
		}
	}

	void focusDeckBPressed() {
		if (focus == Focus.DeckB) {
			focus = Focus.Clock;
			setColor(tempoFocusDeckBControl, TitleButton.OFF);
		} else {
			focus = Focus.DeckB;
			tempoFocusAllOff();
			setColor(tempoFocusDeckBControl, TitleButton.BRIGHT_ON);
		}
	}

	void focusDeckCPressed() {
		if (focus == Focus.DeckC) {
			focus = Focus.Clock;
			setColor(tempoFocusDeckCControl, TitleButton.OFF);
		} else {
			focus = Focus.DeckC;
			tempoFocusAllOff();
			setColor(tempoFocusDeckCControl, TitleButton.BRIGHT_ON);
		}
	}

	void focusDeckDPressed() {
		if (focus == Focus.DeckD) {
			focus = Focus.Clock;
			setColor(tempoFocusDeckDControl, TitleButton.OFF);
		} else {
			focus = Focus.DeckD;
			tempoFocusAllOff();
			setColor(tempoFocusDeckDControl, TitleButton.BRIGHT_ON);
		}
	}

	void tempoMasterFineIncreased() {
		send(TraktorAdapter.tempoMasterFineIncrease);
	}

	void tempoMasterFineDecreased() {
		send(TraktorAdapter.tempoMasterFineDecrease);
	}

	void tempoMasterCoarseIncreased() {
		send(TraktorAdapter.tempoMasterCoarseIncrease);
	}

	void tempoMasterCoarseDecreased() {
		send(TraktorAdapter.tempoMasterCoarseDecrease);
	}

	void tempoDeckAFineIncreased() {
		send(TraktorAdapter.tempoDeckAFineIncrease);
	}

	void tempoDeckAFineDecreased() {
		send(TraktorAdapter.tempoDeckAFineDecrease);
	}

	void tempoDeckACoarseIncreased() {
		send(TraktorAdapter.tempoDeckACoarseIncrease);
	}

	void tempoDeckACoarseDecreased() {
		send(TraktorAdapter.tempoDeckACoarseDecrease);
	}

	void tempoDeckBFineIncreased() {
		send(TraktorAdapter.tempoDeckBFineIncrease);
	}

	void tempoDeckBFineDecreased() {
		send(TraktorAdapter.tempoDeckBFineDecrease);
	}

	void tempoDeckBCoarseIncreased() {
		send(TraktorAdapter.tempoDeckBCoarseIncrease);
	}

	void tempoDeckBCoarseDecreased() {
		send(TraktorAdapter.tempoDeckBCoarseDecrease);
	}

	void tempoDeckCFineIncreased() {
		send(TraktorAdapter.tempoDeckCFineIncrease);
	}

	void tempoDeckCFineDecreased() {
		send(TraktorAdapter.tempoDeckCFineDecrease);
	}

	void tempoDeckCCoarseIncreased() {
		send(TraktorAdapter.tempoDeckCCoarseIncrease);
	}

	void tempoDeckCCoarseDecreased() {
		send(TraktorAdapter.tempoDeckCCoarseDecrease);
	}

	void tempoDeckDFineIncreased() {
		send(TraktorAdapter.tempoDeckDFineIncrease);
	}

	void tempoDeckDFineDecreased() {
		send(TraktorAdapter.tempoDeckDFineDecrease);
	}

	void tempoDeckDCoarseIncreased() {
		send(TraktorAdapter.tempoDeckDCoarseIncrease);
	}

	void tempoDeckDCoarseDecreased() {
		send(TraktorAdapter.tempoDeckDCoarseDecrease);
	}
	
	private void tempoFocusAllOff() {
		setColor(tempoFocusDeckAControl, TitleButton.OFF);
		setColor(tempoFocusDeckBControl, TitleButton.OFF);
		setColor(tempoFocusDeckCControl, TitleButton.OFF);
		setColor(tempoFocusDeckDControl, TitleButton.OFF);
	}

	void browserUpPressed() {
		send(TraktorAdapter.browserUpPress);
	}
	
	void browserDownPressed() {
		send(TraktorAdapter.browserDownPress);
	}
	
	void browserUpReleased() {
		send(TraktorAdapter.browserUpRelease);
	}
	
	void browserDownReleased() {
		send(TraktorAdapter.browserDownRelease);
	}
	
	void loadLeftPressed() {
		if (shiftGreenDown) {
			send(TraktorAdapter.loadDeckC);
		}
		else {
			send(TraktorAdapter.loadDeckA);
		}
	}
	
	void loadRightPressed() {
		if (shiftGreenDown) {
//			send(TraktorAdapter.loadDeckD);
		}
		else {
			send(TraktorAdapter.loadDeckB);
		}
	}
	
	void viewLeftDeckAPressed() {
		if(deckALeft.active) {
			send(TraktorAdapter.sendMonitorState);
			return;
		}
		deckBLeft.deactivate();
		deckCLeft.deactivate();
		deckDLeft.deactivate();
		deckALeft.activate();
		PushAdapter.display.writeColumn(0, Graphics.BigA);
		PushAdapter.display.update();
		leftViewButtonsOff();
		setColor(viewLeftDeckAControl, viewDeckLowerOn);
		send(TraktorAdapter.sendMonitorState);
	}

	void viewRightDeckAPressed() {
		if(deckARight.active) {
			send(TraktorAdapter.sendMonitorState);
			return;
		}
		deckBRight.deactivate();
		deckCRight.deactivate();
		deckDRight.deactivate();
		deckARight.activate();
		PushAdapter.display.writeColumn(3, Graphics.BigA);
		PushAdapter.display.update();
		rightViewButtonsOff();
		setColor(viewRightDeckAControl, viewDeckLowerOn);
		send(TraktorAdapter.sendMonitorState);
	}
	
	void viewLeftDeckBPressed() {
		if(deckBLeft.active) {
			send(TraktorAdapter.sendMonitorState);
			return;
		}
		deckALeft.deactivate();
		deckCLeft.deactivate();
		deckDLeft.deactivate();
		deckBLeft.activate();
		PushAdapter.display.writeColumn(0, Graphics.BigB);
		PushAdapter.display.update();
		leftViewButtonsOff();
		setColor(viewLeftDeckBControl, viewDeckLowerOn);
		send(TraktorAdapter.sendMonitorState);
	}

	void viewRightDeckBPressed() {
		if(deckBRight.active) {
			send(TraktorAdapter.sendMonitorState);
			return;
		}
		deckARight.deactivate();
		deckCRight.deactivate();
		deckDRight.deactivate();
		deckBRight.activate();
		PushAdapter.display.writeColumn(3, Graphics.BigB);
		PushAdapter.display.update();
		rightViewButtonsOff();
		setColor(viewRightDeckBControl, viewDeckLowerOn);
		send(TraktorAdapter.sendMonitorState);
	}

	
	void viewLeftDeckCPressed() {
		if(deckCLeft.active) {
			send(TraktorAdapter.sendMonitorState);
			return;
		}
		deckALeft.deactivate();
		deckBLeft.deactivate();
		deckDLeft.deactivate();
		deckCLeft.activate();
		PushAdapter.display.writeColumn(0, Graphics.BigC);
		PushAdapter.display.update();
		leftViewButtonsOff();
		setColor(viewLeftDeckCControl, viewDeckLowerOn);
		send(TraktorAdapter.sendMonitorState);
	}

	void viewRightDeckCPressed() {
		if(deckCRight.active) {
			send(TraktorAdapter.sendMonitorState);
			return;
		}
		deckARight.deactivate();
		deckBRight.deactivate();
		deckDRight.deactivate();
		deckCRight.activate();
		PushAdapter.display.writeColumn(3, Graphics.BigC);
		PushAdapter.display.update();
		rightViewButtonsOff();
		setColor(viewRightDeckCControl, viewDeckLowerOn);
		send(TraktorAdapter.sendMonitorState);
	}

	private void leftViewButtonsOff() {
		setColor(viewLeftDeckAControl, viewDeckLowerOff);
		setColor(viewLeftDeckBControl, viewDeckLowerOff);
		setColor(viewLeftDeckCControl, viewDeckLowerOff);
		setColor(viewLeftDeckDControl, viewDeckLowerOff);
	}
	
	private void rightViewButtonsOff() {
		setColor(viewRightDeckAControl, viewDeckLowerOff);
		setColor(viewRightDeckBControl, viewDeckLowerOff);
		setColor(viewRightDeckCControl, viewDeckLowerOff);
		setColor(viewRightDeckDControl, viewDeckLowerOff);
	}
	
	



	void shiftGreenPressed() {
		shiftGreenDown = true;
		setColor(shiftGreenControl, TitleButton.BRIGHT_ON);
	}
	
	void shiftGreenReleased() {
		shiftGreenDown = false;
		setColor(shiftGreenControl, TitleButton.MEDIUM_ON);
	}
	
	void shiftRedPressed() {
		shiftRedDown = true;
		setColor(shiftRedControl, TitleButton.BRIGHT_ON);
	}
	
	void shiftRedReleased() {
		shiftRedDown = false;
		setColor(shiftRedControl, TitleButton.MEDIUM_ON);
	}
}
