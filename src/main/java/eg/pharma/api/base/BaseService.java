package eg.pharma.api.base;

import eg.pharma.api.features.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {

    protected User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
