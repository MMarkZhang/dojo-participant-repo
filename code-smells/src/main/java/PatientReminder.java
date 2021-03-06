import tell.dont.ask.EmailService;
import tell.dont.ask.Patient;
import tell.dont.ask.TextMessageService;


public class PatientReminder {

    private final EmailService emailService;
    private final TextMessageService phoneService;

    public PatientReminder(EmailService emailService, TextMessageService phoneService) {
        this.emailService = emailService;
        this.phoneService = phoneService;
    }
    
    // Reminds a patient
    public void remind(Patient patient) {
        // Email the patient if they have an email address
        String emailAddress = patient.getEmailAddress();
        
        // Email addresses are null when the patient doesn't have one
        if(emailAddress != null) {
            emailService.emailReminderTo(emailAddress);
        }
        
        // Text a patient if they have a mobile, otherwise ring them
        String phoneNumber = patient.getPhoneNumber();

        if(isValidPhoneNumber(phoneNumber)) {
            if(isMobileNumber(phoneNumber)) {
                phoneService.sendTextReminderTo(phoneNumber);
            } else {
                phoneService.callWithReminder(phoneNumber);
            }
        }
    }

    private boolean isMobileNumber(String phoneNumber) {
        return phoneNumber.startsWith("07");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Phone numbers are null when teh patient doesn't have one
        return phoneNumber != null 
            && phoneNumber.length() == 11;
    }

}