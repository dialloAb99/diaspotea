function  validationCreditCard(creditCard){
    console.log('validationCreditCard','bob');
}
document.getElementById('credit-card-number').addEventListener('blur',function (event){
    return validationCreditCard(event.target.value);
})