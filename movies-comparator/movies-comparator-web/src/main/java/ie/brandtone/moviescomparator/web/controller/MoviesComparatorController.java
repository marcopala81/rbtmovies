package ie.brandtone.moviescomparator.web.controller;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.AND_THE_WINNNER_IS_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.SAME_RATING_MOVIES_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getLeavingMessage;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;
import ie.brandtone.moviescomparator.core.impl.MovieComparatorServiceImpl;
import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.web.model.MoviesComparatorForm;

/**
 * The Spring MVC controller class.
 * 
 * @author Marco Pala
 */
@Controller
public class MoviesComparatorController
{
    /**
     * The Apache Log4J Logger. 
     */
    private static final Logger LOGGER = Logger.getLogger(MoviesComparatorController.class);
    
    /**
     * The literal for the root mapping.   
     */    
    private static final String ROOT = "/";
    
    /**
     * The key for redirecting to <code>index.jsp</code>.   
     */
    private static final String INDEX = "index";
        
    /**
     * The key for redirecting to <code>moviescomparator.jsp</code>.   
     */    
    private static final String MOVIECOMPARATOR = "moviescomparator";

    /**
     * The key for redirecting to <code>winner.jsp</code>.   
     */ 
    private static final String WINNER = "winner";

    /**
     * The key for redirecting to <code>error.jsp</code>.   
     */
    private static final String ERROR = "error";

    /**
     * The key for the {@link MoviesComparatorForm} attribute.
     */
    private static final String MC_FORM_ATTR = "mcForm";
    
    /**
     * The Movies Comparator service.
     */
    private MovieComparatorService mcService;

    /**
     * Public constructor (initialize the Movies Comparator service).
     * 
     * @throws MovieComparatorException in case of any initialization error
     */
    public MoviesComparatorController() throws MovieComparatorException  
    {
       mcService = MovieComparatorServiceImpl.getInstance(); 
    }
    
    /**
     * The default entry point for the web application ("Homepage").
     * 
     * @param model The default ModelMap argument
     * 
     * @return The forwarding literal
     */
    @RequestMapping(value = ROOT, method = RequestMethod.GET)
    public String home(ModelMap model)
    {
        String methodName = "home";
        LOGGER.info(getEnteringMessage(methodName));
        LOGGER.info(getExitingMessage(methodName));
        
        return INDEX;
    }
    
    /**
     * The mapping for the "Homepage" link.
     * 
     * @param model The default ModelMap argument
     * 
     * @return The forwarding literal
     */    
    @RequestMapping(value = ROOT + INDEX, method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        String methodName = INDEX;
        LOGGER.info(getEnteringMessage(methodName));
        LOGGER.info(getExitingMessage(methodName));

        return INDEX;
    }

    /**
     * The mapping for the Movies Comparator form (initialize the {@link ModelMap} model).
     * 
     * @param model The default ModelMap argument
     * 
     * @return The forwarding literal
     */    
    @RequestMapping(value = ROOT + MOVIECOMPARATOR, method = RequestMethod.GET)
    public String compareMoviesForm(ModelMap model)
    {
        String methodName = "compareMoviesForm";
        LOGGER.info(getEnteringMessage(methodName));
        
        // Init the form
        MoviesComparatorForm mcForm = new MoviesComparatorForm();
        model.addAttribute("mcForm", mcForm);
        
        LOGGER.info(getExitingMessage(methodName));
        
        return MOVIECOMPARATOR;
    }
     
    /**
     * The mapping for the Movies Comparator form <code>POST</code> method (when form is submitted).
     * 
     * @param mcForm The Movies Comparator form
     * @param result The validation result
     * @param model The default ModelMap argument
     * 
     * @return The forwarding literal
     */
    @RequestMapping(value = ROOT + MOVIECOMPARATOR, method = RequestMethod.POST)
    public String submitCompareMoviesForm(@ModelAttribute(MC_FORM_ATTR) @Valid MoviesComparatorForm mcForm, BindingResult result, ModelMap model)
    {
        String methodName = "submitCompareMoviesForm";
        LOGGER.info(getEnteringMessage(methodName));
        
        String returnVal = WINNER;
        boolean mcsError = false;

        if (result.hasErrors())
        {
            LOGGER.warn(getLeavingMessage(methodName));
            // Validation failed, stay in the form page and show errors
            returnVal = MOVIECOMPARATOR;
        }
        else
        {
            Movie bestMovie = null;
            
            try
            {
                bestMovie = mcService.compareMoviesByRating(mcForm.getMovieTitle1(), mcForm.getMovieTitle2());
            }
            catch (MovieComparatorException mce)
            {
                LOGGER.error(getLeavingMessage(methodName));
                // Execution interrupted, go to error page
                mcForm.setMessage(mce.getMessage());
                mcsError = true;
                returnVal = ERROR;
            }
            
            if (!mcsError)
            {
                // Execution OK
                if (bestMovie == null)
                {
                    // Same rating
                    mcForm.setMessage(getMessageFromBundle(SAME_RATING_MOVIES_MSG_KEY));
                }
                else
                {
                    // Fill better movie information
                    mcForm.setWinnerTitle(bestMovie.getTitle());
                    mcForm.setMessage(getMessageFromBundle(AND_THE_WINNNER_IS_MSG_KEY));
                    mcForm.setWinnerFound(true);
                }
                LOGGER.info(getExitingMessage(methodName));
            }
            
            model.addAttribute(MC_FORM_ATTR, mcForm);
        }      

        return returnVal;
    }
}
