function checkPassword() {
    var firstPassword = document.getElementById("inputPws").value;
    var secondPassword = document.getElementById("inputConfirmPws").value;
    if (firstPassword !== secondPassword) {
        alert("\nPassword did not match: Please try again...")
        document.getElementById("registerValid").value = "false";
    }else {
        document.getElementById("registerValid").value = "true";
    }
}