function validateMid() {
    return validateEmpty("mid");
}
function validatePassword() {
    return validateEmpty("password");
}
function validateRegist() {
    return validateMid() && validatePassword();
}
