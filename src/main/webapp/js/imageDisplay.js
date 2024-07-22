document.addEventListener("DOMContentLoaded", function() {

console.log("image JavaScript file loaded successfully.");

    const imageLink = document.getElementById("imageLink");
    if (imageLink) {
        imageLink.addEventListener("click", function(event) {
            event.preventDefault();
            const modal = document.getElementById("imageModal");
            const modalBody = document.querySelector("#imageModal .modal-body");
            const img = document.createElement("img");

            img.src = event.target.dataset.imageUrl;
            img.classList.add("img-fluid");

            modalBody.innerHTML = ""; // Clear any previous image
            modalBody.appendChild(img);

            $(modal).modal("show");
        });
    }
});