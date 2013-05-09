package formel0api;

import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * The {@link RegisterValidator} validates a new {@link User} if it is ready to
 * get registrated
 *
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
@FacesValidator("RegisterValidator")
public class RegisterValidator implements Validator {

    private Register register = null;

    public RegisterValidator() {
        register = Register.getInstance();
    }

    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        User user = (User) value;

        List<FacesMessage> messages = new LinkedList<FacesMessage>();
        checkIfUsernameAlreadyExists(user, messages);
        if (!messages.isEmpty()) {
            throw new ValidatorException(messages);
        }

    }

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
}
