document.addEventListener("DOMContentLoaded", function() {
    let select = document.querySelector("select"),
        divOp = document.querySelector("div.opciones");

    select.addEventListener("change", function() {
        if (select.value == 1) {
            divOp.style.display = "block";
        } else {
            divOp.style.display = "none";
        }
    });
});