function validateTitle() {
    return validateEmpty("title");
}

function validatePrice() {
    return validateRegex("price", /^\d+(\.\d{1,2})?$/);
}

function validateAmount() {
    return validateRegex("amount", /^\d+$/);
}

function validateNote() {
    return validateEmpty("note");
}

function validateISBN() {
    return validateEmpty("isbn");
}

function validatePublisher() {
    return validateEmpty("publisher");
}

function validateWriter() {
    return validateEmpty("writer");
}

function validateInsert() {
    return validateTitle() && validatePrice() && validateAmount() && validateNote()
        && validateWriter() && validateISBN() && validatePublisher();
}