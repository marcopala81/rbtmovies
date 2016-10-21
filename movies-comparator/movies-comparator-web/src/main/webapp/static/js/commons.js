/*
 * JS library for the Movies Comparator web application
 * 
 * Version 1.0.1
 */

/*
 * Check user rating against a regular expression allowing only numbers and dots.
 * 
 * @param event The key pressed event
 * @param inputId The focused rating input text-box
 * 
 * @since v1.0.0
 */ 
function validateRating(event, inputId)
{
    var rgx = /^[0-9]*\.?[0-9]*$/;
    var allowed = false;
    var keyPressed = (typeof event.which == "number") ? event.which : event.keyCode;
    var charCode = String.fromCharCode(keyPressed);
    if (charCode.match(rgx))
    {
      allowed = true;
    }
    else
    {
      getAlertFloat(charCode);
      parseFloatRating(inputId);
      
    }
    return allowed;
}

/*
 * Check rating on leaving rating input text-box (allowing only values between 0.0 to 10.0)
 * 
 * @param inputId The rating input text-box
 * 
 * @since v1.0.0
 */
function parseFloatRating(inputId)
{
    var rating = document.getElementById(inputId);
    // Additional check to avoid truncating if more than one dot is present
    var dots = (rating.value.match(/\./g) || []).length;
    // Converting in float
    var test = parseFloat(rating.value);
    var submit = false;
    
    if (isNaN(test) || dots > 1)
    {
      getAlertFloat(rating.value);
      rating.value = 0;
    }
    else
    {
      if (test > 10)
      {
        getAlertFloat(rating.value);
        rating.value = 10;
      }
      else
      {
        submit = true;
        if (inputId == "rating1")
        { 
          checkOldRatingAndMarkChanged(rating, "oldRating1", "changedFlag1");
        }
        else if (inputId == "rating2")
        {
          checkOldRatingAndMarkChanged(rating, "oldRating2", "changedFlag2");
        }
      }
    }
    return submit;
}

/*
 * Update changed flag on new ratings
 * 
 * @param newRating The rating input text-box
 * @param idOldRating The old rating hidden input
 * @param idChangedFlag The changed flag hidden input
 * 
 * @since v1.0.0
 */
function checkOldRatingAndMarkChanged(newRating, idOldRating, idChangedFlag)
{
    var changedFlag = document.getElementById(idChangedFlag);
    var oldRating = document.getElementById(idOldRating);
    if (newRating.value != oldRating.value)
    {
      changedFlag.value = true;     
    }
    else
    {
      changedFlag.value = false;
    }
    //debugRatingAndChanged(newRating.id, oldRating.value, newRating.value, changedFlag.value);
}

/*
 * Switch favourite checkboxes
 * PATCH 1.0.1: Removed markHiddens() function to not modify changed flag on marking favourites
 * 
 * @param checkboxId The current favourite checkbox
 * @param otherCheckboxId The other favourite checkbox
 * 
 * @since v1.0.0
 */
function switchOther(checkboxId, otherCheckboxId)
{
    var current = document.getElementById(checkboxId);
    var other = document.getElementById(otherCheckboxId);
    if (current.checked == true)
    {
      other.checked = false;
    }
    
    return true;
}

/*
 * Alert used in the rating validation
 * 
 * @param wrong The wrong float value
 * 
 * @since v1.0.0
 */
function getAlertFloat(wrong)
{
  alert("Only values between 0.0 and 10.0 are allowed for rating!\n" +
          "Please enter a new rating...\n\n" +
          ">>> Wrong value: [" + wrong + "]");
}

/*
 * Debugging alert
 * 
 * @param inputId The current rating input text-box id
 * @param oldRatingVal The old rating value
 * @param newRatingVal The new rating value
 * @param changedFlagVal The changed flag value
 * 
 * @since v1.0.0
 */
function debugRatingAndChanged(inputId, oldRatingVal, newRatingVal, changedFlagVal)
{
  alert("Input rating ID: " + inputId +
          "\n Old rating: " + oldRatingVal + " | New: " + newRatingVal +
          "\n 'changed' flag: " + changedFlagVal);
}
