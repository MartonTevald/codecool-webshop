

function checkEmail() {
    var email = document.getElementById("email");
    var emailInput = document.getElementById("inputEmail");
    email.addEventListener("input", function () {
        if (email.validity.typeMismatch || emailInput.validity.typeMismatch) {
            email.setCustomValidity("example@example.example");
        } else {
            email.setCustomValidity("");
        }
    })
}