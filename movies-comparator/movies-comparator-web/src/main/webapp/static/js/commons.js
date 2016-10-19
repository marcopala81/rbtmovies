// Check user rating against a regular expression allowing only numbers and dots
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

// Check rating on leaving text-box (allowing only values between 0.0 to 10.0)
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
          checkOldRatingAndMarkChanged(inputId, rating, "oldRating1", "changedFlag1");
        }
        else if (inputId == "rating2")
        {
          checkOldRatingAndMarkChanged(inputId, rating, "oldRating2", "changedFlag2");
        }
      }
    }
    return submit;
}

// Update changed flag on new rating
function checkOldRatingAndMarkChanged(inputId, newRating, idOldRating, idChangedFlag)
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
    //debugRatingAndChanged(inputId, oldRating.value, newRating.value, changedFlag.value);
}

// Switch favourite checkboxes
function switchOther(checkboxId, otherCheckboxId)
{
    var current = document.getElementById(checkboxId);
    var other = document.getElementById(otherCheckboxId);
    if (current.checked == true)
    {
      markHiddens(checkboxId);
      other.checked = false;
    }
    
    return true;
}

function markHiddens(checkboxId)
{
    var changed1 = document.getElementById("changedFlag1");
    var changed2 = document.getElementById("changedFlag2");
    
    if (checkboxId == "checkbox1")
    {
      changed1.value = true;
    }
    else if (checkboxId == "checkbox2")
    {
      changed2.value = true;
    }
}

// Alert used in the rating validation
function getAlertFloat(wrong)
{
  alert("Only values between 0.0 and 10.0 are allowed for rating!\n" +
          "Please enter a new rating...\n\n" +
          ">>> Wrong value: [" + wrong + "]");
}

// Debugging alert
function debugRatingAndChanged(inputId, oldRatingVal, newRatingVal, changedFlagVal)
{
  alert("Input rating ID: " + inputId +
          "\n Old rating: " + oldRatingVal + " | New: " + newRatingVal +
          "\n 'changed' flag: " + changedFlagVal);
}
