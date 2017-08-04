function validateForm() {
    var x = document.forms["kform"]["firstname"].value;
    if(x == "")
        {
            alert("First name must be filled out");
            return false;
        }
    if(!isNaN(parseFloat(x)) && isFinite(x))
        {
            alert("Invalid name input");
            return false;
        }
    x = document.forms["kform"]["lastname"].value;
    if(x == "")
        {
            alert("Last name must be filled out");
            return false;
        }
    x = document.forms["kform"]["email"].value;
    if(x == "")
        {
            alert("Invalid email input");
            return false;
        }
    if(!isNaN(parseFloat(x)) && isFinite(x))
        {
            alert("Invalid name input");
            return false;
        }
    x = document.forms["kform"]["streetaddress"].value;
    if(x == "")
        {
            alert("Street address must be filled out");
            return false;
        }
    x = document.forms["kform"]["city"].value;
    if(x == "")
        {
            alert("City must be filled out");
            return false;
        }
    if(!isNaN(parseFloat(x)) && isFinite(x))
        {
            alert("Invalid city input");
            return false;
        }
    x = document.forms["kform"]["state"].value;
    if(x == "")
        {
            alert("State must be filled out");
            return false;
        }
    if(!isNaN(parseFloat(x)) && isFinite(x))
        {
            alert("Invalid state input");
            return false;
        }
    x = document.forms["kform"]["zipcode"].value;
    if(x == "")
        {
            alert("Zip code must be filled out");
            return false;
        }
    if(!(!isNaN(parseFloat(x)) && isFinite(x)))
        {
            alert("Invalid zip code input");
            return false;
        }
    x = document.forms["kform"]["phonenumber"].value;
    if(x == "")
        {
            alert("Phone number must be filled out");
            return false;
        }
    if(!(!isNaN(parseFloat(x)) && isFinite(x)))
        {
            alert("Invalid phone number input");
            return false;
        }
    x = document.forms["kform"]["ccnum"].value;
    if(x == "")
        {
            alert("Credit card number must be filled out");
            return false;
        }
    if(!(!isNaN(parseFloat(x)) && isFinite(x)))
        {
            alert("Invalid credit card number input");
            return false;
        }
    if(x.toString().length < 14 || x.toString().length > 16)
        {
            alert("Incorrect credit card number length")
            return false;
        }
    x = document.forms["kform"]["ccname"].value;
    if(x == "")
        {
            alert("Name on credit card must be filled out");
            return false;
        }
    if(!isNaN(parseFloat(x)) && isFinite(x))
        {
            alert("Invalid name input");
            return false;
        }
    x = document.forms["kform"]["ccsnum"].value;
    if(x == "")
        {
            alert("Security number must be filled out");
            return false;
        }
    if(!(!isNaN(parseFloat(x)) && isFinite(x)))
        {
            alert("Invalid security number input");
            return false;
        }
    if(x.toString().length != 3)
        {
            alert("Invalid security number");
            return false;
        }
}