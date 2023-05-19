function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if ((charCode >= 48 && charCode <= 57) ||   // numbers 0-9 on main keyboard
        (charCode >= 96 && charCode <= 105) ||  // numbers 0-9 on numeric keypad
        charCode <= 31) {                      // allow control characters
        return true;
    }
    return false;
}

