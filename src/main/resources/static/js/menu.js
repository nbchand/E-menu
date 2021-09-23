//displays add item form when add item buttonis clicked
$('#addBtn').on('click',function(){
    $('#formContainer').css({
        display: 'block'
    })
})

//hides add item form when cancel button is clicked
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

$('.delButton').on('click',function(event){
    const id = event.target.id;
    fetch("/items/"+id, {
        method: 'DELETE',
    })
    .then(response => response.text())
    .then(data => {
        location.href = "/menu";
        console.log(data);
      })
    .catch((error) => {
        console.error('Error:', error);
      });
})