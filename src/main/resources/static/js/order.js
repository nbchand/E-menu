//deletes order
$('.del').on('click',function(event){
    const id = event.target.id;
    fetch("/orders/"+id, {
        method: 'DELETE',
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