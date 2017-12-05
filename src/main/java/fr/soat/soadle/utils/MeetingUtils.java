package fr.soat.soadle.utils;

import fr.soat.soadle.model.Meeting;

public class MeetingUtils {
	
	

	/**
	 * @param meeting
	 * @param lMeeting
	 */
	public static void mergeMeetingInMeetingDb(Meeting meeting, Meeting lMeeting) {
		lMeeting.setTitle(meeting.getTitle());
		lMeeting.setDescription(meeting.getDescription());
		lMeeting.setTags(meeting.getTags());
		lMeeting.getLocation().setName(meeting.getLocation().getName());
		lMeeting.getLocation().setAddress(meeting.getLocation().getAddress());
		lMeeting.getOptions().iterator().next().setDate(meeting.getOptions().iterator().next().getDate());
	}

}
