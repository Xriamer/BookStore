function validateMid() {
    return validateEmpty("mid");
}
function validatePassword() {
    return validateEmpty("password");
}
function validateCode() {
    return validateEmpty("code");
}
function validateRegist() {
    return validateMid() && validatePassword();
}
function validateLogin() {
    return validateMid() && validatePassword() && validateCode();
}