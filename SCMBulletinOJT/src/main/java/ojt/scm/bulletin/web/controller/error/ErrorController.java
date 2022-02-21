package ojt.scm.bulletin.web.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h2>ErrorController Class</h2>
 * <p>
 * Process for Displaying ErrorController
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Controller
public class ErrorController {
    /**
     * <h2>showErr401Page</h2>
     * <p>
     * 
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/error401", method = RequestMethod.GET)
    public ModelAndView showErr401Page(Model model) {
        ModelAndView errView = new ModelAndView("common/error/error401");
        return errView;
    }

    /**
     * <h2>showErr404Page</h2>
     * <p>
     * 
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public ModelAndView showErr404Page(Model model) {
        ModelAndView errView = new ModelAndView("common/error/error404");
        return errView;
    }

    /**
     * <h2>showErr500Page</h2>
     * <p>
     * 
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/error500", method = RequestMethod.GET)
    public ModelAndView showErr500Page(Model model) {
        ModelAndView errView = new ModelAndView("common/error/error500");
        return errView;
    }
}