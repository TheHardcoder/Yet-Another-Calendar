function validatePwd() {
	var errormessages = document.getElementById("error_messages");
	var invalid = " "; // Invalid character is a space
	var minLength = 6; // Minimum length
	var pw1 = document.forms[0].password.value;
	var pw2 = document.forms[0].password_validation.value;
	// check for a value in both fields.
	if (pw1 == '' || pw2 == '') {
		errormessages.innerHTML = 'Please enter your password twice.';
		return false;
	}
	// check for minimum length
	if (document.forms[0].password.value.length < minLength) {
		errormessages.innerHTML = 'Your password must be at least ' + minLength
				+ ' characters long. Try again.';
		return false;
	}
	// check for spaces
	if (document.forms[0].password.value.indexOf(invalid) > -1) {
		errormessages.innerHTML = "Sorry, spaces are not allowed.";
		return false;
	} else {
		if (pw1 != pw2) {
			errormessages.innerHTML = "You did not enter the same new password twice.";
			return false;
		} else {
			errormessages.innerHTML = "";
			document.getElementById("loginyac").submit();
			return true;
		}
	}
}