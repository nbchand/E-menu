$('#addBtn').on('click',function(){
    $('#formContainer').css({
        display: 'block'
    })
})

$('#cancel').on('click',function(){
    $('#formContainer').css({
        display: 'none'
    })
})

//deal with button clicking
const menu = document.querySelector("form#menu");

menu.addEventListener("change", e => {
	if(e.target.name !== "food") return;
  menu.querySelectorAll("[name=food]").forEach(check => {
  	setTimeout(() => check.nextElementSibling.textContent = check.checked ? "Selected" : "Select Item", 150);
  })
}, {passive: true});

//   //create order
//   //extracts names and prices from the active buttons as well as sends them in csv format to the controller
//   menu.addEventListener("submit", e => {
//     e.preventDefault();
//     const input = new FormData(e.target);
//     console.log(...input.getAll("food"));
//   });