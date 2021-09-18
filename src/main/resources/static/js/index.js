$('.form').find('input, textarea').on('keyup blur focus', function (e) {
  
  var $this = $(this),
      label = $this.prev('label');

	  if (e.type === 'keyup') {
			if ($this.val() === '') {
          label.removeClass('active highlight');
        } else {
          label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
    	if( $this.val() === '' ) {
    		label.removeClass('active highlight'); 
			} else {
		    label.removeClass('highlight');   
			}   
    } else if (e.type === 'focus') {
      
      if( $this.val() === '' ) {
    		label.removeClass('highlight'); 
			} 
      else if( $this.val() !== '' ) {
		    label.addClass('highlight');
			}
    }

});

//hide or show forms when tabs are clicked
$('.tab a').on('click', function (e) {

  e.preventDefault();
  
  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');
  
  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();
  $('#signUpForm').trigger("reset");
  $('#loginForm').trigger("reset");
  $(target).fadeIn(600);
  
});

//handle login form submission
$('#signUpForm').submit(function(event){

  event.preventDefault();

  const formData = $(this).serializeArray().reduce(function(obj, item) {
    obj[item.name] = item.value;
    return obj;
  }, {});

  const data = JSON.stringify(formData);

  fetch('/signup',{
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: data,
    })
    .then(response => response.text())
    .then(data => {
      $('#signupMessage').show().html('<div class="alert alert-danger">'+data+'</div>');
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  }
)


//handle login form submission
$('#loginForm').submit(function(event){
  event.preventDefault();
  }
)