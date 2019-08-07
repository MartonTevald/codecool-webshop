var email = document.getElementById("email");

email.addEventListener("input", function () {
    if (email.validity.typeMismatch) {
        email.setCustomValidity("I expect an e-mail like: example@example.example");
    } else {
        email.setCustomValidity("");
    }
});