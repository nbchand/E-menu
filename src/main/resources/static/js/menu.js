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

//deals with button clicking
const menu = document.querySelector("form#menu");

menu.addEventListener("change", e => {
	if(e.target.name !== "food") return;
  menu.querySelectorAll("[name=food]").forEach(check => {
  	setTimeout(() => check.nextElementSibling.textContent = check.checked ? "Selected" : "Select Item", 150);
  })
}, {passive: true});

//deletes items
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

//creates orders
$('#menu').submit(function(event){
    event.preventDefault();

    var array = [];
    $("input:checkbox[name='food']:checked").each(function() {
        array.push($(this).val());
    });

    fetch("/create-order", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(array),
    })
    .then(response => response.text())
    .then(data => {
        location.href = "/orders";
        console.log(data);
      })
    .catch((error) => {
        console.error('Error:', error);
      });


})