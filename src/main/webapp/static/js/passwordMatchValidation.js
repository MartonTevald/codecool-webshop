function checkPassword() {
    var firstPassword = document.getElementById("password");
    var secondPassword = document.getElementById("repeat_password");
    if (firstPassword !== secondPassword) {
        alert("\nPassword did not match: Please try again...")
        return false;
    }
}