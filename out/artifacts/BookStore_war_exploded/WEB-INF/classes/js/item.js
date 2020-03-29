function validateIid() {
  return validateEmpty("iid");
}

function validateTitle() {
  return validateEmpty("title");
}

function validateInsert() {
  return validateTitle();
}

function goUpdate(iid) {
  if(validateEmpty("title-"+iid)){
    title=document.getElementById("title-"+iid).value;
    document.getElementById("iid").value=iid;
    document.getElementById("title").value=title;
    document.getElementById("itemForm").submit();
  }
}