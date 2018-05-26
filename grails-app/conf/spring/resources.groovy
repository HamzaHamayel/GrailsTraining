import edu.training.security.UserPasswordEncoderListener
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import edu.training.DateValueConverter

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    localeResolver(SessionLocaleResolver) {
        defaultLocale= new java.util.Locale('ar','PS');
    }

    dateConverter DateValueConverter

}
