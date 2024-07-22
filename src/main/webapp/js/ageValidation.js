document.addEventListener('DOMContentLoaded', function () {

 console.log("age JavaScript file loaded successfully."); // Debugging statement

    var form = document.getElementById('editEmployeeForm');
    var ageInput = document.getElementById('age');
    var age = ageInput.value;
    console.log(age);
    var errorMessage = document.getElementById('ageError');

    form.addEventListener('submit', function (e) {
    if(age>=18 && age<=65){
                    errorMessage.style.display = 'none';
                    console.log("Validation passed. Age: ", age); // Debugging statement
    }else{
                    e.preventDefault();
                    errorMessage.textContent = "Age must be between 18 and 65.";
                    errorMessage.style.display = 'block';
                    console.log("Validation failed. Age: ", age); // Debugging statement
    }

    });
});