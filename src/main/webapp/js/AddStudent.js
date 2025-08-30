$(document).ready(function () {
    console.log("JQuery is loaded ");

    $("#studentForm").on("submit", function (e) {
        e.preventDefault();
        console.log("Submit Button Clicked");

        let name = $("#stdName").val().trim();
        let rollNumber = $("#stdRoll").val().trim();
        let age = $("#stdAge").val().trim();
        let email = $("#stdEmail").val().trim();
        let branchName = $("#stdBranch").val().trim();
        let stdAdd = $("#stdAdd").val().trim();
        let stdCity = $("#stdCity").val().trim(); 
        let state = $("#stdState").val().trim();
        let pin = $("#stdPin").val().trim();
        let college = $("#stdCollege").val().trim();
        let isValid = true;

     
        $(".error").remove();

        // Validate Name
        if (name === "" || name.length < 4) {
            $("#stdName").after('<span class="error text-danger">Name must be at least 4 characters</span>');
            isValid = false;
        }

        // Validate Age
        if (age === "" || isNaN(age) || age < 18) {
            $("#stdAge").after('<span class="error text-danger">Age must be 18 or above</span>');
            isValid = false;
        }

        // Validate Roll Number
        if (!/^\d{8,}$/.test(rollNumber)) {
            $("#stdRoll").after('<span class="error text-danger">Roll number must be at least 8 digits and only numbers</span>');
            isValid = false;
        }

        // Validate Branch
        if (branchName === "") {
            $("#stdBranch").after('<span class="error text-danger">Branch is required</span>');
            isValid = false;
        }

        // Validate Email
        if (email === "") {
            $("#stdEmail").after('<span class="error text-danger">Email is required</span>');
            isValid = false;
        } else if (!/^\S+@\S+\.\S+$/.test(email)) {
            $("#stdEmail").after('<span class="error text-danger">Enter a valid email</span>');
            isValid = false;
        }

        // Validate Address
        if (stdAdd === "") {
            $("#stdAdd").after('<span class="error text-danger">Address is required</span>');
            isValid = false;
        }

        // Validate City
        if (stdCity === "") {
            $("#stdCity").after('<span class="error text-danger">City is required</span>');
            isValid = false;
        }

        // Validate State
        if (state === "") {
            $("#stdState").after('<span class="error text-danger">Please select a state</span>');
            isValid = false;
        }

        // Validate Pin Code
        if (!/^[0-9]{6}$/.test(pin)) {
            $("#stdPin").after('<span class="error text-danger">Please enter a valid 6-digit Pin Code</span>');
            isValid = false;
        }

        // Validate College Name
        if (college === "" || college.length < 3) {
            $("#stdCollege").after('<span class="error text-danger">Please enter a valid College Name (min 3 characters)</span>');
            isValid = false;
        }

        // Final Check
        if (isValid) {
            Swal.fire({
                icon: 'success',
                title: 'Success!',
                text: 'Form submitted successfully!',
                confirmButtonText: 'OK'
            });
        }
    });
});
/**
 * 
 */