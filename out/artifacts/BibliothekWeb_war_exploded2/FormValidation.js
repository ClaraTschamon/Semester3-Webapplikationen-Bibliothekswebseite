const form = document.getElementById('form');
const firstName = document.getElementById('firstName');
const lastName = document.getElementById('lastName');
const address = document.getElementById('address');
const country = document.getElementById('country');
const userID = document.getElementById('userID');
const password1 = document.getElementById('password1');
const password2 = document.getElementById('password2');
const gender = document.getElementById('gender');
const genderMale = document.getElementById('genderMale');
const genderFemale = document.getElementById('genderFemale');
const genderDivers = document.getElementById('genderDivers');
const mediatype = document.getElementById('mediatype');
let mediatypes = document.querySelectorAll('input[name="Medientyp"]');
const userCategory = document.getElementById('userCategory');
const categoryCustomer = document.getElementById('categoryCustomer');
const categoryEmployee = document.getElementById('categoryEmployee');
const categoryAdmin = document.getElementById('categoryAdmin');
const email = document.getElementById('emailForm');
let hasError = false;

form.addEventListener('submit', e => {
    e.preventDefault();
    validateInputs();

    if(!hasError){
        form.submit();
    }
    hasError=false
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success');
    hasError = true;
};

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const isValidUserID = userIDValue => {
    const regex = new RegExp('^[a-zA-Z_]{5,12}$'); //^ und $ ist wichtig weil dann gesamte zeichenkette abgedeckt ist
    return regex.test(userIDValue);
}

const isValidPassword = passwordValue => {
 const regex = new RegExp('^[a-zA-Z][a-zA-Z0-9_]{5,10}$');
    return regex.test(passwordValue);
}

const isValidEmail = emailValue => {
    const regex = new RegExp('^[a-zA-Z]([a-zA-Z_-][.]{0,1})*@[a-z0-9]([a-z0-9_-][.]{0,1}){2,62}[^.-]$');
    return regex.test(emailValue);
}

const validateInputs = () =>{
    //trim() removes all the whitespaces that the string has
    const firstNameValue = firstName.value.trim();
    const lastNameValue = lastName.value.trim();
    const addressValue = address.value.trim();
    const userIDValue = userID.value.trim();
    const password1Value = password1.value.trim();
    const password2Value = password2.value.trim();
    const emailValue = email.value.trim();

    if(firstNameValue === ''){
        setError(firstName, 'Pflichtfeld');
    }else{
        setSuccess(firstName);
    }

    if(lastNameValue === ''){
        setError(lastName, 'Pflichtfeld')
    }else{
        setSuccess(lastName)
    }

    if(addressValue === ''){
        setError(address, 'Pflichtfeld')
    }else{
        setSuccess(address)
    }

    if(country.value === 'auswählen'){
        setError(country, 'Bitte Land auswählen')
    }else{
        setSuccess(country)
    }

    if(userIDValue === ''){
        setError(userID,  'Pflichtfeld')
    }else if(!isValidUserID(userIDValue)) {
        setError(userID, 'Muss zwischen 5 und 12 Zeichen lang sein und darf\n' +
            'nur Buchstaben und “_“, keine Leer- oder Sonderzeichen und keine Ziffern\n' +
            'enthalten')
    }else{
        setSuccess(userID)
    }

    if(password1Value === ''){
        setError(password1, 'Pflichtfeld')
    }
    if(password2Value === ''){
        setError(password2, 'Pflichtfeld')
    }else if(password1Value !== password2Value){
        setError(password1,'Passwörter stimmen nicht überein')
        setError(password2, 'Passwörter stimmen nicht überein')
    }else if(!isValidPassword(password1Value)){
        setError(password2, 'Muss zwischen 6 und 11 Zeichen lang sein und mit\n' +
            'einem Buchstaben anfangen und darf nur Buchstaben, Ziffern und “_“, keine\n' +
            'Leer- oder Sonderzeichen enthalten')
    }else{
        setSuccess(password1)
        setSuccess(password2)
    }

    if (!(genderMale.checked === true || genderFemale.checked === true || genderDivers.checked === true)) {
        setError(gender, 'Pflichtfeld')
    }else{
        setSuccess(gender)
    }


    let values = [];
    mediatypes.forEach((checkbox) => {
        if(checkbox.checked === true){
            values.push(checkbox.value);
        }
    });

    if(values.length < 1){
        setError(mediatype, 'Bitte Medientyp(en) auswählen')
    }else{
        setSuccess(mediatype)
    }

    if (!(categoryCustomer.checked === true || categoryEmployee.checked === true || categoryAdmin.checked === true)) {
        setError(userCategory, 'Pflichtfeld')
    }else{
        setSuccess(userCategory)
    }

    if(emailValue === ''){
        setError(email, 'Pflichtfeld');
    }
    else if(!isValidEmail(emailValue)){
        setError(email, 'Keine gültige Email Addresse')
    }
    else{
        setSuccess(email);
    }

    return hasError;
};