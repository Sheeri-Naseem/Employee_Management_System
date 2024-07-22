document.addEventListener('DOMContentLoaded', function () {

 console.log("age JavaScript file loaded successfully."); // Debugging statement

    var form = document.getElementById('editEmployeeForm');
    var age = document.getElementById('age');
    var errorMessage = document.getElementById('ageError');

    form.addEventListener('submit', function (e) {
     if (age < 18 || age > 65) {
                e.preventDefault();
                errorMessage.textContent = "Age must be between 18 and 65.";
                errorMessage.style.display = 'block';
                   console.log("Validation failed. Age: ", age); // Debugging statement
            }else {
                         errorMessage.style.display = 'none';
                          console.log("Validation passed. Age: ", age); // Debugging statement
                     }

//        var dob = dobInput.value;
//        var today = new Date();
//        var age = today.getFullYear() - dob.getFullYear();
//        var monthDiff = today.getMonth() - dob.getMonth();
//
//        console.log("DOB: ", dob); // Debugging statement
//        console.log("Today: ", today); // Debugging statement
//        console.log("Age: ", age); // Debugging statement
//
//        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < dob.getDate())) {
//            age--;
//        }
//
//        if (age < 18 || age > 65) {
//            e.preventDefault();
//            errorMessage.textContent = "Age must be between 18 and 65.";
//            errorMessage.style.display = 'block';
//               console.log("Validation failed. Age: ", age); // Debugging statement
//        }else if (age < 0) {
//                          event.preventDefault();
//                          alert("The date of birth cannot be in the future.");
//                          errorMessage.textContent = "The date of birth cannot be in the future.";
//                          errorMessage.style.display = 'block';
//                          console.log("Validation failed. Age: ", age); // Debugging statement
//        } else {
//            errorMessage.style.display = 'none';
//             console.log("Validation passed. Age: ", age); // Debugging statement
//        }
    });
});