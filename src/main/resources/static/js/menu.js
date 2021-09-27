$('#jsError').hide();
// $('.occurance').hide();

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

// deals with button clicking
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
        if(data==="success"){
            location.href = "/menu";
        }else{
            $('#jsError').show();
            $('#jsError').html("<div class='alert-danger mt-3 p-2 text-center h3'>"+data+"</div>");
            window.scrollTo(0, 0);
        }
        
      })
    .catch((error) => {
        console.error('Error:', error);
      });
})

//creates orders
$('#menu').submit(function(event){
    event.preventDefault();

    var items = [];
    var occurance = [];
    $("input:checkbox[name='food']:checked").each(function() {
        items.push($(this).val());
        const quantity = $(this).siblings('.occurance').children('select').val();
        occurance.push(quantity);
    });

    if(items.length === 0)
    {
        $('#jsError').show();
        $('#jsError').html("<div class='alert-danger mt-3 p-2 text-center h3'>No items selected</div>");
        window.scrollTo(0, 0);
        return;
    };

    const data = JSON.stringify({items,occurance});

    fetch("/create-order", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
          },
          body: data,
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