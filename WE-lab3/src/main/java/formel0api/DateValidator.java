package formel0api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * The {@link DateValidator} validates a new {@link User} if it is ready to
 * get registrated
 *
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
@FacesValidator("dateValidator")
public class DateValidator implements Validator {


    public DateValidator() {
    }

    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {        
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "#{msg['register.birthdayError']}", null);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        df.setLenient(true);
        Calendar date = Calendar.getInstance();
        try {
            Date parsed = df.parse(value.toString());
            date.setTime(parsed);
        } catch (ParseException ex) {
            throw new ValidatorException(msg);
        }
        /* If it is after today also throw a msg */
        if(date.after(Calendar.getInstance()))
            throw new ValidatorException(msg);
    }

    /*
    private void checkIfUsernameAlreadyExists(User user, List<FacesMessage> messages) {
        for (User u : register.getAllUsers()) {
            if (!user.getUsername().equalsIgnoreCase(u.getUsername())) {
                FacesMessage message = new FacesMessage();
                message.setDetail("The username \"" + user.getUsername() + "\" does already exists");
                message.setSummary("Registration incorrect");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                messages.add(message);
                return;
            }
        }
    }
    * */
}
