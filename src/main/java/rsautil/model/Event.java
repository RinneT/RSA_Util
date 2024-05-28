package rsautil.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Event {
	
	public Event(File file) {
		
	}
	
	private List<Participant> participants = new ArrayList<>();

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

}
