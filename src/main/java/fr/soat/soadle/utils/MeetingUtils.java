package fr.soat.soadle.utils;

import fr.soat.soadle.model.Meeting;

public class MeetingUtils {
	
	

	/**
	 * @param meetingIn
	 * @param meetingOut
	 */
	public static void mergeMeetingInMeetingDb(Meeting meetingIn, Meeting meetingOut) {
		meetingOut.setTitle(meetingIn.getTitle());
		meetingOut.setDescription(meetingIn.getDescription());
		meetingOut.setTags(meetingIn.getTags());
		meetingOut.setPictures(meetingIn.getPictures());
		meetingOut.getLocation().setName(meetingIn.getLocation().getName());
		meetingOut.getLocation().setAddress(meetingIn.getLocation().getAddress());
		meetingOut.getOptions().iterator().next().setDate(meetingIn.getOptions().iterator().next().getDate());
	}

}
