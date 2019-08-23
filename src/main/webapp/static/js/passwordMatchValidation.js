function checkPassword() {
    var firstPassword = document.getElementById("password").value;
    var secondPassword = document.getElementById("confirm_password").value;
    if (firstPassword !== secondPassword) {
        alert("\nPassword did not match: Please try again...")
        var submitValue = document.getElementById("register").value = "false";
    }else {
        var submitValue = document.getElementById("register").value = "true";
    }
}