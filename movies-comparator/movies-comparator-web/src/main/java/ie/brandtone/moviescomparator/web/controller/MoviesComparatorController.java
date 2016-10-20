package ie.brandtone.moviescomparator.web.controller;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.AND_THE_WINNNER_IS_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.SAME_RATING_MOVIES_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.brandtone.moviescomparator.web.MoviesMvcFacade;
import ie.brandtone.moviescomparator.web.exception.MvcFacadeException;
import ie.brandtone.moviescomparator.web.model.MoviesCompareForm;

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
     * The key for redirecting to <code>update.jsp</code>. 
     */
    private static final String UPDATE = "update";
    
    /**
     * The key for redirecting to <code>error.jsp</code>.   
     */
    private static final String ERROR = "error";

    /**
     * The key for the {@link MoviesCompareForm} attribute.
     */
    private static final String MOVIES_COMP_FORM_ATTR = "moviesCompForm";
    
    /**
     * The Movies Comparator MVC facade.
     */
    @Autowired
    private MoviesMvcFacade mvcFacade;
    
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
        LOGGER.info(getEnteringMessage(ROOT));        
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
        LOGGER.info(getEnteringMessage(ROOT + INDEX));
        return INDEX;
    }

    /**
     * The mapping for the {@link MoviesCompareForm} <code>GET</code> method (when the form is requested).
     * 
     * @param model The default ModelMap argument
     * 
     * @return The forwarding literal
     */    
    @RequestMapping(value = ROOT + MOVIECOMPARATOR, method = RequestMethod.GET)
    public String moviescomparator(ModelMap model)
    {
        LOGGER.info(getEnteringMessage(ROOT + MOVIECOMPARATOR));

        // Init the form
        MoviesCompareForm moviesCompForm = new MoviesCompareForm();
        model.addAttribute(MOVIES_COMP_FORM_ATTR, moviesCompForm);            
        
        return MOVIECOMPARATOR;
    }
     
    /**
     * The mapping for the {@link MoviesCompareForm} <code>POST</code> method (when <code>submitMoviesCompare</code> form is submitted).
     * 
     * @param moviesCompForm The MoviesCompareForm
     * @param result The validation result
     * @param model The default ModelMap argument
     * 
     * @return The forwarding literal
     */
    @RequestMapping(value = ROOT + MOVIECOMPARATOR, method = RequestMethod.POST)
    public String submitMoviesCompare(@ModelAttribute(MOVIES_COMP_FORM_ATTR) @Valid MoviesCompareForm moviesCompForm, BindingResult result, ModelMap model)
    {
        LOGGER.info(getEnteringMessage(ROOT + MOVIECOMPARATOR));
        
        String returnVal = WINNER;
                
        if (result.hasErrors())
        {
            // Validation failed, stay in the form page and show errors
            returnVal = MOVIECOMPARATOR;
        }
        else
        {
            try
            {
                // Prepare the comparison results 
                moviesCompForm = mvcFacade.prepareComparisonResults(moviesCompForm);

                if (moviesCompForm.getWinnerMovie() == null)
                {
                    // Same rating
                    moviesCompForm.setMessage(getMessageFromBundle(SAME_RATING_MOVIES_MSG_KEY));
                }
                else
                {
                    // FOUND A WINNER
                    moviesCompForm.setMessage(getMessageFromBundle(AND_THE_WINNNER_IS_MSG_KEY));
                    moviesCompForm.setWinnerFound(true);
                }                
            }
            catch (Exception e)
            {
                // Execution interrupted, go to error page
                moviesCompForm.setMessage(e.getMessage());
                returnVal = ERROR;
            }
            
            // Propagate the Model to the View
            model.addAttribute(MOVIES_COMP_FORM_ATTR, moviesCompForm);
        }      

        return returnVal;
    }
    

    /**
     * The mapping for the {@link MoviesCompareForm} <code>POST</code> method (when <code>submitMoviesUpdate</code> form is submitted).
     * 
     * @param moviesCompForm The MoviesCompareForm
     * @param result The validation result
     * @param model The default ModelMap argument
     * 
     * @return The forwarding literal
     */
    @RequestMapping(value = ROOT + UPDATE, method = RequestMethod.POST)
    public String submitMoviesUpdate(MoviesCompareForm moviesCompForm, BindingResult result, ModelMap model)
    {                                
        try
        {
            // Check for changes
            // PATCH 1.0.1 checkForLocalModifications now can throw a MvcFacadeException in case of DB issues
            moviesCompForm = mvcFacade.checkForLocalModifications(moviesCompForm);
            
            // Persist movies in the DB
            mvcFacade.saveMovies(moviesCompForm);
            // Fill the local movie cache from DB
            moviesCompForm.setLocalMovies(mvcFacade.getCachedMovies());
        }
        catch (MvcFacadeException mvcfe)
        {
            // Non blocking error on showing movie table
            LOGGER.error(mvcfe);
        }
        
        // Propagate the Model to the View
        model.addAttribute(MOVIES_COMP_FORM_ATTR, moviesCompForm);
        
        return UPDATE;
    }
}
