var email = document.getElementById("email");

email.addEventListener("input", function () {
    if (email.validity.typeMismatch) {
        email.setCustomValidity("example@example.example");
    } else {
        email.setCustomValidity("");
    }
});