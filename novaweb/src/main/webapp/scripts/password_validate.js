$(document).ready(function () {
    let pass_field = $('#registerpassword');
    let confirm_pass_field = $('#passwordconfirmation');

    // Attach keyup listener to password field
    $(pass_field).keyup(function () {
        // Check password complexity
        if ($(pass_field).val() !== '') {
            $(pass_field).addClass("is-valid");
            $(pass_field).removeClass("is-invalid");
        } else {
            $(pass_field).removeClass("is-valid");
            $(pass_field).addClass("is-invalid");
        }
    });

    $(pass_field).keyup(function () {
        // Check if password fields are empty
        if ($(pass_field).val() !== '') {
            // If passwords match
            if ($(pass_field).val() === $(confirm_pass_field).val()) {
                $(confirm_pass_field).addClass("is-valid");
                $(confirm_pass_field).removeClass("is-invalid");
            } else {
                $(confirm_pass_field).removeClass("is-valid");
                $(confirm_pass_field).addClass("is-invalid");
            }
        }
    });
});